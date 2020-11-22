package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

/**
 * @author cook 22/Nov/2020
 */
abstract class MethodConstant(
	open val classRef: ConstantPoolReference<ClassConstant>,
	open val nameAndTypeRef: ConstantPoolReference<NameAndTypeConstant>
): Constant()

data class MethodRefConstant(
	override val classRef: ConstantPoolReference<ClassConstant>,
	override val nameAndTypeRef: ConstantPoolReference<NameAndTypeConstant>
): MethodConstant(classRef, nameAndTypeRef) {
	override fun toString(): String =
		"MethodRef: (Class: $classRef, NameAndType: $nameAndTypeRef)"
}

data class InterfaceMethodRefConstant(
	override val classRef: ConstantPoolReference<ClassConstant>,
	override val nameAndTypeRef: ConstantPoolReference<NameAndTypeConstant>
): MethodConstant(classRef, nameAndTypeRef) {
	override fun toString(): String =
		"InterfaceMethodRef: (Class: $classRef, NameAndType: $nameAndTypeRef)"
}
