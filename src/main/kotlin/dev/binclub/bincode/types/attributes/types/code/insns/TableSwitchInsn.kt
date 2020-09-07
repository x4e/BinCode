package dev.binclub.bincode.types.attributes.types.code.insns

import dev.binclub.bincode.types.attributes.types.code.Opcode

/**
 * @author cookiedragon234 04/Sep/2020
 */


/**
 * A tableswitch is a variable-length instruction. Immediately after the tableswitch opcode, between zero and three
 * bytes must act as padding, such that defaultbyte1 begins at an address that is a multiple of four bytes from the
 * start of the current method (the opcode of its first instruction). Immediately after the padding are bytes
 * constituting three signed 32-bit values: default, low, and high. Immediately following are bytes constituting a
 * series of high - low + 1 signed 32-bit offsets. The value low must be less than or equal to high.
 * The high - low + 1 signed 32-bit offsets are treated as a 0-based jump table. Each of these signed 32-bit values is
 * constructed as (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4.
 *
 * The index must be of type int and is popped from the operand stack. If index is less than low or index is greater
 * than high, then a target address is calculated by adding default to the address of the opcode of this tableswitch
 * instruction. Otherwise, the offset at position index - low of the jump table is extracted. The target address is
 * calculated by adding that offset to the address of the opcode of this tableswitch instruction. Execution then
 * continues at the target address.
 *
 * The target address that can be calculated from each jump table offset, as well as the one that can be calculated
 * from default, must be the address of an opcode of an instruction within the method that contains this tableswitch
 * instruction.
 */
data class TableSwitchInsn(
	override val opcode: Opcode,
	val default: Int,
	val low: Int,
	val high: Int,
	// NOTE: These are relative to the position of this insn
	val offsets: IntArray
): Insn(opcode) {
	override fun toString(): String = buildString {
		append("$opcode(\n")
		append("\tdflt: $default\n")
		offsets.forEachIndexed { index, i ->
			append("\t${index + low}: $i\n")
		}
		append(")")
	}
}
