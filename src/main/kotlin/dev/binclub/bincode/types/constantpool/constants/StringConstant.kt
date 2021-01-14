package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPool
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

data class StringConstant(
	val stringRef: ConstantPoolReference<Utf8Constant>
): Constant() {
	override fun toString(): String =
		"String:$stringRef"
}
