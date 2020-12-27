import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import static org.junit.Assert.*;

public class StoffVerkaufenCustomTest {
    @Test
    public void custTest__noStdout() {
        /** Tests for unallowed output (e.g. Java-API prohibited) **/
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Execute test here

        assertEquals("This exercise prohibits the use of the Java API. This includes System.out.println(). " +
                "You should remove any such calls before upload!", "", outContent.toString());
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void custTest__noStderr() {
        /** Tests for unallowed output (e.g. Java-API prohibited) **/
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        // Execute test here

        assertEquals("This exercise prohibits the use of the Java API. This includes System.out.println(). " +
                "You should remove any such calls before upload!", "", errContent.toString());
        System.setErr(originalErr);
    }
}
