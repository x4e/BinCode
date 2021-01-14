@file:Suppress("ArrayInDataClass")

package dev.binclub.bincode.types.attributes

import dev.binclub.bincode.types.AccessFlags
import dev.binclub.bincode.types.annotation.Annotation
import dev.binclub.bincode.types.attributes.types.code.LineNumber
import dev.binclub.bincode.types.attributes.types.code.LocalVariable
import dev.binclub.bincode.types.constantpool.ConstantPool
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.PrimitiveConstant
import dev.binclub.bincode.types.constantpool.constants.ClassConstant
import dev.binclub.bincode.types.constantpool.constants.StringConstant
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant
import dev.binclub.bincode.utils.appendMargin
import dev.binclub.bincode.utils.toFormattedStr

data class UnknownAttribute(
	val unknownName: String,
	val nameIndex: ConstantPoolReference<Utf8Constant>,
	val length: Int
): Attribute(nameIndex) {
	override fun toString(): String = "UnknownAttribute($unknownName)"
}

data class DeprecatedAttribute(
	override val name: ConstantPoolReference<Utf8Constant>
): Attribute(name) {
	override fun toString(): String = "DeprecatedAttribute($name)"
}

data class LineNumberTableAttribute(
	override val name: ConstantPoolReference<Utf8Constant>,
	val lineNumbers: Array<LineNumber>
): Attribute(name) {
	override fun toString() = """
		|LineNumberTableAttribute(
		|	lineNumbers=${lineNumbers.toFormattedStr().appendMargin(1)}
		|)""".trimMargin()
}

data class LocalVariableTableAttribute(
	override val name: ConstantPoolReference<Utf8Constant>,
	val variables: Array<LocalVariable>
): Attribute(name) {
	override fun toString() = """
		|LocalVariableTableAttribute(
		|	variables=${variables.toFormattedStr().appendMargin(1)}
		|)""".trimMargin()
}

data class RuntimeInvisibleAnnotationsAttribute(
	override val name: ConstantPoolReference<Utf8Constant>,
	val annotations: Array<Annotation>
): Attribute(name) {
	override fun toString() = """
		|RuntimeInvisibleAnnotationsAttribute(
		|	annotations=${annotations.toFormattedStr().appendMargin(1)}
		|)""".trimMargin()
}

data class RuntimeInvisibleTypeAnnotationsAttribute(
	override val name: ConstantPoolReference<Utf8Constant>,
	val annotations: Array<Annotation>
): Attribute(name) {
	override fun toString() = """
		|RuntimeInvisibleTypeAnnotationsAttribute(
		|	annotations=${annotations.toFormattedStr().appendMargin(1)}
		|)""".trimMargin()
}

data class RuntimeVisibleAnnotationsAttribute(
	override val name: ConstantPoolReference<Utf8Constant>,
	val annotations: Array<Annotation>
): Attribute(name) {
	override fun toString() = """
		|RuntimeVisibleAnnotationsAttribute(
		|	annotations=${annotations.toFormattedStr().appendMargin(1)}
		|)""".trimMargin()
}

data class RuntimeVisibleTypeAnnotationsAttribute(
	override val name: ConstantPoolReference<Utf8Constant>,
	val annotations: Array<Annotation>
): Attribute(name) {
	override fun toString() = """
		|RuntimeVisibleTypeAnnotationsAttribute(
		|	annotations=${annotations.toFormattedStr().appendMargin(1)}
		|)""".trimMargin()
}

data class SignatureAttribute(
	override val name: ConstantPoolReference<Utf8Constant>,
	val signatureIndex: ConstantPoolReference<Utf8Constant>
): Attribute(name) {
	override fun toString(): String = "SignatureAttribute($signatureIndex)"
}

data class SyntheticAttribute(
	override val name: ConstantPoolReference<Utf8Constant>
): Attribute(name) {
	override fun toString(): String = "SyntheticAttribute($name)"
}

data class ConstantValueAttribute(
	override val name: ConstantPoolReference<Utf8Constant>,
	val constantIndex: ConstantPoolReference<*>
) : Attribute(name) {
	operator fun get(constantPool: ConstantPool): Any {
		val constant = constantPool[constantIndex.index]
		if (constant is StringConstant) {
			return constant.stringRef[constantPool].value
		}
		constant as PrimitiveConstant<*>
		return constant.value
	}
	
	override fun toString(): String {
		return "ConstantValueAttribute($constantIndex)"
	}
}

data class SourceFileAttribute(
	override val name: ConstantPoolReference<Utf8Constant>,
	val sourceFileIndex: ConstantPoolReference<Utf8Constant>
) : Attribute(name) {
	operator fun get(constantPool: ConstantPool): String {
		return sourceFileIndex[constantPool].value
	}
	
	override fun toString(): String {
		return "SourceFileAttribute($sourceFileIndex)"
	}
}

data class InnerClassInfo(
	val innerClassInfoIndex: ConstantPoolReference<ClassConstant>,
	val outerClassInfoIndex: ConstantPoolReference<ClassConstant>,
	val innerNameIndex: ConstantPoolReference<Utf8Constant>,
	val innerClassAccessFlags: AccessFlags
)

data class InnerClassesAttribute(
	override val name: ConstantPoolReference<Utf8Constant>,
	val classes: MutableList<InnerClassInfo>
) : Attribute(name) {
	override fun toString(): String = """
		|InnerClassesAttribute(${classes.toFormattedStr().appendMargin(1)})
		""".trimMargin()
}
