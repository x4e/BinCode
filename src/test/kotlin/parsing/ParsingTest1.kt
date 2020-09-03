package parsing

import dev.binclub.bincode.BingaitParser
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.DataInput
import java.io.DataInputStream
import java.io.File
import java.time.Duration
import java.time.Instant
import kotlin.test.*

/**
 * @author cookiedragon234 03/Sep/2020
 */
class ParsingTest1 {
	@Test
	fun test1() {
		val bytes = BingaitParser::class.java.getResourceAsStream("/parsing/ExampleClass1.class").readBytes()
		val classFile = BingaitParser(DataInputStream(ByteArrayInputStream(bytes)))
		println(classFile.toString())
		assertEquals(expected, classFile.toString())
	}
	
	val expected = """ClassFile(
	constantPool=[
		#0: 	[Empty Constant Index]
		#1: 	MethodRef: (Class: CP#6, NameAndType: CP#41)
		#2: 	FieldRef: (Clazz: CP#5, NameAndType: CP#42)
		#3: 	FieldRef: (Clazz: CP#5, NameAndType: CP#43)
		#4: 	MethodRef: (Class: CP#5, NameAndType: CP#44)
		#5: 	ClassConstant: CP#45
		#6: 	ClassConstant: CP#46
		#7: 	ClassConstant: CP#47
		#8: 	Utf8: Other
		#9: 	Utf8: InnerClasses
		#10: 	ClassConstant: CP#48
		#11: 	Utf8: Idk
		#12: 	Utf8: a
		#13: 	Utf8: I
		#14: 	Utf8: b
		#15: 	Utf8: ConstantValue
		#16: 	Integer: 3
		#17: 	Utf8: RuntimeInvisibleAnnotations
		#18: 	Utf8: Lparsing/ExampleClass1${"$"}Idk;
		#19: 	Utf8: Lparsing/ExampleClass1${"$"}Other;
		#20: 	Integer: 2
		#21: 	Utf8: Lkotlin/Deprecated;
		#22: 	Utf8: message
		#23: 	Utf8: msg2
		#24: 	Utf8: c
		#25: 	Utf8: msg
		#26: 	Utf8: <init>
		#27: 	Utf8: ()V
		#28: 	Utf8: Code
		#29: 	Utf8: LineNumberTable
		#30: 	Utf8: LocalVariableTable
		#31: 	Utf8: this
		#32: 	Utf8: Lparsing/ExampleClass1;
		#33: 	Utf8: thing
		#34: 	Utf8: StackMapTable
		#35: 	Utf8: SourceFile
		#36: 	Utf8: ExampleClass1.java
		#37: 	Utf8: Deprecated
		#38: 	Utf8: RuntimeVisibleAnnotations
		#39: 	Utf8: Ljava/lang/Deprecated;
		#40: 	Utf8: hello hello goodbye
		#41: 	NameAndType: (Name: CP#26, Descriptor: CP#27)
		#42: 	NameAndType: (Name: CP#12, Descriptor: CP#13)
		#43: 	NameAndType: (Name: CP#14, Descriptor: CP#13)
		#44: 	NameAndType: (Name: CP#33, Descriptor: CP#27)
		#45: 	Utf8: parsing/ExampleClass1
		#46: 	Utf8: java/lang/Object
		#47: 	Utf8: parsing/ExampleClass1${"$"}Other
		#48: 	Utf8: parsing/ExampleClass1${"$"}Idk],
	access=super public
	thisClass=CP#5,
	superClass=CP#6,
	interfaces=[],
	fields=[
		Field(
			accessFlags=,
			name=CP#12,
			descriptor=CP#13,
			attributes=[]
		),
		Field(
			accessFlags=final private,
			name=CP#14,
			descriptor=CP#13,
			attributes=[ConstantValueAttribute(CP#16), RuntimeInvisibleAnnotationsAttribute(
				annotations=[
					Annotation(
					   type=CP#18
						pairs=[
							(CP#12, ConstantAnnotationValue(CP#12)),
							(CP#14, InnerAnnotationAnnotationValue(Annotation(
							   type=CP#19
								pairs=[
									(CP#12, ConstantAnnotationValue(CP#20)),
									(CP#14, InnerAnnotationAnnotationValue(Annotation(
									   type=CP#21
										pairs=[
											(CP#22, ConstantAnnotationValue(CP#23))
											]
									)))
									]
							))),
							(CP#24, InnerAnnotationAnnotationValue(Annotation(
							   type=CP#21
								pairs=[
									(CP#22, ConstantAnnotationValue(CP#25))
									]
							)))
							]
					)
					]
			)]
		)
		],
	methods=[
		Method(
			accessFlags=public,
			name=CP#26,
			descriptor=CP#27,
			attributes=[CodeAttribute(
				maxStack=2,
				maxLocals=1,
				instructions=[
				   ALOAD_0
					INVOKESPECIAL(CP#1)
					ALOAD_0
					ICONST_2
					PUTFIELD(CP#2)
					ALOAD_0
					ICONST_3
					PUTFIELD(CP#3)
					RETURN
				],
				tryCatchBlocks=[],
				attributes=[LineNumberTableAttribute(
					lineNumbers=[
						LineNumber(
							start=0,
							lineNumber=8
						),
						LineNumber(
							start=4,
							lineNumber=9
						),
						LineNumber(
							start=9,
							lineNumber=11
						)
						]
				), LocalVariableTableAttribute(
					variables=[
						LocalVariable(
							start=0,
							length=15,
							nameRef=CP#31,
							descriptorRef=CP#32,
							index=0
						)
						]
				)],
			)]
		),
		Method(
			accessFlags=,
			name=CP#33,
			descriptor=CP#27,
			attributes=[CodeAttribute(
				maxStack=1,
				maxLocals=1,
				instructions=[
				   ALOAD_0
					INVOKEVIRTUAL(CP#4)
					GOTO(-4)
				],
				tryCatchBlocks=[],
				attributes=[LineNumberTableAttribute(
					lineNumbers=[
						LineNumber(
							start=0,
							lineNumber=16
						)
						]
				), LocalVariableTableAttribute(
					variables=[
						LocalVariable(
							start=0,
							length=7,
							nameRef=CP#31,
							descriptorRef=CP#32,
							index=0
						)
						]
				), UnknownAttribute(StackMapTable)],
			)]
		)
		],
	attributes=[
		SourceFileAttribute(CP#36),
		DeprecatedAttribute(CP#37),
		RuntimeVisibleAnnotationsAttribute(
			annotations=[
				Annotation(
				   type=CP#39
					pairs=[]
				),
				Annotation(
				   type=CP#21
					pairs=[
						(CP#22, ConstantAnnotationValue(CP#40))
						]
				)
				]
		),
		InnerClassesAttribute([
				InnerClassInfo(innerClassInfoIndex=CP#7, outerClassInfoIndex=CP#5, innerNameIndex=CP#8, innerClassAccessFlags=static abstract interface annotation public),
				InnerClassInfo(innerClassInfoIndex=CP#10, outerClassInfoIndex=CP#5, innerNameIndex=CP#11, innerClassAccessFlags=static abstract interface annotation public)
			])
		]
)""".trimIndent()
}

