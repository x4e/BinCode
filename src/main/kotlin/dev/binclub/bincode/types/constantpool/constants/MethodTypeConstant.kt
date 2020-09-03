package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

data class MethodTypeConstant(
	val descriptorIndex: ConstantPoolReference<Utf8Constant>
): Constant() {
	override fun toString(): String =
			"MethodType: #$descriptorIndex"
}
