package dev.binclub.bincode.parsing

import dev.binclub.bincode.types.annotation.Annotation
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.u2
import java.io.DataInput

object AnnotationParser {
	fun parseAnnotations(dataInput: DataInput): Array<Annotation> {
		val numAnnotations = dataInput.u2()
		return Array(numAnnotations) {
			parseAnnotation(dataInput)
		}
	}
	
	fun parseAnnotation(dataInput: DataInput) =
			Annotation(ConstantPoolReference(dataInput.u2()), AnnotationElementPairParser.parsePairs(dataInput))
}
