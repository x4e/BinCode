package dev.binclub.bincode.parsing.attributes

import dev.binclub.bincode.BingaitParser.LOGGER
import dev.binclub.bincode.parsing.attributes.AttributeSource.CODE
import dev.binclub.bincode.types.ClassVersion
import dev.binclub.bincode.types.attributes.types.code.CodeAttribute
import dev.binclub.bincode.types.attributes.types.code.Opcode
import dev.binclub.bincode.types.attributes.types.code.Opcode.*
import dev.binclub.bincode.types.attributes.types.code.TryCatchBlock
import dev.binclub.bincode.types.attributes.types.code.insns.*
import dev.binclub.bincode.types.constantpool.ConstantPool
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.ClassConstant
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant
import dev.binclub.bincode.u1
import dev.binclub.bincode.u2
import dev.binclub.bincode.u4
import java.io.DataInput
import java.util.*

object CodeParser: SpecificAttributeParser<CodeAttribute>("Code") {
	override fun canParse(source: AttributeSource, version: ClassVersion) =
		(source == AttributeSource.METHOD) && version.isAtLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool): CodeAttribute {
		val maxStack = dataInput.u2()
		val maxLocals = dataInput.u2()
		
		val insns = InsnList(
			parseInsns(
				dataInput
			)
		)
		
		val exceptionCount = dataInput.u2()
		val exceptions = Array(exceptionCount) {
			val start = dataInput.u2()
			val end = dataInput.u2()
			val handler = dataInput.u2()
			val catch: ConstantPoolReference<ClassConstant>? = (dataInput.u2()).let { index ->
				if (index == 0) null
				else ConstantPoolReference(index)
			}
			TryCatchBlock(start, end, handler, catch)
		}
		
		val attributes = AttributeParser.parseAttributes(CODE, version, dataInput, constantPool)
		
		return CodeAttribute(nameRef, maxStack, maxLocals, insns, exceptions, attributes)
	}
	
	fun parseInsns(dataInput: DataInput): LinkedList<Insn> {
		val codeLength = dataInput.u4()
		val insns = mutableMapOf<Int, Insn>()
		
		var offset = 0
		while (offset < codeLength) {
			val opcode = Opcode.opcodeOf(dataInput.u1())
			val _offset = offset
			
			insns[_offset] = when (opcode) {
				NOP, IALOAD, LALOAD, FALOAD, DALOAD, AALOAD, BALOAD, CALOAD, SALOAD, IASTORE, LASTORE, FASTORE, DASTORE,
				AASTORE, BASTORE, CASTORE, SASTORE, POP, POP2, DUP, DUP_X1, DUP_X2, DUP2, DUP2_X1, DUP2_X2, SWAP, IADD,
				LADD, FADD, DADD, ISUB, LSUB, FSUB, DSUB, IMUL, LMUL, FMUL, DMUL, IDIV, LDIV, FDIV, DDIV, IREM, LREM, FREM,
				DREM, INEG, LNEG, FNEG, DNEG, ISHL, LSHL, ISHR, LSHR, IUSHR, LUSHR, IAND, LAND, IOR, LOR, IXOR, LXOR, I2L,
				I2F, I2D, L2I, L2F, L2D, F2I, F2L, F2D, D2I, D2L, D2F, I2B, I2C, I2S, LCMP, FCMPL, FCMPG, DCMPL, DCMPG,
				IRETURN, LRETURN, FRETURN, DRETURN, ARETURN, RETURN, ARRAYLENGTH, ATHROW, MONITORENTER, MONITOREXIT,
				ILOAD_0, ILOAD_1, ILOAD_2, ILOAD_3, LLOAD_0, LLOAD_1, LLOAD_2, LLOAD_3, FLOAD_0, FLOAD_1, FLOAD_2, FLOAD_3,
				DLOAD_0, DLOAD_1, DLOAD_2, DLOAD_3, ALOAD_0, ALOAD_1, ALOAD_2, ALOAD_3, ISTORE_0, ISTORE_1, ISTORE_2,
				ISTORE_3, LSTORE_0, LSTORE_1, LSTORE_2, LSTORE_3, FSTORE_0, FSTORE_1, FSTORE_2, FSTORE_3, DSTORE_0,
				DSTORE_1, DSTORE_2, DSTORE_3, ASTORE_0, ASTORE_1, ASTORE_2, ASTORE_3, ACONST_NULL, ICONST_M1, ICONST_0,
				ICONST_1, ICONST_2, ICONST_3, ICONST_4, ICONST_5, LCONST_0, LCONST_1, FCONST_0, FCONST_1, FCONST_2,
				DCONST_0, DCONST_1
				-> {
					offset += 1
					Insn(opcode)
				}
				ILOAD, LLOAD, FLOAD, DLOAD, ALOAD, ISTORE, LSTORE, FSTORE, DSTORE, ASTORE -> {
					offset += 2
					VarInsn(opcode, dataInput.u1())
				}
				GETFIELD, GETSTATIC, PUTFIELD, PUTSTATIC -> {
					offset += 3
					FieldInsn(opcode, ConstantPoolReference(dataInput.u2()))
				}
				INVOKESPECIAL, INVOKESTATIC, INVOKEVIRTUAL -> {
					offset += 3
					InvokeInsn(opcode, ConstantPoolReference(dataInput.u2()))
				}
				ANEWARRAY, NEW -> {
					offset += 3
					ReferenceInsn(opcode, ConstantPoolReference(dataInput.u2()))
				}
				LDC -> {
					offset += 2
					LdcInsn(ConstantPoolReference(dataInput.u1()))
				}
				LDC_W, LDC2_W -> {
					offset += 3
					LdcInsn(opcode, ConstantPoolReference(dataInput.u2()))
				}
				else -> {
					offset += 1
					LOGGER.warning("Unknown opcode $opcode")
					Insn(INVALID)
				}
			}
		}
		
		return LinkedList(insns.values)
	}
}
