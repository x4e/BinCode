package dev.binclub.bincode.types.annotation

import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant

data class ClassAnnotationValue(
	val descriptorIndex: ConstantPoolReference<Utf8Constant>
): AnnotationValue() {
	override fun toString(): String = """
		|ClassAnnotationValue($descriptorIndex)
		""".trimMargin()
}
