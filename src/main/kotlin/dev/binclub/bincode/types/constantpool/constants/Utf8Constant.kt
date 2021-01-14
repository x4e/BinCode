package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.PrimitiveConstant
import dev.binclub.bincode.utils.escapeString

data class Utf8Constant(override var value: String): PrimitiveConstant<String>() {
	override fun toString(): String =
		"Utf8: ${value.escapeString()}"
}
