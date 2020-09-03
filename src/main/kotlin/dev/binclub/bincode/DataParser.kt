@file:Suppress("NOTHING_TO_INLINE")

package dev.binclub.bincode

import java.io.DataInput

inline fun DataInput.u1(): Int = readUnsignedByte()
inline fun DataInput.s1(): Int = readByte().toInt()
inline fun DataInput.u2(): Int = readUnsignedShort()
inline fun DataInput.s2(): Int = readShort().toInt()
inline fun DataInput.u4(): Int = readInt()
inline fun DataInput.s8(): Long {
	val highBytes = readInt().toLong()
	val lowBytes = readInt().toLong() and 0xFFFFFFFFL
	return (highBytes shl 32) or lowBytes
}
inline fun DataInput.utf8(): String = this.readUTF()
inline fun DataInput.bytes(length: Int): ByteArray
	= ByteArray(length).also {
		this.readFully(it)
	}
inline fun DataInput.skip(length: Int) = this.skipBytes(length)
