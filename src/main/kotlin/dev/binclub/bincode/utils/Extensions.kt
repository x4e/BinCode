@file:Suppress("NOTHING_TO_INLINE")

package dev.binclub.bincode.utils

import java.io.DataInput
import java.io.File
import java.io.RandomAccessFile

fun String.appendMargin(num: Int): String {
	val margins = "\t".repeat(num)
	return this.replace("\n", "\n$margins")
}

fun String.replaceButLast(oldValue: String, newValue: String, ignoreCase: Boolean = false): String {
	val index = lastIndexOf(oldValue, ignoreCase = ignoreCase)
	return if (index < 0) this else this.substring(0, index).replace(oldValue, newValue) + this.substring(index)
}

fun String.replaceButLast(oldChar: Char, newChar: Char, ignoreCase: Boolean = false): String {
	val index = lastIndexOf(oldChar, ignoreCase = ignoreCase)
	return if (index < 0) this else this.substring(0, index).replace(oldChar, newChar) + this.substring(index)
}

inline fun <T> List<T>.asMutable(): MutableList<T> {
	return if (this is MutableList) this
	else ArrayList(this)
}

fun <T> Array<out T>?.toFormattedStr(): String {
	if (this == null) {
		return "null"
	} else {
		val iMax = this.size - 1
		if (iMax == -1) {
			return "[]"
		} else {
			val b = StringBuilder()
			b.append("[\n\t")
			var i = 0
			
			while (true) {
				b.append(this[i].toString().appendMargin(1))
				if (i == iMax) {
					return b.append("\n\t]").toString()
				}
				
				b.append(",\n\t")
				++i
			}
		}
	}
}

fun <T> List<T>?.toFormattedStr(): String {
	if (this == null) {
		return "null"
	} else {
		val iMax = this.size - 1
		if (iMax == -1) {
			return "[]"
		} else {
			val b = StringBuilder()
			b.append("[\n\t")
			var i = 0
			
			while (true) {
				b.append(this[i].toString().appendMargin(1))
				if (i == iMax) {
					return b.append("\n]").toString()
				}
				
				b.append(",\n\t")
				++i
			}
		}
	}
}

fun <T> constructArrayList(size: Int, init: (index: Int) -> T): ArrayList<T> {
	return ArrayList<T>(size).also {
		for (i in 0 until size) {
			it.add(init(i))
		}
	}
}

fun String.escapeString(): String = buildString (this.length) {
	for (char in this@escapeString) {
		val ci = char.toInt()
		when {
			ci == 0x0008 -> append("\\b")
			ci == 0x0009 -> append("\\t")
			ci == 0x000a -> append("\\n")
			ci == 0x000c -> append("\\f")
			ci == 0x000d -> append("\\r")
			ci == 0x005c -> append("\\\\")
			(char != ' ' && char.isWhitespace()) -> append("\\${ci.toString(16)}")
			else -> append(char)
		}
	}
}

inline fun Int.toHex(): String = "0x${Integer.toHexString(this)}"
inline fun Long.toHex(): String = "0x${java.lang.Long.toHexString(this)}"

inline fun <reified T: Any> Any?.cast(): T = this as T
