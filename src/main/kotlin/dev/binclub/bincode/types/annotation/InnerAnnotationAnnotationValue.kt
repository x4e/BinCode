package dev.binclub.bincode.types.annotation

data class InnerAnnotationAnnotationValue(val annotation: Annotation): AnnotationValue() {
	override fun toString(): String = """
		|InnerAnnotationAnnotationValue($annotation)
		""".trimMargin()
}
