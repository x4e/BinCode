package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference

/**
 * @author cook 22/Nov/2020
 */
data class ModuleConstant(
	val nameIndex: ConstantPoolReference<Utf8Constant>
): Constant() {
	override fun toString(): String =
		"Module: (NameIndex: $nameIndex)"
}
