package dev.binclub.bincode.types.constantpool

abstract class Constant() {
	open fun isDoubleSize() = false
}

abstract class PrimitiveConstant<T: Any>: Constant() {
	abstract var value: T
}
