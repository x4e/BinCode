package dev.binclub.bincode.types.attributes.types.code.insns

import dev.binclub.bincode.types.attributes.types.code.Opcode

/**
 * @author cookiedragon234 04/Sep/2020
 */

data class LookupSwitchInsn(
	override val opcode: Opcode,
	val default: Int,
	val pairs: MutableMap<Int, Int>
): Insn(opcode) {
	override fun toString(): String = buildString {
		append("$opcode(\n")
		append("\tdflt: $default\n")
		pairs.forEach { key, value ->
			append("\t$key: $value\n")
		}
		append(")")
	}
}
