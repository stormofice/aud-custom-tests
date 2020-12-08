import org.junit.Test;

public class VeggieWahnCustomTest extends VeggieWahnPublicTest {

    // == CONFIG ==
    // If this enabled, the test will check values that lead to overflow
    // This is not part of the exercise, but it can be a way to compare whether your solution produces the
    // same result as others
    private final static boolean CHECK_INVALID = true;
    // == CONFIG ==

    @Test(timeout = 100)
    public void customTest_essen_otherVal() {
        test(122, 122, 246);
        test(100, 122, 3038272443324799200L);
        test(2, 122, 2);
        test(2, 112, 2);
        test(1, 1, 4);
        test(34, 55, 1385032271404359464L);
        test(67, 69, 5291928L);
        test(42, 93, 3969071361861713228L);
        test(1, 2, 4);
        test(1, 1, 4);
        test(1, 2, 4);
        test(122, 123, 15250);
        test(1, 123, 4);
        test(123, 123, 248);

        if (CHECK_INVALID) {
            test(100, 123, -6911747045645259816L);
            test(42, 69, -4110211557730688816L);
        }

    }

}
