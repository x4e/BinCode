package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

// TODO: bootstrapMethod fetching from BootstrapMethodTable
abstract class AbstractDynamicConstant(
	open val bootstrapMethodIndex: Int,
	open val nameAndType: ConstantPoolReference<NameAndTypeConstant>
): Constant()

data class DynamicConstant(
	override val bootstrapMethodIndex: Int,
	override val nameAndType: ConstantPoolReference<NameAndTypeConstant>
): AbstractDynamicConstant(bootstrapMethodIndex, nameAndType) {
	override fun toString(): String =
		"(Dynamic: (BootstrapMethod: $bootstrapMethodIndex, NameAndType: $nameAndType)"
}

data class InvokeDynamicConstant(
	override val bootstrapMethodIndex: Int,
	override val nameAndType: ConstantPoolReference<NameAndTypeConstant>
): AbstractDynamicConstant(bootstrapMethodIndex, nameAndType) {
	// TODO: bootstrapMethod fetching from BootstrapMethodTable
	override fun toString(): String =
		"(InvokeDynamic: (BootstrapMethod: $bootstrapMethodIndex, NameAndType: $nameAndType)"
}
