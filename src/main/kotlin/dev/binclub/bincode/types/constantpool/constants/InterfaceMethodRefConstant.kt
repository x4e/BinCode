package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

data class InterfaceMethodRefConstant(
	val classRef: ConstantPoolReference<ClassConstant>,
	val nameAndTypeRef: ConstantPoolReference<ClassConstant>
): Constant() {
	override fun toString(): String =
			"InterfaceMethodRef: (Class: $classRef, NameAndType: $nameAndTypeRef)"
}
