import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.util.Stack;
import java.util.stream.IntStream;

public class UnendlicheDatenstrukturenCustomTest {

    @Test(timeout = 15000)
    public void customTest_prime() {
        PrimzahlenErzeuger p = new PrimzahlenErzeuger();
        Stack<PrimzahlenErzeuger> primeCreators = new Stack<>();
        for (int i = 0; i < 5000; i++) {
            Assert.assertTrue(isPrime(Integer.parseInt(p.aktuell().toString())));
            primeCreators.push(p);
            p = (PrimzahlenErzeuger) p.nachfolger();
        }

        for (int i = 0; i < 5000; i++) {
            p = (PrimzahlenErzeuger) p.vorgaenger();
            Assert.assertEquals(primeCreators.pop(), p);
        }
    }

    @Test(timeout = 2000)
    public void customTest_collatz() {

        Assert.assertEquals("0", ErzeugerErzeuger.erzeuge(new CollatzVerarbeiter(0, 0)).nachfolger().nachfolger().aktuell().toString());
        Assert.assertEquals("2", ErzeugerErzeuger.erzeuge(new CollatzVerarbeiter(1, 0)).nachfolger().nachfolger().aktuell().toString());

        int[] expOne = new int[]{277, 832, 416, 208, 104, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1};
        test_collatz_termination(277, 0, expOne, 4, 2, 1);

        for (int i = 2; i < 256; i++) {
            test_collatz_termination(i, 0, null, 4, 2, 1);
        }

        for (int i = 1; i < 4; i++) {
            for (int k = 2; k < 256; k++) {
                test_collatz_termination(k, i, null, 108, 54, 27);
            }
        }

    }

    @Test(timeout = 100)
    public void customTest_intervall_callswap() {
        Assert.assertEquals("[1, 3, 5, 7, 9]", Intervall.von(get(1)).mitSchrittweite(get(2)).bisMit(get(9)).toString());
    }

    @Test(timeout = 100)
    public void customTest_intervall_static_values() {
        Assert.assertEquals("[]", Intervall.von(get(0)).bisOhne(get(0)).toString());
        Assert.assertEquals("[0]", Intervall.von(get(0)).bisMit(get(0)).toString());
        Assert.assertEquals("0", Intervall.von(get(0)).bisMit(get(0)).kopf().toString());
        Assert.assertEquals("[]", Intervall.von(get(0)).bisMit(get(0)).rest().toString());
    }

    @Test(timeout = 100)
    public void customTest_intervall_big_values() {
        String highExpected = "[18446744073709551614, 18446744073709551624, 18446744073709551634, 18446744073709551644, 18446744073709551654, 18446744073709551664, 18446744073709551674, 18446744073709551684, 18446744073709551694, 18446744073709551704]";
        Intervall highActual = Intervall.von(getNumberByString("18446744073709551614")).bisOhne(getNumberByString("18446744073709551714")).mitSchrittweite(new RiesigeGanzzahl(10));
        Assert.assertEquals(getNumberByString("18446744073709551614"), highActual.kopf());
        Assert.assertEquals(highExpected, highActual.toString());

        String lowExpected = "[-18446744073709551614, -18446744073709551624, -18446744073709551634, -18446744073709551644, -18446744073709551654, -18446744073709551664, -18446744073709551674, -18446744073709551684, -18446744073709551694, -18446744073709551704]";
        Intervall lowActual = Intervall.von(getNumberByString("-18446744073709551614")).bisOhne(getNumberByString("-18446744073709551714")).mitSchrittweite(new RiesigeGanzzahl(-10));
        Assert.assertEquals(getNumberByString("-18446744073709551614"), lowActual.kopf());
        Assert.assertEquals(lowExpected, lowActual.toString());
    }

    @Test(timeout = 100)
    public void customTest_intervall_zerojump() {
        String lowToHighExpected = "[-10, -7, -4, -1, 2, 5, 8, 11]";
        Assert.assertEquals(lowToHighExpected, Intervall.von(get(-10)).bisMit(get(11)).mitSchrittweite(get(3)).toString());
        String highToLowExpected = "[11, 8, 5, 2, -1, -4, -7, -10]";
        Assert.assertEquals(highToLowExpected, Intervall.von(get(11)).bisMit(get(-10)).mitSchrittweite(get(-3)).toString());

        String lowToHighExpectedWithout = "[-10, -7, -4, -1, 2, 5, 8]";
        Assert.assertEquals(lowToHighExpectedWithout, Intervall.von(get(-10)).bisOhne(get(11)).mitSchrittweite(get(3)).toString());
        String highToLowExpectedWithout = "[11, 8, 5, 2, -1, -4, -7]";
        Assert.assertEquals(highToLowExpectedWithout, Intervall.von(get(11)).bisOhne(get(-10)).mitSchrittweite(get(-3)).toString());
    }

    @Test(timeout = 100)
    public void customTest_intervall_infinity() {
        Assert.assertEquals("[10, ?]", Intervall.von(get(10)).toString());
        Assert.assertEquals("[-10, ?]", Intervall.von(get(-10)).toString());
        Assert.assertEquals("[36893488147419103228, ?]", Intervall.von(getNumberByString("36893488147419103228")).toString());
        Assert.assertEquals("[-36893488147419103228, ?]", Intervall.von(getNumberByString("-36893488147419103228")).toString());

        Assert.assertEquals("[1, ?]", Intervall.von(get(1)).bisMit(get(-1)).toString());

    }

    @Test(timeout = 100)
    public void customTest_intervall_empty() {
        Assert.assertTrue(Intervall.von(get(0)).bisOhne(get(0)).istLeer());
        Assert.assertFalse(Intervall.von(get(0)).bisMit(get(0)).istLeer());
        Assert.assertFalse(Intervall.von(get(0)).mitSchrittweite(get(2)).istLeer());
        Assert.assertFalse(Intervall.von(get(0)).mitSchrittweite(get(-2)).istLeer());
        Assert.assertFalse(Intervall.von(get(0)).mitSchrittweite(get(Integer.MAX_VALUE)).istLeer());
        Assert.assertFalse(Intervall.von(get(0)).mitSchrittweite(get(Integer.MIN_VALUE)).istLeer());
    }

    @Test(timeout = 100)
    public void customTest_intervall_error() {
        // Check for memory leak (saving interval as array/list/etc)
        Intervall t = Intervall.von(getNumberByString("-36893488147419103228")).bisMit(getNumberByString("36893488147419103228"));
        Assert.assertEquals(getNumberByString("-36893488147419103228"), t.kopf());

        try {
            Intervall.von(null);
            Assert.fail("exception exp");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

        try {
            Intervall.von(get(0)).bisMit(null);
            Assert.fail("exception exp");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

        try {
            Intervall.von(get(0)).bisOhne(null);
            Assert.fail("exception exp");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

        try {
            Intervall.von(get(0)).bisMit(get(1)).mitSchrittweite(null);
            Assert.fail("exception exp");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

        try {
            Intervall.von(get(0)).bisMit(get(1)).mitSchrittweite(RiesigeGanzzahl.NULL);
            Assert.fail("exception exp");
        } catch (Exception e) {
            Assert.assertSame(IllegalArgumentException.class, e.getClass());
        }

    }

    private boolean isPrime(int number) {
        return number > 1
                && IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(n -> (number % n == 0));
    }


    private void test_collatz_termination(int n, int x, int[] expected, int t1, int t2, int t3) {
        Erzeuger<RiesigeGanzzahl> c = ErzeugerErzeuger.erzeuge(new CollatzVerarbeiter(n, x));
        int[] exp = expected;

        int flags = 0;

        for (int i = 0; i < 1000; i++) {

            RiesigeGanzzahl cur = c.aktuell();

            if (i == 999) {
                Assert.fail(n + "," + x + "," + cur.toString());
            }

            if (flags == 0) {
                if (cur.equals(get(t1))) {
                    flags++;
                } else {
                    flags = 0;
                }
            } else if (flags == 1) {
                if (cur.equals(get(t2))) {
                    flags++;
                } else {
                    flags = 0;
                }
            } else if (flags == 2) {
                if (cur.equals(get(t3))) {
                    break;
                } else {
                    flags = 0;
                }
            }

            if (exp != null)
                Assert.assertEquals(exp[i] + "", cur.toString());
            c = c.nachfolger();
        }
    }

    private RiesigeGanzzahl getNumberByString(String g) {
        try {
            Class<?> clazz = RiesigeGanzzahl.class;
            Constructor<RiesigeGanzzahl> cons = (Constructor<RiesigeGanzzahl>) clazz.getDeclaredConstructor(BigInteger.class);
            cons.setAccessible(true);
            RiesigeGanzzahl r = cons.newInstance(new BigInteger(g));
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("?");
        return null;
    }

    private RiesigeGanzzahl get(int n) {
        return new RiesigeGanzzahl(n);
    }

}