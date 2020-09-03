package dev.binclub.bincode.parsing

import dev.binclub.bincode.*
import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPool
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.*
import java.io.DataInput
import java.io.DataInputStream

object ConstantPoolParser {
	fun parse(dataInput: DataInput): ConstantPool {
		val size = dataInput.u2()
		val invalid = InvalidConstant()
		val constantPool = ConstantPool(size, invalid)
		
		if (size > 0) {
			constantPool[0] = invalid
		}
		
		var index = 1
		while (index < size) {
			constantPool[index] = parseConstant(constantPool, dataInput)
			if (constantPool[index].isDoubleSize()) {
				// Double size constants take up two indexes
				constantPool[index++] = invalid
			}
			index++
		}
		
		return constantPool
	}
	
	private fun parseConstant(constantPool: ConstantPool, dataInput: DataInput): Constant {
		return when (val tag = dataInput.u1()) {
			7 -> ClassConstant(getCPRef(dataInput))
			9 -> FieldRefConstant(getCPRef(dataInput), getCPRef(dataInput))
			10 -> MethodRefConstant(getCPRef(dataInput), getCPRef(dataInput))
			11 -> InterfaceMethodRefConstant(getCPRef(dataInput), getCPRef(dataInput))
			8 -> StringConstant(getCPRef(dataInput))
			3 -> IntegerConstant(dataInput.u4())
			4 -> FloatConstant(java.lang.Float.intBitsToFloat(dataInput.u4()))
			5 -> LongConstant(dataInput.s8())
			6 -> DoubleConstant(java.lang.Double.longBitsToDouble(dataInput.s8()))
			12 -> NameAndTypeConstant(getCPRef(dataInput), getCPRef(dataInput))
			1 -> Utf8Constant(dataInput.utf8())
			15 -> MethodHandleConstant(dataInput.u1(), getCPRef(dataInput))
			16 -> MethodTypeConstant(getCPRef(dataInput))
			18 -> InvokeDynamicConstant(dataInput.u2(), getCPRef(dataInput))
			else -> InvalidConstant("Invalid Constant Tag $tag")
		}
	}
	
	private class InvalidConstant(val message: String? = null): Constant() {
		override fun toString(): String = message ?: "[Empty Constant Index]"
	}
	
	private fun <T: Constant> getCPRef(dataInput: DataInput) = ConstantPoolReference<T>(dataInput.u2())
}