import org.junit.Test;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UnimodaleSucheCustomTest {
    private static class LongDing {
        protected Long ding;

        private LongDing(Long ding) {
            this.ding = ding;
        }

        @Override
        public String toString() {
            return null;
        }
    }

    private static class LongDingComparable extends LongDing implements Comparable<LongDingComparable> {
        private LongDingComparable(Long ding) {
            super(ding);
        }

        @Override
        public int compareTo(LongDingComparable that) {
            return this.ding.compareTo(that.ding);
        }
    }

    private static class LongDingComparator extends LongDing {
        private LongDingComparator(Long ding) {
            super(ding);
        }
    }

    private static final Comparator<LongDingComparator> CLDC = new Comparator<LongDingComparator>() {
        @Override
        public int compare(LongDingComparator that, LongDingComparator other) {
            return that.ding.compareTo(other.ding);
        }
    };

    // ==================== Liste(n) ====================
    private static class UnimodaleListeLongDing<T> implements UnimodaleListe<T> {
        private final ArrayList<T> all = new ArrayList<>();

        private static UnimodaleListeLongDing<LongDingComparator> createComparator(int... dings) {
            UnimodaleListeLongDing<LongDingComparator> list = new UnimodaleListeLongDing<>();
            for (int ding : dings) {
                list.all.add(new LongDingComparator((long) ding));
            }
            return list;
        }

        private static UnimodaleListeLongDing<LongDingComparable> createComparable(int... dings) {
            UnimodaleListeLongDing<LongDingComparable> list = new UnimodaleListeLongDing<>();
            for (int ding : dings) {
                list.all.add(new LongDingComparable((long) ding));
            }
            return list;
        }

        @Override
        public T hole(int index) {
            StackTraceElement[] st = Thread.currentThread().getStackTrace();
            assertTrue("Die Anzahl der (rekursiven) Aufrufe stimmt nicht (der Stacktrace ist zu kurz).", st.length >= 2);
            assertEquals("Der vorletzte Aufruf sollten an uns gehen...", "hole", st[1].getMethodName());
            int newRZ = 0;
            for (StackTraceElement ste : st) {
                if (ste.getClassName().equals("UnimodaleSuche") && ste.getMethodName().equals("suche")) {
                    newRZ++;
                }
            }
            rZ = rZ > newRZ ? rZ : newRZ;
            zZ++;
            return index < all.size() ? all.get(index) : null;
        }

        private long zZ = 0, rZ = 0; // Zugriffszaehler, Rekursionstiefenzaehler
    }

    @Test
    public void customTest_intestines() {
        Class<UnimodaleSuche> clazz = UnimodaleSuche.class;

        //class
        assertTrue(clazz + " has to be public", Modifier.isPublic(clazz.getModifiers()));
        assertFalse(clazz + " shouldn't be static", Modifier.isStatic(clazz.getModifiers()));
        assertFalse(clazz + " shouldn't be final", Modifier.isFinal(clazz.getModifiers()));
        assertFalse(clazz + " shouldn't be abstract", Modifier.isAbstract(clazz.getModifiers()));
        assertFalse(clazz + " shouldn't be an interface", Modifier.isInterface(clazz.getModifiers()));
        assertFalse(clazz + " shouldn't be an enum", clazz.isEnum());
        assertSame(clazz + " shouldn't inherit any class", Object.class, clazz.getSuperclass());
        assertEquals(clazz + " shouldn't implement an interface", 0, clazz.getInterfaces().length);
        assertEquals(clazz + " shouldn't have inner classes", 0, getDeclaredClasses(clazz).length);
        assertEquals(clazz + " shouldn't have any annotations", 0, clazz.getDeclaredAnnotations().length);

        //attributes
        Field[] fields = getDeclaredFields(clazz);
        assertEquals(clazz + " shouldn't have any attributes", 0, fields.length);

        //constructor
        Constructor<?>[] cons = getDeclaredConstructors(clazz);
        assertEquals(clazz + " should only have an implicit constructor", 1, cons.length);
        assertEquals(clazz + " should only have an implicit constructor", 0, cons[0].getParameterCount());

        //methods
        Method[] meths = getDeclaredMethods(clazz);
        assertEquals(clazz + " should have exactly 2 methods", 2, meths.length);
        for (Method m : meths) {
            assertTrue(m + " should be public", Modifier.isPublic(m.getModifiers()));
            assertTrue(m + " should be static", Modifier.isStatic(m.getModifiers()));
            assertFalse(m + " shouldn't be final", Modifier.isFinal(m.getModifiers()));
            assertFalse(m + " shouldn't be abstract", Modifier.isAbstract(m.getModifiers()));
            assertEquals(m + " should have the right name (duh)", "suche", m.getName());
            if(m.getParameterCount() == 4) {
                assertSame(m + " has a wrong return type", Object.class, m.getReturnType());
                Parameter[] p = m.getParameters();
                assertSame(m + " has wrong arguments", UnimodaleListe.class, p[0].getType());
                assertSame(m + " has wrong arguments", Integer.TYPE, p[1].getType());
                assertSame(m + " has wrong arguments", Integer.TYPE, p[2].getType());
                assertSame(m + " has wrong arguments", Comparator.class, p[3].getType());
            } else if(m.getParameterCount() == 3) {
                assertSame(m + " has a wrong return type", Comparable.class, m.getReturnType());
                Parameter[] p = m.getParameters();
                assertSame(m + " has wrong arguments", UnimodaleListe.class, p[0].getType());
                assertSame(m + " has wrong arguments", Integer.TYPE, p[1].getType());
                assertSame(m + " has wrong arguments", Integer.TYPE, p[2].getType());
            } else {
                fail(m + "should have the right parameters");
            }
        }
    }

    // ==================== ALLGEMEINER ZENTRALER TEST ====================
    protected void test_suche__CLASSIC(int expected, Comparator<LongDingComparator> c, int... dings) {
        UnimodaleListeLongDing<LongDingComparable> listComparable = UnimodaleListeLongDing.createComparable(dings);
        UnimodaleListeLongDing<LongDingComparator> listComparator = UnimodaleListeLongDing.createComparator(dings);
        String dingsString = "Liste: " + Arrays.toString(dings);
        dingsString = dingsString.length() <= 100 ? dingsString : dingsString.substring(0, 42) + "##...##" + dingsString.substring(dingsString.length() - 42);
        String sut = c == null ? "Unimodale Suche mit Comparable" : "Unimodale Suche mit Comparator";
        LongDing max = c == null ? UnimodaleSuche.suche(listComparable, 0, dings.length - 1) : UnimodaleSuche.suche(listComparator, 0, dings.length - 1, c);
        int oMin = 0, oZ = 0, oMax = 0; // Laufzeiten i.S.v. Anzahl Listenzugriffe und Rekursionstiefen in O(log(n))
        long zZ = c == null ? listComparable.zZ : listComparator.zZ;
        long rZ = c == null ? listComparable.rZ : listComparator.rZ;
        if (dings.length <= 0) {
            assertNull(sut + "(" + dingsString + ") gibt faelschlich NICHT null zurueck!", max);
        } else {
            assertNotNull(sut + "(" + dingsString + ") gibt faelschlich null zurueck!", max);
            assertEquals(sut + "(" + dingsString + ") gibt falschen Wert zurueck!", Long.valueOf(expected), max.ding);
            int o = (int) (Math.log(dings.length) / Math.log(2)); // !!! O(log(n)) !!!
            oZ = 2 * (o + 1);
            oMin = o / 2;
            oMax = o + 1;
        }
        assertTrue(sut + "(" + dingsString + ") hat falsche \"O-Laufzeit\"! IST: " + zZ + " vs. SOLL: # <=" + oZ, zZ <= oZ);
        assertTrue(sut + "(" + dingsString + ") hat falsche Rekursionstiefe! IST: " + rZ + " vs. SOLL: " + oMin + "<= # <=" + oMax, oMin <= rZ && rZ <= oMax);
    }

    // ==================== ANTI_CHEAT_SALTED_TEST ====================
    private void test_suche__ANTI_CHEAT_SALTED(int splitPos, Comparator<LongDingComparator> c, int[] primes) {
        for (int pass = 1; pass <= 3; pass++) {
            int antiCheatSaltedSplitPos = splitPos + ((int) (666 * Math.random())) - 333;
            int antiCheatSaltedExpectedSalt = 666 + (int) (4711 * Math.random());
            int[] bentPrimes = bendPrimes(primes, antiCheatSaltedSplitPos);
            bentPrimes[antiCheatSaltedSplitPos] += antiCheatSaltedExpectedSalt;
            int expected = bentPrimes[antiCheatSaltedSplitPos];
            test_suche__CLASSIC(expected, c, bentPrimes);
        }
    }

//    old prime generator
//    private static int[] computePrimes() {
//        final int AMOUNT = 100_000;
//        boolean isPrime;
//        int[] primes = new int[AMOUNT];
//        primes[0] = primes[AMOUNT - 1] = 2;
//        for (int i = 1; i < AMOUNT; i++) {
//            primes[i] = primes[i - 1];
//            do {
//                isPrime = true;
//                primes[i]++;
//                for (int p = 0; p < i && isPrime; p++) {
//                    isPrime = primes[i] % primes[p] != 0;
//                }
//            } while (!isPrime);
//        }
//        return primes;
//    }

    private static int[] computePrimesFast() {
        final int AMOUNT = 100_000;
        int Prime100_000 = 1299709;

        boolean[] isPrime = new boolean[Prime100_000 + 1];
        Arrays.fill(isPrime, true);

        for (int p = 2; p * p < isPrime.length; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i < isPrime.length; i += p)
                    isPrime[i] = false;
            }
        }

        int primeCnt = 0;
        int[] computedPrimes = new int[AMOUNT];
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) computedPrimes[primeCnt++] = i;
        }

        return computedPrimes;
    }

    private static final int[] PRIMES__100_000 = computePrimesFast(), PRIMES__10_000 = Arrays.copyOf(PRIMES__100_000, 10_000);

    private static int[] bendPrimes(int[] primes, int splitPos) {
        int[] bentPrimes = new int[primes.length];
        for (int i = 0; i < primes.length - 1; i++) {
            if (0 <= splitPos - i) {
                bentPrimes[splitPos - i] = primes[primes.length - 1 - i];
            }
            if (splitPos + i < bentPrimes.length) {
                bentPrimes[splitPos + i] = primes[primes.length - 1 - i];
            }
        }
        return bentPrimes;
    }

    // ==================== TEST - Comparable ====================
    @Test(timeout = 666)
    public void test_suche_Comparable__Beispiel_Aufgabenblatt_MitAntiCheatSalt_und_ArgCheck__PUBLIC_TEST() {
        test_suche__CLASSIC(37, null, 13, 17, 19, 23, 29, 31, 37, 7, 5);
        // ANTI-CHEAT-SALT:
        test_suche__ANTI_CHEAT_SALTED(5_000, null, PRIMES__10_000);
        // ARG-CHECK:
        assertNull("CHECK YOUR ARGUMENTS! Loesung gibt faelschlich NICHT null zurueck!", UnimodaleSuche.<Long>suche(null, 1, 42));
        assertNull("CHECK YOUR ARGUMENTS! Loesung gibt faelschlich NICHT null zurueck!", UnimodaleSuche.<String>suche(null, 1, 42));
        UnimodaleListeLongDing<LongDingComparable> listComparable = UnimodaleListeLongDing.createComparable(13, 17, 19, 23, 29, 31, 37, 7, 5);
        for (int von = -7; von <= 7; von++) {
            for (int bis = -7; bis < 0 || bis < von; bis++) {
                assertNull("CHECK YOUR ARGUMENTS! Loesung gibt faelschlich NICHT null zurueck!", UnimodaleSuche.suche(listComparable, von, bis));
            }
        }
    }

    @Test(timeout = 666)
    public void test_suche_Comparable__666_PRIMES_10_000__PUBLIC_TEST() {
        test_suche__ANTI_CHEAT_SALTED(666, null, PRIMES__10_000);
    }

    @Test(timeout = 666)
    public void test_suche_Comparable__6_666_PRIMES_10_000__PUBLIC_TEST() {
        test_suche__ANTI_CHEAT_SALTED(6_666, null, PRIMES__10_000);
    }

    @Test(timeout = 6666)
    public void test_suche_Comparable__4711_PRIMES_100_000__PUBLIC_TEST() {
        test_suche__ANTI_CHEAT_SALTED(4_711, null, PRIMES__100_000);
    }

    @Test(timeout = 6666)
    public void test_suche_Comparable__7_0815_PRIMES_100_000__PUBLIC_TEST() {
        test_suche__ANTI_CHEAT_SALTED(70_815, null, PRIMES__100_000);
    }

    // ==================== TEST - Comparator ====================
    @Test(timeout = 666)
    public void test_suche_Comparator__Beispiel_Aufgabenblatt_MitAntiCheatSalt_und_ArgCheck__PUBLIC_TEST() {
        test_suche__CLASSIC(37, CLDC, 13, 17, 19, 23, 29, 31, 37, 7, 5);
        // ANTI-CHEAT-SALT:
        test_suche__ANTI_CHEAT_SALTED(5_000, CLDC, PRIMES__10_000);
        // ARG-CHECK:
        assertNull("CHECK YOUR ARGUMENTS! Loesung gibt faelschlich NICHT null zurueck!", UnimodaleSuche.suche(null, 1, 42, CLDC));
        UnimodaleListeLongDing<LongDingComparator> listComparator = UnimodaleListeLongDing.createComparator(13, 17, 19, 23, 29, 31, 37, 7, 5);
        for (int von = -7; von <= 7; von++) {
            for (int bis = -7; bis < 0 || bis < von; bis++) {
                assertNull("CHECK YOUR ARGUMENTS! Loesung gibt faelschlich NICHT null zurueck!", UnimodaleSuche.suche(listComparator, von, bis, CLDC));
            }
        }
    }

    @Test(timeout = 666)
    public void test_suche_Comparator__666_PRIMES_10_000__PUBLIC_TEST() {
        test_suche__ANTI_CHEAT_SALTED(666, CLDC, PRIMES__10_000);
    }

    @Test(timeout = 666)
    public void test_suche_Comparator__6_666_PRIMES_10_000__PUBLIC_TEST() {
        test_suche__ANTI_CHEAT_SALTED(6_666, CLDC, PRIMES__10_000);
    }

    @Test(timeout = 6666)
    public void test_suche_Comparator__4711_PRIMES_100_000__PUBLIC_TEST() {
        test_suche__ANTI_CHEAT_SALTED(4_711, CLDC, PRIMES__100_000);
    }

    @Test(timeout = 6666)
    public void test_suche_Comparator__7_0815_PRIMES_100_000__PUBLIC_TEST() {
        test_suche__ANTI_CHEAT_SALTED(70_815, CLDC, PRIMES__100_000);
    }


    //HELPER (copy paste from public tests)
    private static Class<?>[] getDeclaredClasses(Class<?> clazz) {
        java.util.List<Class<?>> declaredClasses = new java.util.ArrayList<>();
        for (Class<?> c : clazz.getDeclaredClasses()) {
            if (!c.isSynthetic()) {
                declaredClasses.add(c);
            }
        }
        return declaredClasses.toArray(new Class[0]);
    }

    private static Field[] getDeclaredFields(Class<?> clazz) {
        java.util.List<Field> declaredFields = new java.util.ArrayList<>();
        for (Field f : clazz.getDeclaredFields()) {
            if (!f.isSynthetic()) {
                declaredFields.add(f);
            }
        }
        return declaredFields.toArray(new Field[0]);
    }

    private static Constructor<?>[] getDeclaredConstructors(Class<?> clazz) {
        java.util.List<Constructor<?>> declaredConstructors = new java.util.ArrayList<>();
        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            if (!c.isSynthetic()) {
                declaredConstructors.add(c);
            }
        }
        return declaredConstructors.toArray(new Constructor[0]);
    }

    private static Method[] getDeclaredMethods(Class<?> clazz) {
        java.util.List<Method> declaredMethods = new java.util.ArrayList<>();
        for (Method m : clazz.getDeclaredMethods()) {
            if (!m.isBridge() && !m.isSynthetic()) {
                declaredMethods.add(m);
            }
        }
        return declaredMethods.toArray(new Method[0]);
    }
}
