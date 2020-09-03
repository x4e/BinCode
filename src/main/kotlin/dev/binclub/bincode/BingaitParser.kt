package dev.binclub.bincode

import dev.binclub.bincode.parsing.ClassFileParser
import dev.binclub.bincode.types.ClassFile
import java.io.*
import java.util.logging.Logger

object BingaitParser {
	val LOGGER: Logger = Logger.getLogger("BinCode")
	
	operator fun invoke(file: File) = parse(file)
	operator fun invoke(input: DataInput) = parse(input)
	
	fun parse(file: File) = parse(RandomAccessFile(file, "r"))
	fun parse(input: DataInput): ClassFile {
		return ClassFileParser.parse(input)
	}
	
	@JvmStatic
	fun main(args: Array<String>) {
		println(BingaitParser(File(args[0])))
	}
}
