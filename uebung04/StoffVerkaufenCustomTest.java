import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

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
     * Tests for unallowed output (e.g. Java-API prohibited)
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

    @Test(timeout = 4200)
    public void customTest_verkaufenNaive() {
        long[] preise_custom = {};
        check_naive("custom_1", -1, preise_custom, 0, 1);
        check_naive("custom_2", 0, preise_custom, 0, 1);

        preise_custom = new long[]{1};
        check_naive("custom_3", -1, preise_custom, 0, 1);
        check_naive("custom_4", 0, preise_custom, 0, 1);
        check_naive("custom_5", 1, preise_custom, 1, 3);
        check_naive("custom_6", 420, preise_custom, 420, 88831);

        preise_custom = new long[]{0};
        check_naive("custom_7", -1, preise_custom, 0, 1);
        check_naive("custom_8", 0, preise_custom, 0, 1);
        check_naive("custom_9", 1, preise_custom, 0, 3);
        check_naive("custom_10", 420, preise_custom, 0, 88831);
    }

    @Test(timeout = 420)
    public void customTest_verkaufenDP() {
        long[] preise_custom = {};
        check_dp("custom_11", -1, preise_custom, 0);
        check_dp("custom_12", 0, preise_custom, 0);
        check_dp("custom_13", 1, preise_custom, 0);
        check_dp("custom_14", 420, preise_custom, 0);

        preise_custom = new long[]{1};
        check_dp("custom_15", -1, preise_custom, 0);
        check_dp("custom_16", 0, preise_custom, 0);
        check_dp("custom_17", 1, preise_custom, 1);
        check_dp("custom_18", 420, preise_custom, 420);

        preise_custom = new long[]{0};
        check_dp("custom_19", -1, preise_custom, 0);
        check_dp("custom_20", 0, preise_custom, 0);
        check_dp("custom_21", 1, preise_custom, 0);
        check_dp("custom_22", 420, preise_custom, 0);
    }

}
