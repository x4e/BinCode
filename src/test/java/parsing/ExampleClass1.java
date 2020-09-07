package parsing;

/**
 * @author cookiedragon234 03/Sep/2020
 */
@Deprecated
@kotlin.Deprecated(message = "hello hello goodbye")
public class ExampleClass1 {
	int a = 2;
	
	@Idk(a = "a", b = @Other(a = 2, b = @kotlin.Deprecated(message = "msg2")), c = @kotlin.Deprecated(message = "msg"))
	private final int b = 3;
	
	void thing() {
		int a = 0;
		/*switch (a) {
			case 0: System.out.println(0);
			case 1: System.out.println(1);
			case 2: System.out.println(2);
		}
		switch (a) {
			case 0:
				System.out.println(0);
			case 3:
				System.out.println(3);
			case 5:
				System.out.println(5);
		}*/
		double b = 0.0102;
		while (true) {
			b += (double)1000L;
			thing();
		}
	}
	
	public @interface Idk {
		String a();
		Other b();
		kotlin.Deprecated c();
	}
	
	public @interface Other {
		int a();
		kotlin.Deprecated b();
	}
	
}
