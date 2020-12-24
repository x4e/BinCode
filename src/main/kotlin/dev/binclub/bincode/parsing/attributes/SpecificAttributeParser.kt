package dev.binclub.bincode.parsing.attributes

import dev.binclub.bincode.parsing.AnnotationParser
import dev.binclub.bincode.parsing.attributes.AttributeSource.*
import dev.binclub.bincode.types.AccessFlags
import dev.binclub.bincode.types.ClassVersion
import dev.binclub.bincode.types.attributes.*
import dev.binclub.bincode.types.attributes.types.code.CodeAttribute
import dev.binclub.bincode.types.attributes.types.code.LineNumber
import dev.binclub.bincode.types.attributes.types.code.LocalVariable
import dev.binclub.bincode.types.attributes.types.code.TryCatchBlock
import dev.binclub.bincode.types.attributes.types.code.insns.InsnList
import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPool
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.ClassConstant
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant
import dev.binclub.bincode.u2
import dev.binclub.bincode.utils.constructArrayList
import java.io.DataInput

/**
 * @author cookiedragon234 21/Jun/2020
 */
abstract class SpecificAttributeParser<T: Attribute>(val name: String) {
	abstract fun canParse(source: AttributeSource, version: ClassVersion?): Boolean
	abstract fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool): T
}

object ConstantValueParser: SpecificAttributeParser<ConstantValueAttribute>("ConstantValue") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == FIELD) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool)
		= ConstantValueAttribute(nameRef, ConstantPoolReference<Constant>(dataInput.u2()))
}

object SyntheticParser: SpecificAttributeParser<SyntheticAttribute>("Synthetic") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == CLASS || source == FIELD || source == METHOD) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool)
		= SyntheticAttribute(nameRef)
}

object DeprecatedParser: SpecificAttributeParser<DeprecatedAttribute>("Deprecated") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == CLASS || source == FIELD || source == METHOD) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool)
		= DeprecatedAttribute(nameRef)
}

object RuntimeVisibleAnnotationsParser: SpecificAttributeParser<RuntimeVisibleAnnotationsAttribute>("RuntimeVisibleAnnotations") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == CLASS || source == FIELD || source == METHOD) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool)
	= RuntimeVisibleAnnotationsAttribute(nameRef, AnnotationParser.parseAnnotations(dataInput))
}

object RuntimeInvisibleAnnotationsParser: SpecificAttributeParser<RuntimeInvisibleAnnotationsAttribute>("RuntimeInvisibleAnnotations") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == CLASS || source == FIELD || source == METHOD) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool)
	= RuntimeInvisibleAnnotationsAttribute(nameRef, AnnotationParser.parseAnnotations(dataInput))
}

object RuntimeVisibleTypeAnnotationsParser: SpecificAttributeParser<RuntimeVisibleTypeAnnotationsAttribute>("RuntimeVisibleTypeAnnotations") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == CLASS || source == FIELD || source == METHOD || source == CODE) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool)
	= RuntimeVisibleTypeAnnotationsAttribute(nameRef, AnnotationParser.parseAnnotations(dataInput))
}

object RuntimeInvisibleTypeAnnotationsParser: SpecificAttributeParser<RuntimeInvisibleTypeAnnotationsAttribute>("RuntimeInvisibleTypeAnnotations") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == CLASS || source == FIELD || source == METHOD || source == CODE) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool)
	= RuntimeInvisibleTypeAnnotationsAttribute(nameRef, AnnotationParser.parseAnnotations(dataInput))
}

object SignatureParser: SpecificAttributeParser<SignatureAttribute>("Signature") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == CLASS || source == FIELD || source == METHOD) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool)
	= SignatureAttribute(nameRef, ConstantPoolReference(dataInput.u2()))
}

object LocalVariableTableParser: SpecificAttributeParser<LocalVariableTableAttribute>("LocalVariableTable") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == CODE) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool): LocalVariableTableAttribute {
		val numVars = dataInput.u2()
		return LocalVariableTableAttribute(nameRef, Array(numVars) {
			val start = dataInput.u2()
			val length = dataInput.u2()
			val varNameRef = ConstantPoolReference<Utf8Constant>(dataInput.u2())
			val descriptorRef = ConstantPoolReference<Utf8Constant>(dataInput.u2())
			val index = dataInput.u2()
			LocalVariable(start, length, varNameRef, descriptorRef, index)
		})
	}
}

object LineNumberTableParser: SpecificAttributeParser<LineNumberTableAttribute>("LineNumberTable") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == CODE) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool): LineNumberTableAttribute {
		val numLines = dataInput.u2()
		return LineNumberTableAttribute(nameRef, Array(numLines) {
			val start = dataInput.u2()
			val lineNum = dataInput.u2()
			LineNumber(start, lineNum)
		})
	}
}

object SourceFileParser: SpecificAttributeParser<SourceFileAttribute>("SourceFile") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == CLASS) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool)
		= SourceFileAttribute(nameRef, ConstantPoolReference(dataInput.u2()))
}

object InnerClassesParser: SpecificAttributeParser<InnerClassesAttribute>("InnerClasses") {
	override fun canParse(source: AttributeSource, version: ClassVersion?) =
		(source == CLASS) && version.atLeast(ClassVersion.V5)
	
	override fun parse(source: AttributeSource, version: ClassVersion?, nameRef: ConstantPoolReference<Utf8Constant>, dataInput: DataInput, constantPool: ConstantPool): InnerClassesAttribute {
		return InnerClassesAttribute(nameRef, constructArrayList(dataInput.u2()) {
			InnerClassInfo(
				ConstantPoolReference(dataInput.u2()),
				ConstantPoolReference(dataInput.u2()),
				ConstantPoolReference(dataInput.u2()),
				AccessFlags.parseInnerClass(dataInput.u2())
			)
		})
	}
}

private inline fun ClassVersion?.atLeast(version: ClassVersion) = this?.isAtLeast(version) ?: true
