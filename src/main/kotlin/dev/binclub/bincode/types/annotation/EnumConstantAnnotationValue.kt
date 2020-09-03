package dev.binclub.bincode.types.annotation

import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant

data class EnumConstantAnnotationValue(
	val typeNameIndex: ConstantPoolReference<Utf8Constant>,
	val nameIndex: ConstantPoolReference<Utf8Constant>
): AnnotationValue() {
	override fun toString(): String = """
		|EnumConstantAnnotationValue($typeNameIndex, $nameIndex)
		""".trimMargin()
}
