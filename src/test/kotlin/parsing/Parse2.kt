package parsing

import dev.binclub.bincode.*
import dev.binclub.bincode.types.constantpool.Constant
import dev.binclub.bincode.types.constantpool.ConstantPoolReference
import dev.binclub.bincode.types.constantpool.constants.*
import org.junit.jupiter.api.Test
import java.io.DataInput
import java.io.DataInputStream
import java.lang.Double
import java.lang.Float

/**
 * @author cook 11/Jan/2021
 */
object TestParser {
	@Test
	fun test() {
		val inputStream = DataInputStream(ParsingTest1::class.java.getResourceAsStream("/parsing/ExampleClass1.class"))
		parse(inputStream)
	}
	
	fun parse(inputStream: DataInputStream) {
		if (inputStream.u4() != 0xCAFEBABE.toInt()) {
			throw InvalidClassMagicException()
		}
	}
	
	fun parseConstantPool(dataInput: DataInput): Array<Constant> {
		val size = dataInput.u2()
		// Array of invalid constants by default. We will update indices with parsed constants.
		val constantPool = Array<Constant>(size) { InvalidConstant() }
		
		// index 0 is null, start parsing at index 1
		var index = 1
		while (index < size) {
			val constant = when (val tag = dataInput.u1()) {
				1 -> Utf8Constant(dataInput.utf8())
				3 -> IntegerConstant(dataInput.u4())
				4 -> FloatConstant(Float.intBitsToFloat(dataInput.u4()))
				5 -> LongConstant(dataInput.s8())
				6 -> DoubleConstant(Double.longBitsToDouble(dataInput.s8()))
				7 -> ClassConstant(ConstantPoolReference(dataInput.u2()))
				8 -> StringConstant(ConstantPoolReference(dataInput.u2()))
				9 -> FieldRefConstant(ConstantPoolReference(dataInput.u2()), ConstantPoolReference(dataInput.u2()))
				10 -> MethodRefConstant(ConstantPoolReference(dataInput.u2()), ConstantPoolReference(dataInput.u2()))
				11 -> InterfaceMethodRefConstant(ConstantPoolReference(dataInput.u2()), ConstantPoolReference(dataInput.u2()))
				12 -> NameAndTypeConstant(ConstantPoolReference(dataInput.u2()), ConstantPoolReference(dataInput.u2()))
				15 -> MethodHandleConstant(dataInput.u1(), ConstantPoolReference(dataInput.u2()))
				16 -> MethodTypeConstant(ConstantPoolReference(dataInput.u2()))
				17 -> DynamicConstant(dataInput.u2(), ConstantPoolReference(dataInput.u2()))
				18 -> InvokeDynamicConstant(dataInput.u2(), ConstantPoolReference(dataInput.u2()))
				19 -> ModuleConstant(ConstantPoolReference(dataInput.u2()))
				20 -> PackageConstant(ConstantPoolReference(dataInput.u2()))
				else -> {
					System.err.println("Warning: Invalid constant tag $tag in constant pool at index $index")
					InvalidConstant("Invalid Constant Tag $tag")
				}
			}
			constantPool[index] = constant
			if (constant.isDoubleSize()) {
				// Double size constants take up two indexes
				index += 1
			}
			index += 1
		}
		
		return constantPool
	}
}
