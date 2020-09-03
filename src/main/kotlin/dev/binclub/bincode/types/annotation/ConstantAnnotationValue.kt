package dev.binclub.bincode.types.annotation

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

data class ConstantAnnotationValue(
	val constantRef: ConstantPoolReference<Constant>
): AnnotationValue() {
	override fun toString(): String = """
		|ConstantAnnotationValue($constantRef)
		""".trimMargin()
}
