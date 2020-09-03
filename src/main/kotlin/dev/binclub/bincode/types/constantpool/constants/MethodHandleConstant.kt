package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

data class MethodHandleConstant(
	val referenceKind: Int,
	val referenceRef: ConstantPoolReference<Constant>
): Constant() {
	override fun toString(): String =
			"MethodHandle: (Kind: $referenceKind, Ref: $referenceRef)"
}
