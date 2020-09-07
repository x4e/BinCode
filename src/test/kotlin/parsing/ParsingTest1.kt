package parsing

import dev.binclub.bincode.Bincode
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.DataInputStream
import kotlin.test.*

/**
 * @author cookiedragon234 03/Sep/2020
 */
class ParsingTest1 {
	@Test
	fun test1() {
		val bytes = ParsingTest1::class.java.getResourceAsStream("/parsing/ExampleClass1.class").readBytes()
		val classFile = Bincode(DataInputStream(ByteArrayInputStream(bytes)))
		println(classFile.toString())
		//val expected = ParsingTest1::class.java.getResourceAsStream("/parsing/ExampleClass1.txt").readBytes().toString(Charsets.UTF_8)
		assertEquals("expected", classFile.toString())
	}
}

