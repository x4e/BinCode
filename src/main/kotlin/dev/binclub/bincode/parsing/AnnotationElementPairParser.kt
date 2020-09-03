package dev.binclub.bincode.parsing

import dev.binclub.bincode.InvalidClassException
import dev.binclub.bincode.types.annotation.*
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant
import dev.binclub.bincode.u1
import dev.binclub.bincode.u2
import java.io.DataInput
import java.io.DataInputStream

object AnnotationElementPairParser {
	fun parsePairs(dataInput: DataInput): Array<Pair<ConstantPoolReference<Utf8Constant>, AnnotationValue>> {
		val numPairs = dataInput.u2()
		return Array(numPairs) {
			val nameIndex = ConstantPoolReference<Utf8Constant>(dataInput.u2())
			val value = parseValue(dataInput)
			Pair(nameIndex, value)
		}
	}
	
	private fun parseValues(dataInput: DataInput): Array<AnnotationValue> {
		val numValues = dataInput.u2()
		return Array(numValues) {
			parseValue(dataInput)
		}
	}
	
	private fun parseValue(dataInput: DataInput): AnnotationValue {
		return when (val tag = dataInput.u1().toChar()) {
			'B', 'C', 'D', 'F', 'I', 'J', 'S', 'Z', 's' ->
				ConstantAnnotationValue(ConstantPoolReference(dataInput.u2()))
			'e' ->
				EnumConstantAnnotationValue(ConstantPoolReference(dataInput.u2()), ConstantPoolReference(dataInput.u2()))
			'c' ->
				ClassAnnotationValue(ConstantPoolReference(dataInput.u2()))
			'@' ->
				InnerAnnotationAnnotationValue(AnnotationParser.parseAnnotation(dataInput))
			'[' ->
				ArrayAnnotationValue(parseValues(dataInput))
			else ->
				throw InvalidClassException("Unknown Annotation Element Type <$tag> (${tag.toInt()})")
		}
	}
}
