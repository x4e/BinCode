package dev.binclub.bincode.parsing

import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.ClassConstant
import dev.binclub.bincode.u2
import java.io.DataInput
import java.io.DataInputStream

object InterfaceParser {
	fun parseInterfaces(dataInput: DataInput): Array<ConstantPoolReference<ClassConstant>> {
		val numInterfaces = dataInput.u2()
		return Array(numInterfaces) {
			// See https://discuss.kotlinlang.org/t/compiler-type-inference-error/16704
			ConstantPoolReference<ClassConstant>(dataInput.u2())
		}
	}
}
