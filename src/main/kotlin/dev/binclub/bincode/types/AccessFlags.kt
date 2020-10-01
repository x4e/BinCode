package dev.binclub.bincode.types

import dev.binclub.bincode.types.Access.Companion.classAccesses
import dev.binclub.bincode.types.Access.Companion.fieldAccesses
import dev.binclub.bincode.types.Access.Companion.innerClassAccesses
import dev.binclub.bincode.types.Access.Companion.methodAccesses
import dev.binclub.bincode.utils.asMutable
import java.util.*

enum class Access(
	val flags: Int,
	private val str: String
) {
	ACC_PUBLIC(0x0001, "public"),
	ACC_PRIVATE(0x0002, "private"),
	ACC_PROTECTED(0x0004, "protected"),
	ACC_STATIC(0x0008, "static"),
	ACC_FINAL(0x0010, "final"),
	ACC_SYNCHRONIZED(0x0020, "synchronized"),
	ACC_SUPER(0x0020, "super"),
	ACC_BRIDGE(0x0040, "bridge"),
	ACC_VOLATILE(0x0040, "volatile"),
	ACC_VARARGS(0x0080, "varargs"),
	ACC_TRANSIENT(0x0080, "transient"),
	ACC_NATIVE(0x0100, "native"),
	ACC_INTERFACE(0x0200, "interface"),
	ACC_ABSTRACT(0x0400, "abstract"),
	ACC_STRICT(0x0800, "strictfp"),
	ACC_SYNTHETIC(0x1000, "synthetic"),
	ACC_ANNOTATION(0x2000, "annotation"),
	ACC_ENUM(0x4000, "enum"),
	ACC_MODULE(0x8000, "module"),
	ACC_MANDATED(0x8000, "mandated");
	
	override fun toString(): String = str
	
	companion object {
		val classAccesses = arrayOf(ACC_PUBLIC, ACC_FINAL, ACC_SUPER, ACC_INTERFACE, ACC_ABSTRACT,
			ACC_SYNTHETIC, ACC_ANNOTATION, ACC_ENUM, ACC_MODULE)
		val fieldAccesses = arrayOf(ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL,
			ACC_VOLATILE, ACC_TRANSIENT, ACC_SYNTHETIC, ACC_ENUM)
		val methodAccesses = arrayOf(ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL,
			ACC_SYNCHRONIZED, ACC_BRIDGE, ACC_VARARGS, ACC_NATIVE, ACC_ABSTRACT, ACC_STRICT, ACC_SYNTHETIC)
		val innerClassAccesses = arrayOf(ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL,
			ACC_INTERFACE, ACC_ABSTRACT, ACC_SYNTHETIC, ACC_ANNOTATION, ACC_ENUM)
	}
}

data class AccessFlags(
	val accesses: MutableSet<Access>
) {
	constructor(vararg accesses: Access): this(accesses.toHashSet())
	
	companion object {
		fun parseClass(access: Int): AccessFlags = AccessFlags(collectAccesses(classAccesses, access))
		fun parseField(access: Int): AccessFlags = AccessFlags(collectAccesses(fieldAccesses, access))
		fun parseMethod(access: Int): AccessFlags = AccessFlags(collectAccesses(methodAccesses, access))
		fun parseInnerClass(access: Int): AccessFlags = AccessFlags(collectAccesses(innerClassAccesses, access))
		
		private fun collectAccesses(options: Array<Access>, access: Int): MutableSet<Access> {
			val set = EnumSet.noneOf(Access::class.java)
			options.forEach { option ->
				if (access and option.flags != 0) {
					set += option
				}
			}
			return set
		}
	}
	
	fun backToInt(): Int {
		var out = 0
		accesses.forEach {
			out = out or it.flags
		}
		return out
	}
	
	override fun toString(): String = accesses.joinToString(" ")
}
