package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

data class MethodRefConstant(
	val classRef: ConstantPoolReference<ClassConstant>,
	val nameAndTypeRef: ConstantPoolReference<NameAndTypeConstant>
): Constant() {
	override fun toString(): String =
			"MethodRef: (Class: $classRef, NameAndType: $nameAndTypeRef)"
}
