package dev.binclub.bincode.types.attributes.types.code.insns

import dev.binclub.bincode.types.attributes.types.code.Opcode
import dev.binclub.bincode.types.attributes.types.code.Opcode.*
import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.FieldRefConstant
import dev.binclub.bincode.types.constantpool.constants.MethodRefConstant
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant

/**
 * @author cookiedragon234 17/Mar/2020
 */
open class Insn(
	open val opcode: Opcode
) {
	override fun toString(): String = """
		|$opcode
		""".trimMargin()
}

data class FieldInsn(
	override val opcode: Opcode,
	val fieldRef: ConstantPoolReference<FieldRefConstant>
): Insn(opcode) {
	override fun toString(): String = """
		|$opcode($fieldRef)
		""".trimMargin()
}

data class InvokeInsn(
	override val opcode: Opcode,
	val methodRef: ConstantPoolReference<MethodRefConstant>
): Insn(opcode) {
	override fun toString(): String = """
		|$opcode($methodRef)
		""".trimMargin()
}

data class LdcInsn(
	override val opcode: Opcode = LDC,
	val constantRef: ConstantPoolReference<Constant>
): Insn(opcode) {
	constructor(constantRef: ConstantPoolReference<Constant>): this(LDC, constantRef)
	
	override fun toString(): String = """
		|$opcode($constantRef)
		""".trimMargin()
}

data class ReferenceInsn(
	override val opcode: Opcode,
	val typeConstant: ConstantPoolReference<Utf8Constant> // Fully qualified internal name reference
): Insn(opcode) {
	override fun toString(): String = """
		|$opcode($typeConstant)
		""".trimMargin()
}

data class VarInsn(
	override val opcode: Opcode,
	val varIndex: Int
): Insn(opcode) {
	override fun toString(): String = """
		|$opcode($varIndex)
		""".trimMargin()
}

data class JumpInsn(
	override val opcode: Opcode,
	val offset: Int
): Insn(opcode) {
	override fun toString(): String = """
		|$opcode($offset)
		""".trimMargin()
}

