package dev.binclub.bincode.types.attributes.types.code

import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.Utf8Constant
import dev.binclub.bincode.utils.appendMargin

data class LocalVariable(val start: Int, val length: Int, val nameRef: ConstantPoolReference<Utf8Constant>, val descriptorRef: ConstantPoolReference<Utf8Constant>, val index: Int) {
	override fun toString() =
			"""LocalVariable(
		|	start=${start.toString().appendMargin(1)},
		|	length=${length.toString().appendMargin(1)},
		|	nameRef=${nameRef.toString().appendMargin(1)},
		|	descriptorRef=${descriptorRef.toString().appendMargin(1)},
		|	index=${index.toString().appendMargin(1)}
		|)""".trimMargin()
}
