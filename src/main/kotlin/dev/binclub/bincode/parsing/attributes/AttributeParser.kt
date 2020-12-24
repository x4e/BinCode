package dev.binclub.bincode.parsing.attributes

import dev.binclub.bincode.skip
import dev.binclub.bincode.types.ClassVersion
import dev.binclub.bincode.types.attributes.*
import dev.binclub.bincode.types.constantpool.ConstantPool
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant
import dev.binclub.bincode.u2
import dev.binclub.bincode.u4
import java.io.DataInput

/**
 * @author cookiedragon234 21/Jun/2020
 */
object AttributeParser {
	/**
	 * If version is null then all attributes will be assumed to be supported. Otherwise only attributes declared in
	 * the specified class file version will be parsed.
	 */
	fun parseAttributes(source: AttributeSource, version: ClassVersion?, dataInput: DataInput, constantPool: ConstantPool): Array<Attribute> {
		val numAttributes = dataInput.u2()
		return Array(numAttributes) {
			parseAttribute(source, version, dataInput, constantPool)
		}
	}
	
	private val attributeParsers: Map<String, SpecificAttributeParser<*>>
	
	init {
		val parsers = arrayOf(
			CodeParser,
			ConstantValueParser,
			SyntheticParser,
			DeprecatedParser,
			RuntimeVisibleAnnotationsParser,
			RuntimeInvisibleAnnotationsParser,
			RuntimeVisibleTypeAnnotationsParser,
			RuntimeInvisibleTypeAnnotationsParser,
			SignatureParser,
			LocalVariableTableParser,
			LineNumberTableParser,
			SourceFileParser,
			InnerClassesParser
		)
		attributeParsers = HashMap(parsers.size)
		parsers.forEach {
			attributeParsers[it.name] = it
		}
	}
	
	fun parseAttribute(source: AttributeSource, version: ClassVersion?, dataInput: DataInput, constantPool: ConstantPool): Attribute {
		val nameIndex = dataInput.u2()
		val nameRef = ConstantPoolReference<Utf8Constant>(nameIndex)
		val length = dataInput.u4()
		val name = nameRef[constantPool].value
		val parser = attributeParsers[name]
		return if (parser != null && parser.canParse(source, version)) {
			parser.parse(source, version, nameRef, dataInput, constantPool)
		} else {
			dataInput.skip(length)
			UnknownAttribute(name, nameRef, length)
		}
	}
}
