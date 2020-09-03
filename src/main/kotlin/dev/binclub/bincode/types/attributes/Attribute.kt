package dev.binclub.bincode.types.attributes

import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant

open class Attribute(open val name: ConstantPoolReference<Utf8Constant>) {
	override fun toString(): String = """
		|Attribute($name)
	""".trimMargin()
}
