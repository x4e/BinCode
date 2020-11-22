package dev.binclub.bincode.types.constantpool.constants

import dev.binclub.bincode.types.constantpool.Constant

/**
 * @author cook 02/Oct/2020
 */
class InvalidConstant(val message: String? = null): Constant() {
	override fun toString(): String = message ?: "[Empty Constant Index]"
}
