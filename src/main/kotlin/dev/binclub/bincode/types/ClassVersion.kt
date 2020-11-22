@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package dev.binclub.bincode.types

/**
 * @author cookiedragon234 21/Jun/2020
 */
data class ClassVersion(val minor: Int, val major: Int) {
	fun isAtLeast(other: ClassVersion): Boolean {
		if (this.major > other.major) return true
		else if (this.major == other.major && this.minor >= other.minor) return true
		return false
	}
	fun isBefore(other: ClassVersion): Boolean {
		if (this.major < other.major) return true
		else if (this.major == other.major && this.minor < other.minor) return true
		return false
	}
	
	override fun toString(): String =
		"Version ${names[major]} ($minor)"
	
	companion object {
		val V1_1 = ClassVersion(0, 0x2D)
		val V1_2 = ClassVersion(0, 0x2E)
		val V1_3 = ClassVersion(0, 0x2F)
		val V1_4 = ClassVersion(0, 0x30)
		val V5 = ClassVersion(0, 0x31)
		val V6 = ClassVersion(0, 0x32)
		val V7 = ClassVersion(0, 0x33)
		val V8 = ClassVersion(0, 0x34)
		val V9 = ClassVersion(0, 0x35)
		val V10 = ClassVersion(0, 0x36)
		val V11 = ClassVersion(0, 0x37)
		val V12 = ClassVersion(0, 0x38)
		val V13 = ClassVersion(0, 0x39)
		val V14 = ClassVersion(0, 0x3A)
		val V15 = ClassVersion(0, 0x3B)
		
		val names = hashMapOf(
			V1_1.major to "1.1",
			V1_2.major to "1.2",
			V1_3.major to "1.3",
			V1_4.major to "1.4",
			V5.major to "5",
			V6.major to "6",
			V7.major to "7",
			V8.major to "8",
			V9.major to "9",
			V10.major to "10",
			V11.major to "11",
			V12.major to "12",
			V13.major to "13",
			V14.major to "14",
			V15.major to "15"
		)
	}
}
