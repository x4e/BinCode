package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.PrimitiveConstant

data class IntegerConstant(override var value: Int): PrimitiveConstant<Int>() {
	override fun toString(): String =
			"Integer: $value"
}
