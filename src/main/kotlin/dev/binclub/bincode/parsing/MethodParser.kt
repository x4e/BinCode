package dev.binclub.bincode.parsing

import dev.binclub.bincode.parsing.attributes.AttributeParser
import dev.binclub.bincode.parsing.attributes.AttributeSource
import dev.binclub.bincode.parsing.attributes.AttributeSource.METHOD
import dev.binclub.bincode.types.AccessFlags
import dev.binclub.bincode.types.ClassVersion
import dev.binclub.bincode.types.Method
import dev.binclub.bincode.types.constantpool.ConstantPool
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.u2
import java.io.DataInput
import java.io.DataInputStream

object MethodParser {
	fun parseMethods(version: ClassVersion, dataInput: DataInput, constantPool: ConstantPool): Array<Method> {
		val numMethods = dataInput.u2()
		return Array(numMethods) {
			parseMethod(version, dataInput, constantPool)
		}
	}
	
	fun parseMethod(version: ClassVersion, dataInput: DataInput, constantPool: ConstantPool): Method {
		return Method(
			AccessFlags.parseMethod(dataInput.u2()),
			ConstantPoolReference(dataInput.u2()),
			ConstantPoolReference(dataInput.u2()),
			AttributeParser.parseAttributes(METHOD, version, dataInput, constantPool)
		)
	}
}
