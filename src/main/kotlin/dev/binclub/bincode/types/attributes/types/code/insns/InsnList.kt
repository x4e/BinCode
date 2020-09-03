package dev.binclub.bincode.types.attributes.types.code.insns

import dev.binclub.bincode.utils.appendMargin

/**
 * @author cookiedragon234 17/Mar/2020
 */
class InsnList(
	private val internalList: MutableList<Insn>
): Iterable<Insn> {
	override fun iterator(): Iterator<Insn> = internalList.iterator()
	override fun toString(): String = """
	|[
	|   ${internalList.joinToString("\n").appendMargin(1)}
	|]""".trimMargin()
	
	operator fun get(index: Int) = internalList[index]
	operator fun set(index: Int, value: Insn) {
		internalList[index] = value
	}
}
