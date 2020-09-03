package dev.binclub.bincode.types.attributes.types.code

import dev.binclub.bincode.utils.toHex

@Suppress("unused", "SpellCheckingInspection")
enum class Opcode(open val num: Int) {
	INVALID(-1),
	NOP(0x00),              // Perform no operation
	ACONST_NULL(0x01),      // Push a null reference onto the stack
	ICONST_M1(0x02),        // Load the int value −1 onto the stack
	ICONST_0(0x03),         // Load the int value 0 onto the stack
	ICONST_1(0x04),         // Load the int value 1 onto the stack
	ICONST_2(0x05),         // Load the int value 2 onto the stack
	ICONST_3(0x06),         // Load the int value 3 onto the stack
	ICONST_4(0x07),         // Load the int value 4 onto the stack
	ICONST_5(0x08),         // Load the int value 5 onto the stack
	LCONST_0(0x09),         // Push 0L (the number zero with type long) onto the stack
	LCONST_1(0x0a),         // Push 1L (the number one with type long) onto the stack
	FCONST_0(0x0b),         // Push 0.0f on the stack
	FCONST_1(0x0c),         // Push 1.0f on the stack
	FCONST_2(0x0d),         // Push 2.0f on the stack
	DCONST_0(0x0e),         // Push the constant 0.0 (a double) onto the stack
	DCONST_1(0x0f),         // Push the constant 1.0 (a double) onto the stack
	BIPUSH(0x10),           // Push a byte onto the stack as an integer value
	SIPUSH(0x11),           // Push a short onto the stack as an integer value
	LDC(0x12),              // Push a constant #index from a constant pool (String, int, float, Class, java.lang.invoke.MethodType, java.lang.invoke.MethodHandle, or a dynamically-computed constant) onto the stack
	LDC_W(0x13),            // Push a constant #index from a constant pool (String, int, float, Class, java.lang.invoke.MethodType, java.lang.invoke.MethodHandle, or a dynamically-computed constant) onto the stack (wide index is constructed as indexbyte1 << 8 + indexbyte2)
	LDC2_W(0x14),           // Push a constant #index from a constant pool (double, long, or a dynamically-computed constant) onto the stack (wide index is constructed as indexbyte1 << 8 + indexbyte2)
	ILOAD(0x15),            // Load an int value from a local variable #index
	LLOAD(0x16),            // Load a long value from a local variable #index
	FLOAD(0x17),            // Load a float value from a local variable #index
	DLOAD(0x18),            // Load a double value from a local variable #index
	ALOAD(0x19),            // Load a reference onto the stack from a local variable #index
	ILOAD_0(0x1a),          // Load an int value from local variable 0
	ILOAD_1(0x1b),          // Load an int value from local variable 1
	ILOAD_2(0x1c),          // Load an int value from local variable 2
	ILOAD_3(0x1d),          // Load an int value from local variable 3
	LLOAD_0(0x1e),          // Load a long value from a local variable 0
	LLOAD_1(0x1f),          // Load a long value from a local variable 1
	LLOAD_2(0x20),          // Load a long value from a local variable 2
	LLOAD_3(0x21),          // Load a long value from a local variable 3
	FLOAD_0(0x22),          // Load a float value from local variable 0
	FLOAD_1(0x23),          // Load a float value from local variable 1
	FLOAD_2(0x24),          // Load a float value from local variable 2
	FLOAD_3(0x25),          // Load a float value from local variable 3
	DLOAD_0(0x26),          // Load a double from local variable 0
	DLOAD_1(0x27),          // Load a double from local variable 1
	DLOAD_2(0x28),          // Load a double from local variable 2
	DLOAD_3(0x29),          // Load a double from local variable 3
	ALOAD_0(0x2a),          // Load a reference onto the stack from local variable 0
	ALOAD_1(0x2b),          // Load a reference onto the stack from local variable 1
	ALOAD_2(0x2c),          // Load a reference onto the stack from local variable 2
	ALOAD_3(0x2d),          // Load a reference onto the stack from local variable 3
	IALOAD(0x2e),           // Load an int from an array
	LALOAD(0x2f),           // Load a long from an array
	FALOAD(0x30),           // Load a float from an array
	DALOAD(0x31),           // Load a double from an array
	AALOAD(0x32),           // Load onto the stack a reference from an array
	BALOAD(0x33),           // Load a byte or Boolean value from an array
	CALOAD(0x34),           // Load a char from an array
	SALOAD(0x35),           // Load short from array
	ISTORE(0x36),           // Store int value into variable #index
	LSTORE(0x37),           // Store a long value in a local variable #index
	FSTORE(0x38),           // Store a float value into a local variable #index
	DSTORE(0x39),           // Store a double value into a local variable #index
	ASTORE(0x3a),           // Store a reference into a local variable #index
	ISTORE_0(0x3b),         // Store int value into variable 0
	ISTORE_1(0x3c),         // Store int value into variable 1
	ISTORE_2(0x3d),         // Store int value into variable 2
	ISTORE_3(0x3e),         // Store int value into variable 3
	LSTORE_0(0x3f),         // Store a long value in a local variable 0
	LSTORE_1(0x40),         // Store a long value in a local variable 1
	LSTORE_2(0x41),         // Store a long value in a local variable 2
	LSTORE_3(0x42),         // Store a long value in a local variable 3
	FSTORE_0(0x43),         // Store a float value into local variable 0
	FSTORE_1(0x44),         // Store a float value into local variable 1
	FSTORE_2(0x45),         // Store a float value into local variable 2
	FSTORE_3(0x46),         // Store a float value into local variable 3
	DSTORE_0(0x47),         // Store a double into local variable 0
	DSTORE_1(0x48),         // Store a double into local variable 1
	DSTORE_2(0x49),         // Store a double into local variable 2
	DSTORE_3(0x4a),         // Store a double into local variable 3
	ASTORE_0(0x4b),         // Store a reference into local variable 0
	ASTORE_1(0x4c),         // Store a reference into local variable 1
	ASTORE_2(0x4d),         // Store a reference into local variable 2
	ASTORE_3(0x4e),         // Store a reference into local variable 3
	IASTORE(0x4f),          // Store an int into an array
	LASTORE(0x50),          // Store a long to an array
	FASTORE(0x51),          // Store a float in an array
	DASTORE(0x52),          // Store a double into an array
	AASTORE(0x53),          // Store a reference in an array
	BASTORE(0x54),          // Store a byte or Boolean value into an array
	CASTORE(0x55),          // Store a char into an array
	SASTORE(0x56),          // Store short to array
	POP(0x57),              // Discard the top value on the stack
	POP2(0x58),             // Discard the top two values on the stack (or one value, if it is a double or long)
	DUP(0x59),              // Duplicate the value on top of the stack
	DUP_X1(0x5a),           // Insert a copy of the top value into the stack two values from the top. value1 and value2 must not be of the type double or long.
	DUP_X2(0x5b),           // Insert a copy of the top value into the stack two (if value2 is double or long it takes up the entry of value3, too) or three values (if value2 is neither double nor long) from the top
	DUP2(0x5c),             // Duplicate top two stack words (two values, if value1 is not double nor long; a single value, if value1 is double or long)
	DUP2_X1(0x5d),          // Duplicate two words and insert beneath third word (see explanation above)
	DUP2_X2(0x5e),          // Duplicate two words and insert beneath fourth word
	SWAP(0x5f),             // Swaps two top words on the stack (note that value1 and value2 must not be double or long)
	IADD(0x60),             // Add two ints
	LADD(0x61),             // Add two longs
	FADD(0x62),             // Add two floats
	DADD(0x63),             // Add two doubles
	ISUB(0x64),             // Int subtract
	LSUB(0x65),             // Subtract two longs
	FSUB(0x66),             // Subtract two floats
	DSUB(0x67),             // Subtract a double from another
	IMUL(0x68),             // Multiply two integers
	LMUL(0x69),             // Multiply two longs
	FMUL(0x6a),             // Multiply two floats
	DMUL(0x6b),             // Multiply two doubles
	IDIV(0x6c),             // Divide two integers
	LDIV(0x6d),             // Divide two longs
	FDIV(0x6e),             // Divide two floats
	DDIV(0x6f),             // Divide two doubles
	IREM(0x70),             // Logical int remainder
	LREM(0x71),             // Remainder of division of two longs
	FREM(0x72),             // Get the remainder from a division between two floats
	DREM(0x73),             // Get the remainder from a division between two doubles
	INEG(0x74),             // Negate int
	LNEG(0x75),             // Negate a long
	FNEG(0x76),             // Negate a float
	DNEG(0x77),             // Negate a double
	ISHL(0x78),             // Int shift left
	LSHL(0x79),             // Bitwise shift left of a long value1 by int value2 positions
	ISHR(0x7a),             // Int arithmetic shift right
	LSHR(0x7b),             // Bitwise shift right of a long value1 by int value2 positions
	IUSHR(0x7c),            // Int logical shift right
	LUSHR(0x7d),            // Bitwise shift right of a long value1 by int value2 positions, unsigned
	IAND(0x7e),             // Perform a bitwise AND on two integers
	LAND(0x7f),             // Bitwise AND of two longs
	IOR(0x80),              // Bitwise int OR
	LOR(0x81),              // Bitwise OR of two longs
	IXOR(0x82),             // Int xor
	LXOR(0x83),             // Bitwise XOR of two longs
	IINC(0x84),             // Increment local variable #index by signed byte const
	I2L(0x85),              // Convert an int into a long
	I2F(0x86),              // Convert an int into a float
	I2D(0x87),              // Convert an int into a double
	L2I(0x88),              // Convert a long to a int
	L2F(0x89),              // Convert a long to a float
	L2D(0x8a),              // Convert a long to a double
	F2I(0x8b),              // Convert a float to an int
	F2L(0x8c),              // Convert a float to a long
	F2D(0x8d),              // Convert a float to a double
	D2I(0x8e),              // Convert a double to an int
	D2L(0x8f),              // Convert a double to a long
	D2F(0x90),              // Convert a double to a float
	I2B(0x91),              // Convert an int into a byte
	I2C(0x92),              // Convert an int into a character
	I2S(0x93),              // Convert an int into a short
	LCMP(0x94),             // Push 0 if the two longs are the same, 1 if value1 is greater than value2, -1 otherwise
	FCMPL(0x95),            // Compare two floats
	FCMPG(0x96),            // Compare two floats
	DCMPL(0x97),            // Compare two doubles
	DCMPG(0x98),            // Compare two doubles
	IFEQ(0x99),             // If value is 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IFNE(0x9a),             // If value is not 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IFLT(0x9b),             // If value is less than 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IFGE(0x9c),             // If value is greater than or equal to 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IFGT(0x9d),             // If value is greater than 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IFLE(0x9e),             // If value is less than or equal to 0, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IF_ICMPEQ(0x9f),        // If ints are equal, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IF_ICMPNE(0xa0),        // If ints are not equal, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IF_ICMPLT(0xa1),        // If value1 is less than value2, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IF_ICMPGE(0xa2),        // If value1 is greater than or equal to value2, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IF_ICMPGT(0xa3),        // If value1 is greater than value2, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IF_ICMPLE(0xa4),        // If value1 is less than or equal to value2, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IF_ACMPEQ(0xa5),        // If references are equal, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IF_ACMPNE(0xa6),        // If references are not equal, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	GOTO(0xa7),             // Goes to another instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	JSR(0xa8),              // Jump to subroutine at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) and place the return address on the stack
	RET(0xa9),              // Continue execution from address taken from a local variable #index (the asymmetry with jsr is intentional)
	TABLESWITCH(0xaa),      // Continue execution from an address in the table at offset index
	LOOKUPSWITCH(0xab),     // A target address is looked up from a table using a key and execution continues from the instruction at that address
	IRETURN(0xac),          // Return an integer from a method
	LRETURN(0xad),          // Return a long value
	FRETURN(0xae),          // Return a float
	DRETURN(0xaf),          // Return a double from a method
	ARETURN(0xb0),          // Return a reference from a method
	RETURN(0xb1),           // Return void from method
	GETSTATIC(0xb2),        // Get a static field value of a class, where the field is identified by field reference in the constant pool index (indexbyte1 << 8 + indexbyte2)
	PUTSTATIC(0xb3),        // Set static field to value in a class, where the field is identified by a field reference index in constant pool (indexbyte1 << 8 + indexbyte2)
	GETFIELD(0xb4),         // Get a field value of an object objectref, where the field is identified by field reference in the constant pool index (indexbyte1 << 8 + indexbyte2)
	PUTFIELD(0xb5),         // Set field to value in an object objectref, where the field is identified by a field reference index in constant pool (indexbyte1 << 8 + indexbyte2)
	INVOKEVIRTUAL(0xb6),    // Invoke virtual method on object objectref and puts the result on the stack (might be void); the method is identified by method reference index in constant pool (indexbyte1 << 8 + indexbyte2)
	INVOKESPECIAL(0xb7),    // Invoke instance method on object objectref and puts the result on the stack (might be void); the method is identified by method reference index in constant pool (indexbyte1 << 8 + indexbyte2)
	INVOKESTATIC(0xb8),     // Invoke a static method and puts the result on the stack (might be void); the method is identified by method reference index in constant pool (indexbyte1 << 8 + indexbyte2)
	INVOKEINTERFACE(0xb9),  // Invokes an interface method on object objectref and puts the result on the stack (might be void); the interface method is identified by method reference index in constant pool (indexbyte1 << 8 + indexbyte2)
	INVOKEDYNAMIC(0xba),    // Invokes a dynamic method and puts the result on the stack (might be void); the method is identified by method reference index in constant pool (indexbyte1 << 8 + indexbyte2)
	NEW(0xbb),              // Create new object of type identified by class reference in constant pool index (indexbyte1 << 8 + indexbyte2)
	NEWARRAY(0xbc),         // Create new array with count elements of primitive type identified by atype
	ANEWARRAY(0xbd),        // Create a new array of references of length count and component type identified by the class reference index (indexbyte1 << 8 + indexbyte2) in the constant pool
	ARRAYLENGTH(0xbe),      // Get the length of an array
	ATHROW(0xbf),           // Throws an error or exception (notice that the rest of the stack is cleared, leaving only a reference to the Throwable)
	CHECKCAST(0xc0),        // Checks whether an objectref is of a certain type, the class reference of which is in the constant pool at index (indexbyte1 << 8 + indexbyte2)
	INSTANCEOF(0xc1),       // Determines if an object objectref is of a given type, identified by class reference index in constant pool (indexbyte1 << 8 + indexbyte2)
	MONITORENTER(0xc2),     // Enter monitor for object ("grab the lock" – start of synchronized() section)
	MONITOREXIT(0xc3),      // Exit monitor for object ("release the lock" – end of synchronized() section)
	WIDE(0xc4),             // Execute opcode, where opcode is either iload, fload, aload, lload, dload, istore, fstore, astore, lstore, dstore, or ret, but assume the index is 16 bit; or execute iinc, where the index is 16 bits and the constant to increment by is a signed 16 bit short
	MULTIANEWARRAY(0xc5),   // Create a new array of dimensions dimensions of type identified by class reference in constant pool index (indexbyte1 << 8 + indexbyte2); the sizes of each dimension is identified by count1, [count2, etc.]
	IFNULL(0xc6),           // If value is null, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	IFNONNULL(0xc7),        // If value is not null, branch to instruction at branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2)
	GOTO_W(0xc8),           // Goes to another instruction at branchoffset (signed int constructed from unsigned bytes branchbyte1 << 24 + branchbyte2 << 16 + branchbyte3 << 8 + branchbyte4)
	JSR_W(0xc9),            // Jump to subroutine at branchoffset (signed int constructed from unsigned bytes branchbyte1 << 24 + branchbyte2 << 16 + branchbyte3 << 8 + branchbyte4) and place the return address on the stack
	BREAKPOINT(0xca),       // Reserved for breakpoints in Java debuggers; should not appear in any class file
	IMPDEP1(0xfe),          // Reserved for implementation-dependent operations within debuggers; should not appear in any class file
	IMPDEP2(0xff);          // Reserved for implementation-dependent operations within debuggers; should not appear in any class file
	
	
	companion object {
		private val cachedValues = values()
		private val map = HashMap<Int, Opcode>(cachedValues.size).also {
			cachedValues.forEach { op ->
				it[op.num] = op
			}
		}
		
		fun maybeOpcodeOf(opcode: Int): Opcode? = map[opcode]
		
		fun opcodeOf(opcode: Int): Opcode
			= map[opcode] ?: throw IllegalArgumentException("Invalid Opcode ${opcode.toHex()}")
	}
	
	override fun toString(): String {
		return when (this) {
			INVALID -> num.toHex()
			else -> name
		}
	}
}
