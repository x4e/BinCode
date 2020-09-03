package dev.binclub.bincode.types.constantpool

abstract class PrimitiveConstant<T: Any>: Constant() {
	abstract var value: T
}
