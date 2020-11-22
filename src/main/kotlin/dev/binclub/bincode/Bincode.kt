package dev.binclub.bincode

import dev.binclub.bincode.parsing.ClassFileParser
import dev.binclub.bincode.types.ClassFile
import java.io.*
import java.util.jar.JarFile
import java.util.logging.Logger

object Bincode {
	val LOGGER: Logger = Logger.getLogger("BinCode")
	
	operator fun invoke(file: File) = parse(file)
	operator fun invoke(input: DataInput) = parse(input)
	
	fun parse(file: File): Array<ClassFile>
		= when {
			file.isDirectory -> error("Expected a file, found a directory")
			file.extension == "class" -> {
				arrayOf(parse(RandomAccessFile(file, "r")))
			}
			file.extension == "jar" || file.extension == "zip" -> {
				JarFile(file).use { jar ->
					val out = ArrayList<ClassFile>(jar.size())
					jar.entries().iterator().forEach { entry ->
						if (entry.name.endsWith(".class")) {
							out += parse(DataInputStream(jar.getInputStream(entry)))
						}
					}
					out.toTypedArray()
				}
			}
			else -> error("Unrecognised file extension [${file.extension}]")
		}
	
	fun parse(input: DataInput): ClassFile {
		return ClassFileParser.parse(input)
	}
	
	@JvmStatic
	fun main(args: Array<String>) {
		if (args.isEmpty()) {
			error("Please provide a file")
		}
		
		parse(File(args[0])).forEach(::println)
	}
}
