package dev.binclub.bincode.types.attributes.types.code

import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.ClassConstant
import dev.binclub.bincode.utils.appendMargin

data class TryCatchBlock(val start: Int, val end: Int, val handler: Int, val catch: ConstantPoolReference<ClassConstant>?) {
	override fun toString() =
		"""TryCatchBlock(
		|	start=${start.toString().appendMargin(1)},
		|	end=${end.toString().appendMargin(1)},
		|	handler=${handler.toString().appendMargin(1)},
		|	catch=${catch.toString().appendMargin(1)},
		|)""".trimMargin()
}
