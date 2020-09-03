package dev.binclub.bincode.types

import dev.binclub.bincode.types.attributes.Attribute
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant
import dev.binclub.bincode.utils.appendMargin
import java.util.*

data class Field(
	val accessFlags: AccessFlags,
	val name: ConstantPoolReference<Utf8Constant>,
	val descriptor: ConstantPoolReference<Utf8Constant>,
	val attributes: Array<Attribute>
) {
	override fun toString(): String = """
		|Field(
		|	accessFlags=${accessFlags.toString().appendMargin(1)},
		|	name=${name.toString().appendMargin(1)},
		|	descriptor=${descriptor.toString().appendMargin(1)},
		|	attributes=${attributes.contentToString().appendMargin(1)}
		|)""".trimMargin()
}
