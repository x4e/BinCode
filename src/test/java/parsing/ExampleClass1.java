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
        thing();
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
