package dev.binclub.bincode.types

import dev.binclub.bincode.types.attributes.Attribute
import dev.binclub.bincode.types.attributes.types.code.CodeAttribute
import dev.binclub.bincode.types.attributes.types.code.insns.InsnList
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant
import dev.binclub.bincode.utils.appendMargin
import dev.binclub.bincode.utils.cast

data class Method(
	val accessFlags: AccessFlags,
	val name: ConstantPoolReference<Utf8Constant>,
	val descriptor: ConstantPoolReference<Utf8Constant>,
	val attributes: Array<Attribute>
) {
	val code: CodeAttribute?
		get() = attributes.firstOrNull { it is CodeAttribute }?.cast()
	val instructions: InsnList?
		get() = code?.instructions
	
	override fun toString(): String = """
		|Method(
		|	accessFlags=${accessFlags.toString().appendMargin(1)},
		|	name=${name.toString().appendMargin(1)},
		|	descriptor=${descriptor.toString().appendMargin(1)},
		|	attributes=${attributes.contentToString().appendMargin(1)}
		|)""".trimMargin()
}
