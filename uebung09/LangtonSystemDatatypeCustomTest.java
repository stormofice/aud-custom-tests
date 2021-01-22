import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LSDCustomTest {

    @Test
    public void customTest__get_X_Y() {
        LSD stepper = new LSD(Direction.E);

        long[] x = new long[]{
                0, -1, -1, 0, 0, 1, 1, 0, 0, -1, -1, 0, 0, -1, -1, 0, 0, -1, -1, -2, -2, -1, -1, -2, -2, -3, -3, -2, -2, -1, -1, -2, -2, -3, -3, -4, -4, -3, -3, -2, -2, -3, -3, -2, -2, -3, -3, -2, -2, -1, -1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 2, 2, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 2, 2, 3, 3, 4, 4, 3, 3, 2, 2, 3, 3, 2, 2, 3, 3, 2, 2, 1, 1, 0, 0, 1, 1, 2
        };
        long[] y = new long[]{
                -1, -1, 0, 0, 1, 1, 0, 0, 1, 1, 2, 2, 1, 1, 0, 0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 0, 0, 1, 1, 0, 0, -1, -1, 0, 0, 1, 1, 0, 0, -1, -1, 0, 0, -1, -1, -2, -2, -1, -1, 0, 0, -1, -1, -2, -2, -3, -3, -2, -2, -1, -1, -2, -2, -1, -1, -2, -2, -1, -1, 0, 0, -1, -1, 0, 0, 1, 1, 0, 0, -1, -1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0
        };
        int steps = 14;

        for (int i = 0; i < steps; i++) {
            stepper = stepper.step();

            long actualX = stepper.getX();
            long actualY = stepper.getY();

            assertEquals("After " + (i + 1) + " steps wrong x: ", x[i], actualX);
            assertEquals("After " + (i + 1) + " steps wrong y: ", y[i], actualY);
        }
    }

    @Test(timeout = 666)
    public void customTest__Dir_E__steps_10_20_100() {
        LSD stepper = new LSD(Direction.E);
        int steps = 18;

        int[][] s10 = new int[][]{
                {0, 0, 1},
                {1, 1, 1},
                {1, 1, 0}
        };
        int[][] s20 = new int[][]{
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int[][] s100 = new int[][]{
                {0, 0, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        for (int i = 0; i < steps; i++) {
            stepper = stepper.step();
            if (i == 9) {
                printExpectedVsActual(s10, stepper);
                checkEquals(s10, stepper);
            } else if (i == 19) {
                printExpectedVsActual(s20, stepper);
                checkEquals(s20, stepper);
            } else if (i == 99) {
                printExpectedVsActual(s100, stepper);
                checkEquals(s100, stepper);
            }
        }
    }

    private void checkEquals(int[][] expected, LSD lsd) {
        int[][] actual = transformLSD(expected, lsd);
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected.length; j++) {
                assertEquals(expected[i][j], actual[i][j]);
            }
        }
        System.out.println("Nice");
        System.out.println();
    }

    private int[][] transformLSD(int[][] expected, LSD lsd) {
        int[][] lsdArray = new int[expected.length][expected.length];

        int i = 0;
        int j = 0;
        for (int x = 0; x < expected.length; x++) {
            for (int y = expected.length - 1; y >= 0; y--) {
                lsdArray[i][j] = lsd.getCol(x - expected.length / 2L, y - expected.length / 2L) == Colour.WHITE ? 0 : 1;
                i++;
            }
            i = 0;
            j++;
        }
        return lsdArray;
    }

    private void printExpectedVsActual(int[][] expected, LSD lsd) {
        System.out.println("Expected: ");
        for (int x = 0; x < expected.length; x++) {
            for (int y = 0; y < expected[x].length; y++) {
                System.out.print(expected[x][y] == 0 ? "W, " : "B, ");
            }
            System.out.println();
        }


        int[][] actual = transformLSD(expected, lsd);
        System.out.println("Actual: ");
        for (int x = 0; x < expected.length; x++) {
            for (int y = 0; y < expected[x].length; y++) {
                System.out.print(actual[x][y] == 0 ? "W, " : "B, ");
            }
            System.out.println();
        }
    }
}