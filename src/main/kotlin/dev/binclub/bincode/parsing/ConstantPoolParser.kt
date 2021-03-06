package dev.binclub.bincode.parsing

import dev.binclub.bincode.*
import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPool
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.*
import java.io.DataInput

@Suppress("MemberVisibilityCanBePrivate")
object ConstantPoolParser {
	fun parse(dataInput: DataInput): ConstantPool {
		val size = dataInput.u2()
		return parse(dataInput, size)
	}
	
	fun parse(dataInput: DataInput, size: Int): ConstantPool {
		val invalid = InvalidConstant()
		val constantPool = ConstantPool(size, invalid)
		
		if (size > 0) {
			constantPool[0] = invalid
		}
		
		var index = 1
		while (index < size) {
			constantPool[index] = parseConstant(dataInput, index)
			if (constantPool[index].isDoubleSize()) {
				// Double size constants take up two indexes
				index += 1
			}
			index += 1
		}
		
		return constantPool
	}
	
	private fun parseConstant(dataInput: DataInput, index: Int): Constant {
		return when (val tag = dataInput.u1()) {
			1 -> Utf8Constant(dataInput.utf8())
			3 -> IntegerConstant(dataInput.u4())
			4 -> FloatConstant(java.lang.Float.intBitsToFloat(dataInput.u4()))
			5 -> LongConstant(dataInput.s8())
			6 -> DoubleConstant(java.lang.Double.longBitsToDouble(dataInput.s8()))
			7 -> ClassConstant(getCPRef(dataInput))
			8 -> StringConstant(getCPRef(dataInput))
			9 -> FieldRefConstant(getCPRef(dataInput), getCPRef(dataInput))
			10 -> MethodRefConstant(getCPRef(dataInput), getCPRef(dataInput))
			11 -> InterfaceMethodRefConstant(getCPRef(dataInput), getCPRef(dataInput))
			12 -> NameAndTypeConstant(getCPRef(dataInput), getCPRef(dataInput))
			15 -> MethodHandleConstant(dataInput.u1(), getCPRef(dataInput))
			16 -> MethodTypeConstant(getCPRef(dataInput))
			17 -> DynamicConstant(dataInput.u2(), getCPRef(dataInput))
			18 -> InvokeDynamicConstant(dataInput.u2(), getCPRef(dataInput))
			19 -> ModuleConstant(getCPRef(dataInput))
			20 -> PackageConstant(getCPRef(dataInput))
			else -> throw ClassParseException("Invalid Constant Tag $tag")
		}
	}
	
	private fun <T: Constant> getCPRef(dataInput: DataInput) = ConstantPoolReference<T>(dataInput.u2())
}
