package dev.binclub.bincode.parsing

import dev.binclub.bincode.parsing.attributes.AttributeParser
import dev.binclub.bincode.parsing.attributes.AttributeSource
import dev.binclub.bincode.parsing.attributes.AttributeSource.FIELD
import dev.binclub.bincode.types.AccessFlags
import dev.binclub.bincode.types.ClassVersion
import dev.binclub.bincode.types.Field
import dev.binclub.bincode.types.constantpool.ConstantPool
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.u2
import java.io.DataInput
import java.io.DataInputStream

object FieldsParser {
	fun parseFields(version: ClassVersion, dataInput: DataInput, constantPool: ConstantPool): Array<Field> {
		val numFields = dataInput.u2()
		return Array(numFields) {
			Field(
				AccessFlags.parseField(dataInput.u2()),
				ConstantPoolReference(dataInput.u2()),
				ConstantPoolReference(dataInput.u2()),
				AttributeParser.parseAttributes(FIELD, version, dataInput, constantPool)
			)
		}
	}
}
