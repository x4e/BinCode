package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.PrimitiveConstant

data class DoubleConstant(
	override var value: Double
): PrimitiveConstant<Double>() {
	override fun isDoubleSize(): Boolean = true
	override fun toString(): String =
		"Double: $value"
}

