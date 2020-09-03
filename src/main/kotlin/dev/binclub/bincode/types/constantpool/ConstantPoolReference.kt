package dev.binclub.bincode.types.constantpool

import dev.binclub.bincode.types.ClassFile

data class ConstantPoolReference<T: Constant>(val index: Int) {
	operator fun get(constantPool: ConstantPool): T = constantPool[index] as T
	operator fun get(classFile: ClassFile) = this[classFile.constantPool]
	fun resolve(constantPool: ConstantPool) = this[constantPool]
	fun resolve(classFile: ClassFile) = this[classFile]
	
	override fun toString(): String = "CP#$index"
}
