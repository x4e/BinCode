package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

data class InvokeDynamicConstant(
	val bootstrapMethodIndex: Int,
	val nameAndTypeIndex: ConstantPoolReference<NameAndTypeConstant>
): Constant() {
	// TODO: bootstrapMethod fetching from BootstrapMethodTable
	override fun toString(): String =
			"(InvokeDynamic: (BootstrapMethod: $bootstrapMethodIndex, NameAndType: $nameAndTypeIndex)"
}
