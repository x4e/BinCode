package dev.binclub.bincode.types.annotation

import dev.binclub.bincode.utils.toFormattedStr

data class ArrayAnnotationValue(
	val array: Array<AnnotationValue>
): AnnotationValue() {
	override fun toString(): String = """
		|ArrayAnnotationValue(${array.toFormattedStr()})
		""".trimMargin()
}
