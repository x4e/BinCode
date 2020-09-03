package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.PrimitiveConstant

data class FloatConstant(override var value: Float): PrimitiveConstant<Float>() {
	override fun toString(): String =
		"Float: $value"
}
