package dev.binclub.bincode.parsing

import dev.binclub.bincode.*
import dev.binclub.bincode.parsing.attributes.AttributeParser
import dev.binclub.bincode.parsing.attributes.AttributeSource.CLASS
import dev.binclub.bincode.types.AccessFlags
import dev.binclub.bincode.types.ClassFile
import dev.binclub.bincode.types.ClassVersion
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.utils.toHex
import java.io.DataInput

object ClassFileParser {
	private const val CLASS_MAGIC = 0xCAFEBABE.toInt()
	
	/**
	 * Parses the entirety of a class file using the given data input
	 */
	@Throws(InvalidClassMagicException::class, ClassParseException::class)
	fun parse(dataInput: DataInput): ClassFile {
		val magic = dataInput.u4()
		if (magic != CLASS_MAGIC) {
			throw InvalidClassMagicException("Invalid magic ${magic.toHex()}, expected ${CLASS_MAGIC.toHex()}")
		}
		val classFile = ClassFile()
		
		try {
			classFile.version = ClassVersion(dataInput.u2(), dataInput.u2())
			classFile.constantPool = ConstantPoolParser.parse(dataInput)
			classFile.access = AccessFlags.parseClass(dataInput.u2())
			classFile.thisClass = ConstantPoolReference(dataInput.u2())
			classFile.superClass = ConstantPoolReference(dataInput.u2())
			classFile.interfaces = InterfaceParser.parseInterfaces(dataInput)
			classFile.fields = FieldsParser.parseFields(classFile.version, dataInput, classFile.constantPool)
			classFile.methods = MethodParser.parseMethods(classFile.version, dataInput, classFile.constantPool)
			classFile.attributes = AttributeParser.parseAttributes(CLASS, classFile.version, dataInput, classFile.constantPool)
		} catch (t: Throwable) {
			val version = try { classFile.version } catch (t: Throwable) { null } ?.toString() ?: "unknown"
			val className = try { classFile.thisClass[classFile.constantPool].nameRef[classFile.constantPool].value } catch (t: Throwable) { null } ?: "unknown"
			throw ClassParseException("Couldn't parse class [$className] of version [$version]", t)
		}
		
		return classFile
	}
	
	/**
	 * Returns a partial classfile only containing the minimal header
	 */
	@Throws(InvalidClassMagicException::class, ClassParseException::class)
	fun parseClassHeader(dataInput: DataInput): ClassFile {
		val magic = dataInput.u4()
		if (magic != CLASS_MAGIC) {
			throw InvalidClassMagicException("Invalid magic ${magic.toHex()}, expected ${CLASS_MAGIC.toHex()}")
		}
		val classFile = ClassFile()
		
		try {
			classFile.version = ClassVersion(dataInput.u2(), dataInput.u2())
			classFile.constantPool = ConstantPoolParser.parse(dataInput)
			classFile.access = AccessFlags.parseClass(dataInput.u2())
			classFile.thisClass = ConstantPoolReference(dataInput.u2())
			classFile.superClass = ConstantPoolReference(dataInput.u2())
			classFile.interfaces = InterfaceParser.parseInterfaces(dataInput)
		} catch (t: Throwable) {
			val version = try { classFile.version } catch (t: Throwable) { null } ?.toString() ?: "unknown"
			val className = try { classFile.thisClass[classFile.constantPool].nameRef[classFile.constantPool].value } catch (t: Throwable) { null } ?: "unknown"
			throw ClassParseException("Couldn't parse class [$className] of version [$version]", t)
		}
		
		return classFile
	}
}
