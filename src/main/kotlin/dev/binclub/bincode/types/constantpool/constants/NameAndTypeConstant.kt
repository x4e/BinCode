package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

data class NameAndTypeConstant(
	val nameIndex: ConstantPoolReference<Utf8Constant>,
	val descriptorIndex: ConstantPoolReference<Utf8Constant>
): Constant() {
	override fun toString(): String =
			"NameAndType: (Name: $nameIndex, Descriptor: $descriptorIndex)"
}
