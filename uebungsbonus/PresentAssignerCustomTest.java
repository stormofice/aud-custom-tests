import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

public class PresentAssignerCustomTest {

    //PublicTest muss im selben Package sein!!
    public static final String EX_NAME_makeQuadratic = "PresentAssigner.makeQuadratic";

    @Test(timeout = 1000)
    public void customTest_makeQuadratic_various() {
        int[][] inputQuadratic = {{1, 2, 3, 4, 5, 6, 7, 8, 9}};
        int[][] expectedQuadratic = {{1, 2, 3, 4, 5, 6, 7, 8, 9}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        int[][] actualQuadratic = PresentAssigner.makeQuadratic(deepCopy(inputQuadratic));
        assertArrayEquals(EX_NAME_makeQuadratic + " does not convert 1x9 matrix to 9x9 matrix", expectedQuadratic, actualQuadratic);
        // --------------------
        int[][] input5x1 = {{1}, {2}, {3}, {4}, {5}};
        int[][] expected5x1 = {{1, 0, 0, 0, 0}, {2, 0, 0, 0, 0}, {3, 0, 0, 0, 0}, {4, 0, 0, 0, 0}, {5, 0, 0, 0, 0}};
        int[][] actual5x1 = PresentAssigner.makeQuadratic(deepCopy(input5x1));
        assertArrayEquals(EX_NAME_makeQuadratic + " does not convert 5x1 matrix to 5x5 matrix", expected5x1, actual5x1);

        int[][] inputWeird = new int[][]{
                {0, 3, 4},
                {1, 2, 3, 4, 5, 6},
                {2, 6, 1, 5}
        };
        int[][] expectedWeird = {{0, 3, 4, 0, 0, 0}, {1, 2, 3, 4, 5, 6}, {2, 6, 1, 5, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};
        int[][] actualWeird = PresentAssigner.makeQuadratic(deepCopy(inputWeird));
        assertArrayEquals(EX_NAME_makeQuadratic + " does not convert [nicht rechteckige] matrix right", expectedWeird, actualWeird);

        // kp ob das relevant ist
        assertArrayEquals("weiß ned ob sowas überhaupt kommt aber sollte eig funktionieren", new int[5][5], PresentAssigner.makeQuadratic(new int[5][0]));

        assertArrayEquals("nicht 100% sicher ein Fehler", new int[][]{}, PresentAssigner.makeQuadratic(new int[][]{}));

        Exception temp = null;
        try {
            PresentAssigner.makeQuadratic(null);
        } catch (Exception e) {
            temp = e;
        }
        assertNotNull(temp);
        assertEquals(IllegalArgumentException.class, temp.getClass());
    }

    @Test(timeout = 1000)
    public void customTest_negative_values() {
        PresentAssigner tester = new PresentAssigner(new int[][]{
                {-5, -2},
                {3, -7},
                {2, 1}
        });
        int[] result = tester.getAssignment();
        assertArrayEquals(new int[]{2, 0, 1}, result);
    }

    @Test(timeout = 420)
    public void customTest_nothing_to_gift() {
        PresentAssigner tester = new PresentAssigner(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        int[] result = tester.getAssignment();
        assertArrayEquals(new int[]{0, 1, 2, 3}, result);
    }

    @Test(timeout = 1815)
    public void customTest_industrial_production() {
        PresentAssigner tester = new PresentAssigner(new int[][]{
                {1, 5, 6, 9, 2, -345, 654},
                {Integer.MAX_VALUE, 5, 6, 9, -4, -345, 654},
                {34958, 5, 6, Integer.MIN_VALUE, -4, -345, 654},
                {1, 42, 6, 0, -0, -0, 654},
                {2, 42, 5, 9, -4, 1, -43},
                {3, 42, 100, 95, 2, -34567, 654}
        });
        int[] result = tester.getAssignment();
        assertArrayEquals(new int[]{4, 0, 6, 1, 3, 2, 5}, result);
    }

    @Test(timeout = 1000)
    public void customTest_basic_structure() {
        final PresentAssignerPublicTest.PresentAssignerTestStub tester = new PresentAssignerPublicTest.PresentAssignerTestStub(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}, new int[]{0, 1, 2});
        assertArrayEquals(new int[]{0, 1, 2}, tester.getAssignment());
        assertFalse("In this case there should be no call to initialAssignment() in getAssignment()", tester.initialAssignmentCalled);
        assertFalse("In this case there should be no call to singleAuctionPhase() in getAssignment()", tester.singleAuctionPhaseCalled);
    }

    @Test(timeout = 1000)
    public void customTest_simple_prices() {
        final PresentAssignerPublicTest.PresentAssignerTestStub tester = new PresentAssignerPublicTest.PresentAssignerTestStub(new int[][]{{-2, -3, -3}, {100, -233, 11}, {0, 21, 1}}, new int[]{0, 1, 2});
        Queue<Integer> queue = new LinkedList<>();
        tester.singleAuctionPhase(0, queue);
        assertEquals(0.0, tester.prices[0], 0.01);
        assertEquals(0.0, tester.prices[1], 0.01);
        assertEquals(0.0, tester.prices[2], 0.01);
        tester.singleAuctionPhase(1, queue);
        assertEquals(89.3333, tester.prices[0], 0.01);
        assertEquals(0.0, tester.prices[1], 0.01);
        assertEquals(0.0, tester.prices[2], 0.01);
        tester.singleAuctionPhase(2, queue);
        assertEquals(20.3333, tester.prices[1], 0.01);
        assertEquals(89.3333, tester.prices[0], 0.01);
        assertEquals(0.0, tester.prices[2], 0.01);
    }

    @Test
    public void customTest_gift_factory() {
        final PresentAssigner tester = new PresentAssigner(new int[][]{
                {1, 5, 6, 9, 2, -345, 654},
                {Integer.MAX_VALUE, 5, 6, 9, -4, -345, 654},
                {34958, 5, 6, Integer.MIN_VALUE, -4, -345, 654},
                {1, 42, 6, 0, -0, -0, 654},
                {2, 42, 5, 5, 345, 6334, 9, -4, 1, -43},
                {3, 42, 100, 95, 2, -34567, 654},
                {1, 5, 6, 9, 2, -345, 654},
                {Integer.MAX_VALUE, 5, 6, 9, -4, -345, 654},
                {34958, 5, 6, Integer.MIN_VALUE, -4, -345, 654},
                {1, 42, 6, 0, -0, -0, 654},
                {2, 42, 5, 9, -4, 1, -43},
                {3, 42, 100, 95, 2, -34567, 654},
                {1, 5, 6, 9, 2, -345, 654},
                {Integer.MAX_VALUE, 5, 6, 9, -4, -345, 654},
                {34958, 5, 6, Integer.MIN_VALUE, -4, -345, 654},
                {1, 42, 6, 0, -0, -0, 654},
                {2, 42, 5, 9, -4, 1, -43},
                {3, 42, 100, 95, 2, -34567, 654},
                {1, 5, 6, 9, 5, 345, 5, 345, 6334, 6334, 2, -345, 654},
                {Integer.MAX_VALUE, 5, 6, 9, -4, -345, 654},
                {34958, 55, 345, 6334, 6, Integer.MIN_VALUE, -4, -345, 654},
                {1, 42, 6, 0, -0, -0, 654},
                {2, 42, 5, 9, 5, 345, 6334, -4, 1, -43},
                {3, 42, 100, 95, 2, -34567, 654},
                {1, 5, 6, 9, 2, -345, 654},
                {Integer.MAX_VALUE, 5, 6, 9, -4, -345, 654},
                {34958, 5, 6, Integer.MIN_VALUE, -4, -345, 654},
                {1, 42, 6, 0, -0, -0, 654},
                {2, 42, 5, 9, -4, 1, -43},
                {3, 42, 100, 95, 5, 345, 6334, 2, -34567, 654},
                {1, 5, 6, 9, 2, -345, 654},
                {Integer.MAX_VALUE, 5, 6, 9, -4, -345, 654},
                {34958, 5, 6, Integer.MIN_VALUE, -4, -345, 654},
                {1, 42, 6, 0, -0, -0, 654},
                {2, 42, 5, 9, -4, 1, -43},
                {3, 42, 100, 95, 2, -34567, 654}
        });
        int[] result = tester.getAssignment();
        long gesVal = 0;

        for (int i = 0; i < result.length; i++) {
            gesVal += tester.values[i][result[i]];
        }

        //falls erreichter Glückswert höher bitte Rückmeldung
        assertEquals("Maximaler Glückswert nicht erreicht!              Deine Endzuweisung ist: " + Arrays.toString(result), 2147509781L, gesVal);
    }

    @Test(timeout = 1815)
    public void customTest_negative_values_various() {
        PresentAssigner tester = new PresentAssigner(new int[][]{
                {-100, -100, 1},
                {-1, -10, -10}
        });
        int[] result = tester.getAssignment();
        long gesVal = 0;

        for (int i = 0; i < result.length; i++) {
            gesVal += tester.values[i][result[i]];
        }


        assertEquals("Maximaler Glückswert nicht erreicht!              Deine Endzuweisung ist: " + Arrays.toString(result), 0L, gesVal);
    }

    @Test
    public void customTest_duplicates() {
        PresentAssigner tester = new PresentAssigner(new int[][]{
                {-1, -3, -5, 0},
                {-1, -3, -5, 0},
                {-5, -3, -1, 0},
                {-5, -3, -1, 0},
        });
        int[] result = tester.getAssignment();
        long gesVal = 0;

        for (int i = 0; i < result.length; i++) {
            gesVal += tester.values[i][result[i]];
        }

        assertEquals("Maximaler Glückswert nicht erreicht!              Deine Endzuweisung ist: " + Arrays.toString(result), -5L, gesVal);
    }

    @Test
    public void customTest_cycle() {
        PresentAssigner tester = new PresentAssigner(new int[][]{
                {1, 2, 3},
                {Integer.MAX_VALUE, 2, 3},
                {Integer.MAX_VALUE, 2, 3},
        });
        int[] result = tester.getAssignment();
        long gesVal = 0;

        for (int i = 0; i < result.length; i++) {
            gesVal += tester.values[i][result[i]];
        }

        assertEquals("Maximaler Glückswert nicht erreicht!              Deine Endzuweisung ist: " + Arrays.toString(result), 2147483652L, gesVal);
    }

    private int[][] deepCopy(final int[][] array) {
        final int[][] result = new int[array.length][];
        for (int i = 0; i < array.length; ++i) {
            result[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return result;
    }
}