package dev.binclub.bincode.types.attributes.types.code

import dev.binclub.bincode.types.attributes.Attribute
import dev.binclub.bincode.types.attributes.types.code.insns.Insn
import dev.binclub.bincode.types.attributes.types.code.insns.InsnList
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant
import dev.binclub.bincode.utils.appendMargin
import java.util.*

data class CodeAttribute(
		override val name: ConstantPoolReference<Utf8Constant>,
		val maxStack: Int,
		val maxLocals: Int,
		val instructions: InsnList,
		val tryCatchBlocks: Array<TryCatchBlock>,
		val attributes: Array<Attribute>
): Attribute(name) {
	override fun toString() =
		"""CodeAttribute(
		|	maxStack=${maxStack.toString().appendMargin(1)},
		|	maxLocals=${maxLocals.toString().appendMargin(1)},
		|	instructions=${instructions.toString().appendMargin(1)},
		|	tryCatchBlocks=${tryCatchBlocks.contentToString().appendMargin(1)},
		|	attributes=${attributes.contentToString().appendMargin(1)},
		|)""".trimMargin()
}
