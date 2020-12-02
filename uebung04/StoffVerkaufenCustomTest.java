import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class StoffVerkaufenCustomTest {
    private static final long[] PREISLISTE = {1, 5, 8, 9, 10, 17, 17, 20};
    private static final Random RND = new Random(4711_0815_666L);

    @Test
    public void custTest__noStdout() {
        // Tests for unallowed output (e.g. Java-API prohibited)
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        long[] preisePreisliste_gekuerzt = Arrays.copyOf(PREISLISTE, PREISLISTE.length - 1 - RND.nextInt(1));
        StoffVerkaufenPublicTest.check_naive("Verkuerzte PREISLISTE", PREISLISTE.length, preisePreisliste_gekuerzt, 22, preisePreisliste_gekuerzt.length == 7 ? 1278 : 1270);
        // @TODO Add test for DP solution

        assertEquals("This exercise prohibits the use of the Java API. This includes System.out.println(). " +
                "You should remove any such calls before upload!", "", outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void custTest__noStderr() {
        // Tests for unallowed output (e.g. Java-API prohibited)
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        long[] preisePreisliste_gekuerzt = Arrays.copyOf(PREISLISTE, PREISLISTE.length - 1 - RND.nextInt(1));
        StoffVerkaufenPublicTest.check_naive("Verkuerzte PREISLISTE", PREISLISTE.length, preisePreisliste_gekuerzt, 22, preisePreisliste_gekuerzt.length == 7 ? 1278 : 1270);
        // @TODO Add test for DP solution

        assertEquals("This exercise prohibits the use of the Java API. This includes System.out.println(). " +
                "You should remove any such calls before upload!", "", errContent.toString());
        System.setErr(originalErr);
    }
}
