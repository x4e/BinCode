package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.PrimitiveConstant

data class LongConstant(override var value: Long): PrimitiveConstant<Long>() {
	override fun isDoubleSize(): Boolean = true
	override fun toString(): String =
			"Long: $value"
}
