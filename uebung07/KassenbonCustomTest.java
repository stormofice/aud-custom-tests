import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.fail;

public class KassenbonCustomTest {

    @Test(timeout = 666)
    public void customTest__check_all() {
        ArrayList<Throwable> thrown = new ArrayList<>();
        ArrayList<Integer> failedAt = new ArrayList<>();

        try {
            KassenbonAusnahmen.ausnahme1();
            failedAt.add(1);
        } catch (Throwable e) {
            thrown.add(e);
        }
        try {
            KassenbonAusnahmen.ausnahme2();
            failedAt.add(2);
        } catch (Throwable e) {
            if (alreadyThrown(thrown, e)) fail("Ausnahme2: Same exception multiple times");
            thrown.add(e);
        }
        try {
            KassenbonAusnahmen.ausnahme3();
            failedAt.add(3);
        } catch (Throwable e) {
            if (alreadyThrown(thrown, e)) fail("Ausnahme3: Same exception multiple times");
            thrown.add(e);
        }
        try {
            KassenbonAusnahmen.ausnahme4();
            failedAt.add(4);
        } catch (Throwable e) {
            if (alreadyThrown(thrown, e)) fail("Ausnahme4: Same exception multiple times");
            thrown.add(e);
        }
        try {
            KassenbonAusnahmen.ausnahme5();
            failedAt.add(5);
        } catch (Throwable e) {
            if (alreadyThrown(thrown, e)) fail("Ausnahme5: Same exception multiple times");
            thrown.add(e);
        }
        try {
            KassenbonAusnahmen.ausnahme6();
            failedAt.add(6);
        } catch (Throwable e) {
            if (alreadyThrown(thrown, e)) fail("Ausnahme6: Same exception multiple times");
            thrown.add(e);
        }
        try {
            KassenbonAusnahmen.ausnahme7();
            failedAt.add(7);
        } catch (Throwable e) {
            if (alreadyThrown(thrown, e)) fail("Ausnahme7: Same exception multiple times");
            thrown.add(e);
        }
        try {
            KassenbonAusnahmen.ausnahme8();
            failedAt.add(8);
        } catch (Throwable e) {
            if (alreadyThrown(thrown, e)) fail("Ausnahme8: Same exception multiple times");
            thrown.add(e);
        }
        
        System.out.println("==================================");
        System.out.println("Thrown exceptions:");
        for (Throwable e : thrown) {
            e.printStackTrace();
            System.out.println();
        }
        System.out.println("==================================");

        if (failedAt.size() > 0) {
            StringBuilder failedAtString = new StringBuilder(" ");
            for (Integer i : failedAt) {
                failedAtString.append(i).append(", ");
            }
            fail("No exception thrown:" + failedAtString.toString());
        }
    }

    private boolean alreadyThrown(ArrayList<Throwable> allThrowable, Throwable t) {
        AtomicBoolean isThrown = new AtomicBoolean(false);
        for (Throwable throwable : allThrowable) {
            if (t.getClass().equals(throwable.getClass())
                    && throwable.getStackTrace()[0].equals(t.getStackTrace()[0])) {
                isThrown.set(true);
            }
        }
        return isThrown.get();
    }
}
