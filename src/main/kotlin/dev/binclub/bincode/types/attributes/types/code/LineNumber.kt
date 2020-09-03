package dev.binclub.bincode.types.attributes.types.code

import dev.binclub.bincode.utils.appendMargin

data class LineNumber(val start: Int, val lineNumber: Int) {
	override fun toString() =
		"""LineNumber(
		|	start=${start.toString().appendMargin(1)},
		|	lineNumber=${lineNumber.toString().appendMargin(1)}
		|)""".trimMargin()
}
