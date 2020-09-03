package dev.binclub.bincode.types.annotation

import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant
import dev.binclub.bincode.utils.appendMargin
import dev.binclub.bincode.utils.toFormattedStr

data class Annotation(
	val typeIndex: ConstantPoolReference<Utf8Constant>,
	val pairs: Array<Pair<ConstantPoolReference<Utf8Constant>, AnnotationValue>>
) {
	override fun toString() = """
		|Annotation(
		|   type=$typeIndex
		|	pairs=${pairs.toFormattedStr().appendMargin(1)}
		|)""".trimMargin()
}
