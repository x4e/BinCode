package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

data class ClassConstant(
	val nameRef: ConstantPoolReference<Utf8Constant>
): Constant() {
	override fun toString(): String =
		"ClassConstant: $nameRef"
}
