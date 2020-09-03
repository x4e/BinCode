package dev.binclub.bincode.types.constantpool

import dev.binclub.bincode.utils.appendMargin
import dev.binclub.bincode.utils.constructArrayList

class ConstantPool(size: Int, dflt: Constant): MutableList<Constant> {
	//val constantPool: MutableList<Constant>
	val constantPool: MutableList<Constant> = constructArrayList(size){dflt}
	
	override fun toString(): String {
		val builder = StringBuilder("[")
		for ((index, constant) in constantPool.withIndex()) {
			builder.append("\n\t#$index: \t${constant.toString().appendMargin(2)}")
		}
		return builder.append(']').toString()
	}
	
	override val size: Int
		get() = constantPool.size
	override operator fun get(index: Int) = constantPool[index]
	override operator fun set(index: Int, element: Constant): Constant = constantPool.set(index, element)
	override operator fun iterator(): MutableIterator<Constant> = constantPool.iterator()
	override fun contains(element: Constant): Boolean = constantPool.contains(element)
	override fun containsAll(elements: Collection<Constant>): Boolean = constantPool.containsAll(elements)
	override fun indexOf(element: Constant): Int = constantPool.indexOf(element)
	override fun isEmpty(): Boolean = constantPool.isEmpty()
	override fun lastIndexOf(element: Constant): Int = constantPool.lastIndexOf(element)
	override fun add(element: Constant): Boolean = constantPool.add(element)
	override fun add(index: Int, element: Constant) = constantPool.add(index, element)
	override fun addAll(index: Int, elements: Collection<Constant>): Boolean = constantPool.addAll(index, elements)
	override fun addAll(elements: Collection<Constant>): Boolean = constantPool.addAll(elements)
	override fun clear() = constantPool.clear()
	override fun listIterator(): MutableListIterator<Constant> = constantPool.listIterator()
	override fun listIterator(index: Int): MutableListIterator<Constant> = constantPool.listIterator(index)
	override fun remove(element: Constant): Boolean = constantPool.remove(element)
	override fun removeAll(elements: Collection<Constant>): Boolean = constantPool.removeAll(elements)
	override fun removeAt(index: Int): Constant = constantPool.removeAt(index)
	override fun retainAll(elements: Collection<Constant>): Boolean = constantPool.retainAll(elements)
	override fun subList(fromIndex: Int, toIndex: Int): MutableList<Constant> = constantPool.subList(fromIndex, toIndex)
}
