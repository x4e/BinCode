package dev.binclub.bincode.types

import dev.binclub.bincode.types.attributes.Attribute
import dev.binclub.bincode.types.constantpool.ConstantPool
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.ClassConstant
import dev.binclub.bincode.utils.appendMargin
import dev.binclub.bincode.utils.toFormattedStr
import kotlin.properties.Delegates

class ClassFile {
	var version: ClassVersion by Delegates.notNull()
	var constantPool: ConstantPool by Delegates.notNull()
	var access: AccessFlags by Delegates.notNull()
	var thisClass: ConstantPoolReference<ClassConstant> by Delegates.notNull()
	var superClass: ConstantPoolReference<ClassConstant> by Delegates.notNull()
	var interfaces: Array<ConstantPoolReference<ClassConstant>> by Delegates.notNull()
	var fields: Array<Field> by Delegates.notNull()
	var methods: Array<Method> by Delegates.notNull()
	var attributes: Array<Attribute> by Delegates.notNull()
	
	override fun toString() =
		"""ClassFile(
		|	constantPool=${constantPool.toString().appendMargin(1)},
		|	access=${access.toString().appendMargin(1)}
		|	thisClass=${thisClass.toString().appendMargin(1)},
		|	superClass=${superClass.toString().appendMargin(1)},
		|	interfaces=${interfaces.toFormattedStr().appendMargin(1)},
		|	fields=${fields.toFormattedStr().appendMargin(1)},
		|	methods=${methods.toFormattedStr().appendMargin(1)},
		|	attributes=${attributes.toFormattedStr().appendMargin(1)}
		|)""".trimMargin()
}
