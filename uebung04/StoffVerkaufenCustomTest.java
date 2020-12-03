import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class StoffVerkaufenCustomTest extends StoffVerkaufenPublicTest {
    /**
     * Tests for unallowed output (e.g. Java-API prohibited)
     */
    @Test
    public void customTest__noStdout() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        pubTest__verkaufenNaive();
        pubTest__verkaufenDP();

        assertEquals("This exercise prohibits the use of the Java API. This includes System.out.println(). " +
                "You should remove any such calls before upload!", "", outContent.toString());
        System.setOut(originalOut);
    }

    /**
     *  Tests for unallowed output (e.g. Java-API prohibited)
     */
    @Test
    public void customTest__noStderr() {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        pubTest__verkaufenNaive();
        pubTest__verkaufenDP();

        assertEquals("This exercise prohibits the use of the Java API. This includes System.out.println(). " +
                "You should remove any such calls before upload!", "", errContent.toString());
        System.setErr(originalErr);
    }
}
