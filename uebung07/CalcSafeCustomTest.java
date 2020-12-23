import org.junit.Test;

import static org.junit.Assert.*;

public class CalcSafeCustomTest {

    @Test
    public void customTest_calcV() {
        CalcSafe c = new CalcSafe();
        Exception working = null;
        int lineExceptionThrown = 0;
        try {
            c.calcV(Integer.MAX_VALUE, Integer.MAX_VALUE);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 17;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcV(Integer.MAX_VALUE / 3 + 1, 2);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 27;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcV(23, Integer.MAX_VALUE);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 37;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcV((Integer.MAX_VALUE - 16) / 3, 42);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 47;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                InexactIntegerArithmeticsException.class, working.getClass());

        working = null;
        try {
            c.calcV(Integer.MIN_VALUE, -42);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 57;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcV(Integer.MIN_VALUE, Integer.MIN_VALUE);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 67;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcV(Integer.MIN_VALUE / 3 - 1, -2);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 77;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcV(-23, Integer.MIN_VALUE);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 87;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcV(23, Integer.MIN_VALUE);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 97;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                InexactIntegerArithmeticsException.class, working.getClass());

        working = null;
        try {
            c.calcV((Integer.MIN_VALUE + 16) / 3, -42);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 107;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                InexactIntegerArithmeticsException.class, working.getClass());

        working = null;
        try {
            c.calcV(0, 0);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 117;
        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen" + " ; Fehler gefangen in: " + lineExceptionThrown, working);

        working = null;
        try {
            c.calcV(23, 21);
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 127;
        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen" + " ; Fehler gefangen in: " + lineExceptionThrown, working);

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    -9660, c.calcV(-115, -10065));
        } catch (Exception e) {
            working = e;
            lineExceptionThrown = 137;
        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen" + " ; Fehler gefangen in: " + lineExceptionThrown, working);

    }

    @Test
    public void customTest_calcA() {
        CalcSafe c = new CalcSafe();
        Exception working = null;

        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    Integer.MAX_VALUE, c.calcA(Integer.MAX_VALUE, new int[]{}));
        } catch (Exception e) {
            working = e;

        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    2, c.calcA(2, new int[]{0, 0, 0, 0}));
        } catch (Exception e) {
            working = e;

        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    -2147483645, c.calcA(2, new int[]{Integer.MAX_VALUE}));
        } catch (Exception e) {
            working = e;

        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    2, c.calcA(2, new int[]{2, 2, 0}));
        } catch (Exception e) {
            working = e;

        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    -4, c.calcA(2, new int[]{2, 2, -4, 2}));
        } catch (Exception e) {
            working = e;

        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            c.calcA(42, new int[]{2, 2, 5, 0, 56, Integer.MIN_VALUE});
        } catch (Exception e) {
            working = e;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                InexactIntegerArithmeticsException.class, working.getClass());

        working = null;
        try {
            c.calcA(Integer.MAX_VALUE - 1, new int[]{2});
        } catch (Exception e) {
            working = e;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcA(Integer.MAX_VALUE - 1, new int[]{-2});
        } catch (Exception e) {
            working = e;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcA(Integer.MIN_VALUE, new int[]{2});
        } catch (Exception e) {
            working = e;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcA(Integer.MIN_VALUE, new int[]{-2});
        } catch (Exception e) {
            working = e;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    -4, c.calcA(-2, new int[]{2}));
        } catch (Exception e) {
            working = e;

        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    4, c.calcA(-2, new int[]{-2}));
        } catch (Exception e) {
            working = e;

        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            c.calcA(Integer.MAX_VALUE - 5, new int[]{6});
        } catch (Exception e) {
            working = e;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcA(Integer.MIN_VALUE + 5, new int[]{-6});
        } catch (Exception e) {
            working = e;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcA(Integer.MAX_VALUE - 4, new int[]{-5});
        } catch (Exception e) {
            working = e;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            c.calcA(Integer.MIN_VALUE + 4, new int[]{5});
        } catch (Exception e) {
            working = e;
        }
        assertNotNull("Keine Exception obwohl Exception erwartert", working);
        assertEquals("keine Exception oder falsche Exception",
                UnderOrOverflowException.class, working.getClass());

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    Integer.MAX_VALUE, c.calcA(Integer.MAX_VALUE - 5, new int[]{-5}));
        } catch (Exception e) {
            working = e;
        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    Integer.MAX_VALUE, c.calcA(Integer.MAX_VALUE - 6, new int[]{6}));
        } catch (Exception e) {
            working = e;
        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    Integer.MAX_VALUE - 1, c.calcA(Integer.MAX_VALUE / -2, new int[]{-2}));
        } catch (Exception e) {
            working = e;
        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);
    }

    @Test
    public void customTest_calcVSafe() {
        CalcSafe c = new CalcSafe();

        assertEquals("falscher Rueckgabewert!", -1, c.calcVSafe(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals("falscher Rueckgabewert!", -1, c.calcVSafe(Integer.MAX_VALUE / 3 + 1, 2));
        assertEquals("falscher Rueckgabewert!", -1, c.calcVSafe(23, Integer.MAX_VALUE));
        assertEquals("falscher Rueckgabewert!", -2, c.calcVSafe((Integer.MAX_VALUE - 16) / 3, 42));
        assertEquals("falscher Rueckgabewert!", -1, c.calcVSafe(Integer.MIN_VALUE, -42));
        assertEquals("falscher Rueckgabewert!", -1, c.calcVSafe(Integer.MIN_VALUE, Integer.MIN_VALUE));
        assertEquals("falscher Rueckgabewert!", -1, c.calcVSafe(Integer.MIN_VALUE / 3 - 1, -2));
        assertEquals("falscher Rueckgabewert!", -1, c.calcVSafe(-23, Integer.MIN_VALUE));
        assertEquals("falscher Rueckgabewert!", -2, c.calcVSafe(23, Integer.MIN_VALUE));
        assertEquals("falscher Rueckgabewert!", -2, c.calcVSafe((Integer.MIN_VALUE + 16) / 3, -42));
        assertEquals("falscher Rueckgabewert!", 0, c.calcVSafe(0, 0));
        assertEquals("falscher Rueckgabewert!", 23, c.calcVSafe(23, 21));
        assertEquals("falscher Rueckgabewert!", -9660, c.calcVSafe(-115, -10065));

    }

    @Test
    public void customTest_calcASafe() {
        CalcSafe c = new CalcSafe();
        Exception working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    Integer.MAX_VALUE - 1, c.calcASafe(Integer.MAX_VALUE / -2, new int[]{-2}));
        } catch (Exception e) {
            working = e;
        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    -4, c.calcASafe(2, new int[]{2, 2, -4, 2}));
        } catch (Exception e) {
            working = e;
        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    93, c.calcASafe(42, new int[]{2, 2, 5, 0, 56, Integer.MIN_VALUE}));
        } catch (Exception e) {
            working = e;
        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

        working = null;
        try {
            assertEquals("nicht faelschlicherweise eine Exception" +
                            "geworfen, aber nicht das richtige Ergebnis berechnet",
                    98396, c.calcASafe(98345, new int[]{2, 2, 5, 0, 56, Integer.MIN_VALUE, 7, 45, 345, -4, -5, -345345, 5, 345, 5, 5, 5, 5, 5, 5, 55, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, -45354354, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}));
        } catch (Exception e) {
            working = e;
        }
        assertNull("Es wird faelschlicherweise eine Exception geworfen", working);

    }
}
