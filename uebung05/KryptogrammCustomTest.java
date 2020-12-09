import org.junit.Test;

/**
 * Extending the PublicTest invokes all the tests in the superclass; normally this shouldn't be a problem,<br>
 * but due to the random tests this will change the time needed for every further test that relies on randomness <br>
 * <i>Every PublicTest is affected by this!</i><br>
 * <b>THIS MAY LEAD TO PUBLIC TESTS TIMING OUT</b><br>
 * <i>This is not a problem in your code, just a side effect of the way we reuse the methods provided in the PublicTest</i><br>
 * These tests can take up to 15 minutes to complete in the worst case<br>
 * Test times are <i>random</i>>
 */
public class KryptogrammCustomTest extends KryptogrammPublicTest {

    @Test(timeout = 600000)
    public void customTest_extrahiereZeichen_random() {
        int maxValue = (int) Math.pow(2, 12);
        for (int i = 0; i < maxValue; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    KryptogrammPublicTest.check_extrahiereZeichen(KryptogrammPublicTest.generiere_zufaelliges_Kryptogramm().kryptogramm);
                }
            }).start();
        }
    }

    @Test(timeout = 600000)
    public void customTest_werteAus_random() {
        int maxValue = (int) Math.pow(2, 12);
        for (int i = 0; i < maxValue; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ZufaelligesKryptogramm zk = KryptogrammPublicTest.generiere_zufaelliges_Kryptogramm();
                    for (int j = 0; j < zk.kryptogramm.length; j++) {
                        KryptogrammPublicTest.check_werteAus(zk.kryptogramm[j], zk.loesung, zk.rechnung[j]);
                    }
                }
            }).start();
        }
    }

    @Test(timeout = 600000)
    public void customTest_loese_random() {
        int maxValue = (int) Math.pow(2, 12);
        for (int i = 0; i < maxValue; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    KryptogrammPublicTest.check_loese(KryptogrammPublicTest.generiere_zufaelliges_Kryptogramm().kryptogramm, true);
                }
            }).start();
        }
    }

}