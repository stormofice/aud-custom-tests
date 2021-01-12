import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class NatListeAlgebraCustomTest {

    // from public test
    private static final Nat[] n;

    static {
        n = new Nat[10000];
        n[0] = Nat.zero();
        for (int i = 1; i < n.length; i++) {
            n[i] = Nat.succ(n[i - 1]);
        }
    }

    // tests

    @Test(timeout = 420)
    public void cusTest_Algebra_Intestines_noAmbiguity() {
        Class<?> clazz = Algebra.class;
        Method[] methods = getDeclaredMethods(clazz);
        assertEquals(clazz + " soll genau die vorgegebene Anzahl Methoden haben.", 9, methods.length);
        for (Method method : methods) {
            assertTrue(method + " - Methode soll >static< sein.", Modifier.isStatic(method.getModifiers()));
            assertFalse(method + " - Methode soll nicht >abstract< sein.", Modifier.isAbstract(method.getModifiers()));
            switch (method.getName()) {
                case "modulo":
                case "kleiner":
                case "gleich":
                case "groesser":
                case "ggt":
                case "kgv":
                    assertTrue(method + " - Methode soll >public< sein.", Modifier.isPublic(method.getModifiers()));
                    assertEquals(method + " - Methode soll genau die vorgegebene Anzahl Parameter haben.", 2, method.getParameterTypes().length);
                    assertSame(method + " - Methode soll genau die vorgegebenen Parametertypen haben (der erste ist falsch).", Nat.class, method.getParameterTypes()[0]);
                    assertSame(method + " - Methode soll genau die vorgegebenen Parametertypen haben (der zweite ist falsch).", Nat.class, method.getParameterTypes()[1]);
                    assertEquals(method + " - Methode soll genau den vorgegebenen  Rueckgabetyp haben.", Nat.class, method.getReturnType());
                    break;
                case "fib":
                    assertTrue(method + " - Methode soll >public< sein.", Modifier.isPublic(method.getModifiers()));
                    assertEquals(method + " - Methode soll genau die vorgegebene Anzahl Parameter haben.", 1, method.getParameterTypes().length);
                    assertSame(method + " - Methode soll genau die vorgegebenen Parametertypen haben (der erste ist falsch).", Nat.class, method.getParameterTypes()[0]);
                    assertEquals(method + " - Methode soll genau den vorgegebenen  Rueckgabetyp haben.", Nat.class, method.getReturnType());
                    break;
                case "pfz":
                    assertTrue(method + " - Methode soll >public< sein.", Modifier.isPublic(method.getModifiers()));
                    assertEquals(method + " - Methode soll genau die vorgegebene Anzahl Parameter haben.", 1, method.getParameterTypes().length);
                    assertSame(method + " - Methode soll genau die vorgegebenen Parametertypen haben (der erste ist falsch).", Nat.class, method.getParameterTypes()[0]);
                    assertEquals(method + " - Methode soll genau den vorgegebenen  Rueckgabetyp haben.", Liste.class, method.getReturnType());
                    break;
                case "pfzH":
                    assertTrue(method + " - Methode soll >private< sein.", Modifier.isPrivate(method.getModifiers()));
                    assertEquals(method + " - Methode soll genau die vorgegebene Anzahl Parameter haben.", 2, method.getParameterTypes().length);
                    assertSame(method + " - Methode soll genau die vorgegebenen Parametertypen haben (der erste ist falsch).", Nat.class, method.getParameterTypes()[0]);
                    assertEquals(method + " - Methode soll genau den vorgegebenen  Rueckgabetyp haben.", Liste.class, method.getReturnType());
                    break;
                default:
                    fail(method + " sollte nicht existieren.");
                    break;
            }
        }
    }

    @Test(timeout = 420)
    public void cusTest_Liste_combined() {
        //neue Liste erstellen
        Liste<Nat> liste = Liste.neu();
        assertNotNull("Liste sollte initialisiert sein.", liste);
        assertNull("Liste sollte noch nicht befuellt sein.", Liste.kopf(liste));
        assertNull("Liste sollte keinen Nachfolger haben.", Liste.rest(liste));

        //1. vorne
        liste = Liste.vorne(liste, n[1]);
        assertArrayEquals("Schritt 1: vorne", new int[] {1}, deCancer(liste));
        assertEquals("Kopf falsch", 1, deCancer(Liste.kopf(liste)));
        assertNotNull("Rest sollte nicht null sein", Liste.rest(liste));

        //2. pos 1 (Ende)
        liste = Liste.einfuegen(liste, n[21], n[1]);
        assertArrayEquals("Schritt 2: pos 1", new int[] {1, 21}, deCancer(liste));
        assertEquals("Kopf falsch", 1, deCancer(Liste.kopf(liste)));
        assertNotNull("Rest sollte nicht null sein", Liste.rest(liste));

        //3. pos 1
        liste = Liste.einfuegen(liste, n[31], n[1]);
        assertArrayEquals("Schritt 3: pos 1", new int[] {1, 31, 21}, deCancer(liste));
        assertEquals("Kopf falsch", 1, deCancer(Liste.kopf(liste)));
        assertNotNull("Rest sollte nicht null sein", Liste.rest(liste));

        //4. pos 0
        liste = Liste.einfuegen(liste, n[40], n[0]);
        assertArrayEquals("Schritt 4: pos 0", new int[] {40, 1, 31, 21}, deCancer(liste));
        assertEquals("Kopf falsch", 40, deCancer(Liste.kopf(liste)));
        assertNotNull("Rest sollte nicht null sein", Liste.rest(liste));

        //5. pos 5 (Ende)
        liste = Liste.einfuegen(liste, n[55], n[5]);
        assertArrayEquals("Schritt 5: pos 5", new int[] {40, 1, 31, 21, 55}, deCancer(liste));
        assertEquals("Kopf falsch", 40, deCancer(Liste.kopf(liste)));
        assertNotNull("Rest sollte nicht null sein", Liste.rest(liste));

        //6. vorne
        liste = Liste.vorne(liste, n[6]);
        assertArrayEquals("Schritt 6: vorne", new int[] {6, 40, 1, 31, 21, 55}, deCancer(liste));
        assertEquals("Kopf falsch", 6, deCancer(Liste.kopf(liste)));
        assertNotNull("Rest sollte nicht null sein", Liste.rest(liste));
    }

    @Test(timeout = 1)
    public void cusTest_freeTest() { //free test so you wont feel bad :)
        try {
            assertTrue(true);
            assertNull(null);
        } catch (Throwable t) {
            fail("Wait, how did you even fail this?");
        }
    }

    //abgewandelt von https://discord.com/channels/765192487227883530/782236975297986570/798124198156435477

    @Test(timeout = 420)
    public void cusTest_Algebra_modulo() {
        assertSame("0 modulo 1", n[0], Algebra.modulo(n[0], n[1])); // 0 modulo n = 0 // Quelle Java und Taschenrechner
        assertSame("2511 modulo 93", n[0], Algebra.modulo(n[2511], n[93]));
        assertSame("121 modulo 40", n[0], Nat.sub(n[1], Algebra.modulo(n[121], n[40])));
        assertSame("42 modulo 121", n[0], Nat.sub(n[42], Algebra.modulo(n[42], n[121])));
        assertSame("12 modulo 12", n[0], Algebra.modulo(n[12], n[12]));

        // Randfall 0%0 nicht abgeprüft, da mir unklar ist, wie man das ganze gehandelt werden soll
        // Nat.div(zero, zero) liefert StackOverflow -> wird wahrscheinlich nicht geprüft
    }

    @Test(timeout = 420)
    public void cusTest_Algebra_kleiner() {
        assertSame("121 kleiner 40", n[0], Algebra.kleiner(n[121], n[40]));
        assertSame("42 kleiner 121", n[0], Nat.sub(n[1], Algebra.kleiner(n[42], n[121])));
        assertSame("42 kleiner 42", n[0], Algebra.kleiner(n[42], n[42])); // Verweis auf Angabe nur < und nicht <=
    }

    @Test(timeout = 420)
    public void cusTest_Algebra_groesse() {
        assertSame("121 groesser 40", n[0], Nat.sub(n[1], Algebra.groesser(n[121], n[40])));
        assertSame("42 groesser 121", n[0], Algebra.groesser(n[42], n[121]));
        assertSame("42 groesser 42", n[0], Algebra.groesser(n[42], n[42]));// Verweis auf Angabe nur > und nicht >=
    }

    @Test(timeout = 420)
    public void cusTest_Algebra_gleich() {
        assertSame("121 gleich 40", n[0], Algebra.gleich(n[121], n[40]));
        assertSame("42 gleich 121", n[0], Algebra.gleich(n[42], n[121]));
        assertSame("42 gleich 42", n[0], Nat.sub(n[1], Algebra.gleich(n[42], n[42])));
    }

    @Test(timeout = 420)
    public void cusTest_Algebra_fib() {
        int[] fib = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765};
        for (int i = 0; i <= 20; i++) {
            assertEquals(i + "te Fibonaccizahl", fib[i], deCancer(Algebra.fib(n[i])));
        }
    }


    @Test(timeout = 420)
    public void cusTest_Algebra_kgV() {
        assertEquals("kgV 673 und 12", 8076, deCancer(Algebra.kgv(n[673], n[12])));
        assertEquals("kgV 12 und 673", 8076, deCancer(Algebra.kgv(n[12], n[673])));
        assertEquals("kgV 73 und 73", 73, deCancer(Algebra.kgv(n[73], n[73])));
        assertSame("kgV 0 und 12", n[0], Algebra.kgv(n[0], n[12])); //kgV von 0 und n ist 0
        assertSame("kgV 12 und 0", n[0], Algebra.kgv(n[12], n[0])); // Quelle: https://de.wikipedia.org/wiki/Kleinstes_gemeinsames_Vielfaches
        assertSame("kgV 0 und 0", n[0], Algebra.kgv(n[0], n[0]));
    }

    @Test(timeout = 420)
    public void cusTest_Algebra_ggT() {
        assertEquals("ggT 612 und 23", 1, deCancer(Algebra.ggt(n[612], n[23])));
        assertEquals("ggT 387 und 86", 43, deCancer(Algebra.ggt(n[387], n[86])));
        assertEquals("ggT 86 und 387", 43, deCancer(Algebra.ggt(n[86], n[387])));
        assertEquals("ggT 24 und 24", 24, deCancer(Algebra.ggt(n[24], n[24])));
        assertEquals("ggT 5 und 0", 5, deCancer(Algebra.ggt(n[5], n[0]))); // ggT von 0 und n = n
        assertEquals("ggT 0 und 5", 5, deCancer(Algebra.ggt(n[0], n[5]))); // Quelle: https://de.numere-prime.ro/wie-man-ggt-groessten-gemeinsamen-teiler-von-zahlen-findet.php?ganzzahl1=0&ganzzahl2=1
        assertSame("ggT 0 und 0", n[0], Algebra.ggt(n[0], n[0])); // ggT(0,0) = 0 // Quelle: https://userpages.uni-koblenz.de/~krapf/Sommersemester%202018/Proseminar%20Primzahlen/Ausarbeitung3.pdf Seite 3 Zeile 8 https://matheplanet.com/default3.html?call=viewtopic.php?topic=15724&ref=https%3A%2F%2Fwww.google.com%2F // https://de.numere-prime.ro/wie-man-ggt-groessten-gemeinsamen-teiler-von-zahlen-findet.php?ganzzahl1=0&ganzzahl2=1
    }

    @Test(timeout = 420)
    public void cusTest_Algebra_pfz() {
        Liste<Nat> pfz;

        // pfZ von 1 = leere Liste // Quelle: Angabe
        pfz = Algebra.pfz(n[1]);
        assertNotNull(pfz);
        assertNull(Liste.kopf(pfz));
        assertNull(Liste.rest(pfz));

        pfz = Algebra.pfz(n[2]);
        assertNotNull(pfz);
        assertArrayEquals("(2) Pfz is broken.", new int[] {2}, deCancer(pfz));

        pfz = Algebra.pfz(n[2 * 2 * 2 * 2 * 2]);
        assertNotNull(pfz);
        assertArrayEquals("(2^5) Pfz is broken.", new int[] {2, 2, 2, 2, 2}, deCancer(pfz));

        pfz = Algebra.pfz(n[97]);
        assertNotNull(pfz);
        assertArrayEquals("(97) Pfz is broken.", new int[] {97}, deCancer(pfz));

        pfz = Algebra.pfz(n[2 * 3 * 5 * 7]);
        assertNotNull(pfz);
        assertArrayEquals("(2*3*5*7) Pfz is broken.", new int[] {2, 3, 5, 7}, deCancer(pfz));
    }

    // helper

    protected static int deCancer(Nat number) {
        String cancer = number.toString();
        return cancer.length() - 1;
    }

    protected static int[] deCancer(Liste<Nat> cancerList) {
        ArrayList<Integer> curedList = new ArrayList<>();

        while(Liste.kopf(cancerList) != null) {
            curedList.add(deCancer(Liste.kopf(cancerList)));
            cancerList = Liste.rest(cancerList);
        }

        int[] outputList = new int[curedList.size()];
        for (int i = 0; i < outputList.length; i++) {
            outputList[i] = curedList.get(i);
        }

        return outputList;
    }

    protected static Method[] getDeclaredMethods(Class<?> clazz) {
        java.util.List<Method> declaredMethods = new java.util.ArrayList<>();
        for (Method m : clazz.getDeclaredMethods()) {
            if (!m.isBridge() && !m.isSynthetic()) {
                declaredMethods.add(m);
            }
        }
        return declaredMethods.toArray(new Method[0]);
    }
}
