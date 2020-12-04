package uebung04;

import org.junit.*;

import static org.junit.Assert.*;

public class KenKenCustomTest {

    /**
     * Checks if the KenKen code is able to solve a bigger (and more complex) KenKen
     * Note: Timeout is intentionally left out, to see how long the code takes for this complex KenKen.
     * Warning: can be up to 1hr if code is not effective.
     */
    @Test
    public void customTest_exampleSolve9x9_0_full() {
        int[][][] testInputOrg = {
                {{3, '-'}, {0, 0}, {0, 1}},
                {{20, '*'}, {0, 2}, {0, 3}, {1, 3}},
                {{1, '-'}, {0, 4}, {1, 4},},
                {{4, '/'}, {0, 5}, {0, 6}},
                {{6, '-'}, {0, 7}, {0, 8}},
                {{18, '*'}, {1, 0}, {1, 1}, {1, 2}},
                {{5, '-'}, {1, 5}, {1, 6}},
                {{120, '*'}, {1, 7}, {1, 8}, {2, 8}},
                {{30, '+'}, {2, 0}, {2, 1}, {2, 2}, {2, 3}},
                {{4, '-'}, {2, 4}, {3, 4}},
                {{2, '-'}, {2, 5}, {3, 5}},
                {{54, '*'}, {2, 6}, {2, 7}, {3, 6}, {3, 7}},
                {{2, '-'}, {3, 0}, {3, 1}},
                {{5, '-'}, {3, 2}, {3, 3}},
                {{1, '-'}, {3, 8}, {4, 8}},
                {{11, '+'}, {4, 0}, {5, 0}, {6, 0}, {7, 0}},
                {{72, '*'}, {4, 1}, {5, 1}},
                {{1, '-'}, {4, 2}, {5, 2}},
                {{6, '-'}, {4, 3}, {4, 4}},
                {{6, ' '}, {4, 5}},
                {{1, '-'}, {4, 6}, {4, 7}},
                {{20, '+'}, {5, 3}, {5, 4}, {6, 3}, {6, 4}},
                {{5, '-'}, {5, 5}, {5, 6}},
                {{28, '*'}, {5, 7}, {6, 6}, {6, 7}},
                {{40, '*'}, {5, 8}, {6, 8}},
                {{2, '-'}, {6, 1}, {6, 2}},
                {{5, '-'}, {6, 5}, {7, 5}},
                {{4, '/'}, {7, 1}, {7, 2}},
                {{3, '/'}, {7, 3}, {7, 4}},
                {{1, '-'}, {7, 6}, {8, 6}},
                {{2, '-'}, {7, 7}, {8, 7}},
                {{2, '-'}, {7, 8}, {8, 8}},
                {{2, '-'}, {8, 0}, {8, 1}},
                {{10, '+'}, {8, 2}, {8, 3}, {8, 4}, {8, 5}}
        };
        int[][] expectedSolution = { //
                {4, 7, 1, 5, 6, 8, 2, 9, 3},
                {9, 1, 2, 4, 7, 3, 8, 5, 6},
                {8, 6, 9, 7, 1, 5, 3, 2, 4},
                {6, 4, 3, 8, 5, 7, 9, 1, 2},
                {5, 9, 7, 2, 8, 6, 4, 3, 1},
                {3, 8, 6, 1, 9, 2, 7, 4, 5},
                {2, 3, 5, 6, 4, 9, 1, 7, 8},
                {1, 2, 8, 9, 3, 4, 5, 6, 7},
                {7, 5, 4, 3, 2, 1, 6, 8, 9}
        };
        int[][][] testInput = deepCloneKenken(testInputOrg);
        int[][] studentSolution = KenKen.solve(testInputOrg);
        assertArrayEquals("kenkenExample: " + METHOD_NAME_KenKen_solve + " MODIFIED THE INPUT KenKen!", testInputOrg, testInput);
        assertNotNull("kenkenExampleOriginal: has a solution, but " + METHOD_NAME_KenKen_solve + " didn't find it.", studentSolution);
        assertArrayEquals("kenkenExampleOriginal: solution returned by " + METHOD_NAME_KenKen_solve + " is wrong.", expectedSolution, studentSolution);
    }

    /**
     * The same as cusTest_exampleSolve9x9_0_full but with more whitespace fields to speed up solving.
     * => Can potentially help with debugging.
     */
    @Test
    public void customTest_exampleSolve9x9_0_white() {
        int[][][] testInputOrg = {
                {{3, '-'}, {0, 0}, {0, 1}},
                {{20, '*'}, {0, 2}, {0, 3}, {1, 3}},
                {{1, '-'}, {0, 4}, {1, 4},},
                {{4, '/'}, {0, 5}, {0, 6}},
                {{6, '-'}, {0, 7}, {0, 8}},
                {{18, '*'}, {1, 0}, {1, 1}, {1, 2}},
                {{5, '-'}, {1, 5}, {1, 6}},
                {{120, '*'}, {1, 7}, {1, 8}, {2, 8}},
                {{8, ' '}, {2, 0}},
                {{6, ' '}, {2, 1}},
                {{9, ' '}, {2, 2}},
                {{7, ' '}, {2, 3}},
                {{4, '-'}, {2, 4}, {3, 4}},
                {{2, '-'}, {2, 5}, {3, 5}},
                {{3, ' '}, {2, 6}},
                {{2, ' '}, {2, 7}},
                {{9, ' '}, {3, 6}},
                {{1, ' '}, {3, 7}},
                {{2, '-'}, {3, 0}, {3, 1}},
                {{5, '-'}, {3, 2}, {3, 3}},
                {{1, '-'}, {3, 8}, {4, 8}},
                {{5, ' '}, {4, 0}},
                {{3, ' '}, {5, 0}},
                {{2, ' '}, {6, 0}},
                {{1, ' '}, {7, 0}},
                {{72, '*'}, {4, 1}, {5, 1}},
                {{1, '-'}, {4, 2}, {5, 2}},
                {{6, '-'}, {4, 3}, {4, 4}},
                {{6, ' '}, {4, 5}},
                {{1, '-'}, {4, 6}, {4, 7}},
                {{20, '+'}, {5, 3}, {5, 4}, {6, 3}, {6, 4}},
                {{5, '-'}, {5, 5}, {5, 6}},
                {{28, '*'}, {5, 7}, {6, 6}, {6, 7}},
                {{40, '*'}, {5, 8}, {6, 8}},
                {{2, '-'}, {6, 1}, {6, 2}},
                {{5, '-'}, {6, 5}, {7, 5}},
                {{4, '/'}, {7, 1}, {7, 2}},
                {{3, '/'}, {7, 3}, {7, 4}},
                {{1, '-'}, {7, 6}, {8, 6}},
                {{2, '-'}, {7, 7}, {8, 7}},
                {{2, '-'}, {7, 8}, {8, 8}},
                {{2, '-'}, {8, 0}, {8, 1}},
                {{10, '+'}, {8, 2}, {8, 3}, {8, 4}, {8, 5}}
        };
        int[][] expectedSolution = { //
                {4, 7, 1, 5, 6, 8, 2, 9, 3},
                {9, 1, 2, 4, 7, 3, 8, 5, 6},
                {8, 6, 9, 7, 1, 5, 3, 2, 4},
                {6, 4, 3, 8, 5, 7, 9, 1, 2},
                {5, 9, 7, 2, 8, 6, 4, 3, 1},
                {3, 8, 6, 1, 9, 2, 7, 4, 5},
                {2, 3, 5, 6, 4, 9, 1, 7, 8},
                {1, 2, 8, 9, 3, 4, 5, 6, 7},
                {7, 5, 4, 3, 2, 1, 6, 8, 9}
        };
        int[][][] testInput = deepCloneKenken(testInputOrg);
        int[][] studentSolution = KenKen.solve(testInputOrg);
        assertArrayEquals("kenkenExample: " + METHOD_NAME_KenKen_solve + " MODIFIED THE INPUT KenKen!", testInputOrg, testInput);
        assertNotNull("kenkenExampleOriginal: has a solution, but " + METHOD_NAME_KenKen_solve + " didn't find it.", studentSolution);
        assertArrayEquals("kenkenExampleOriginal: solution returned by " + METHOD_NAME_KenKen_solve + " is wrong.", expectedSolution, studentSolution);
    }

    /**
     * Tests whether checkIntegrity can successfully identify a valid (integrity wise) KenKen
     */
    @Test(timeout = 500)
    public void customTest_checkIntegrity_good() {
        int[][][] goodKenKen = new int[][][]{
                {{4, 45}, {0, 0}, {1, 1}},
                {{24, 42}, {0, 1}, {0, 2}, {0, 3}},
                {{15, 42}, {0, 4}, {0, 5}},
                {{12, 42}, {1, 1}, {1, 2}, {2, 2}},
                {{20, 42}, {1, 3}, {1, 4}, {1, 5}},
                {{1, 45}, {2, 0}, {2, 1}},
                {{11, 43}, {2, 3}, {2, 4}},
                {{3, 47}, {2, 5}, {3, 5}},
                {{1, 45}, {3, 0}, {3, 1}},
                {{120, 42}, {3, 2}, {4, 1}, {4, 2}, {5, 1}},
                {{2, 47}, {3, 3}, {3, 4}},
                {{3, 47}, {4, 0}, {5, 0}},
                {{36, 42}, {4, 3}, {4, 4}, {4, 5}},
                {{11, 43}, {5, 2}, {5, 3}},
                {{2, 45}, {5, 4}, {5, 5}},
        };
        assertEquals("Good KenKen, that complies with all integrity rules", 0, KenKen.checkIntegrity(goodKenKen));
    }

    /**
     * Tests every way in which a KenKen can be invalid (integrity wise)
     */
    @Test(timeout = 500)
    public void customTest_checkIntegrity_allFailures() {

        assertEquals("1. Check non null", 1, KenKen.checkIntegrity(null));
        assertEquals("1. Check at least one entry", 1, KenKen.checkIntegrity(new int[0][0][0]));

        int[][][] secondTestKenKenOne = new int[][][]{null, null};
        int[][][] secondTestKenKenTwo = new int[][][]{{{0, 0}}};

        assertEquals("2. Partition non null", 2, KenKen.checkIntegrity(secondTestKenKenOne));
        assertEquals("2. Partition at least two entries", 2, KenKen.checkIntegrity(secondTestKenKenTwo));

        int[][][] thirdTestKenKenOne = new int[][][]{
                {null, null, null},
                {null, null, null}
        };
        int[][][] thirdTestKenKenTwo = new int[][][]{{{0, 0, 1}, {1, 1}}};

        assertEquals("3. Partition sub entry non null", 3, KenKen.checkIntegrity(thirdTestKenKenOne));
        assertEquals("3. Partition sub entry length needs to be two", 3, KenKen.checkIntegrity(thirdTestKenKenTwo));

        int[][][] fourthTestKenKenOne = new int[][][]{{{100000, -99999}, {1, 1}}};
        int[][][] fourthTestKenKenTwo = new int[][][]{{{200000, 99999}, {1, 1}}};

        assertEquals("4. Result needs to be an int and operation a char", 4, KenKen.checkIntegrity(fourthTestKenKenOne));
        assertEquals("4. Result needs to be an int and operation a char", 4, KenKen.checkIntegrity(fourthTestKenKenTwo));

        int[][][] fifthTestKenKenOne = new int[][][]{{{0, 0}, {1, 1}}};
        int[][][] fifthTestKenKenTwo = new int[][][]{{{0, 44}, {1, 1}}};

        assertEquals("5. Operator needs to be a char describing '+', '-', '*', '/', ' '", 5, KenKen.checkIntegrity(fifthTestKenKenOne));
        assertEquals("5. Operator needs to be a char describing '+', '-', '*', '/', ' '", 5, KenKen.checkIntegrity(fifthTestKenKenTwo));

        int[][][] sixthTestKenKenOne = new int[][][]{{{0, 42}, {-1, 1}}};
        int[][][] sixthTestKenKenTwo = new int[][][]{{{0, 42}, {1, -1}}};
        int[][][] sixthTestKenKenThree = new int[][][]{{{0, 42}, {Integer.MIN_VALUE, 1}}};
        int[][][] sixthTestKenKenFour = new int[][][]{{{0, 42}, {1, Integer.MIN_VALUE}}};

        assertEquals("6. Each coordinate should be >= 0", 6, KenKen.checkIntegrity(sixthTestKenKenOne));
        assertEquals("6. Each coordinate should be >= 0", 6, KenKen.checkIntegrity(sixthTestKenKenTwo));
        assertEquals("6. Each coordinate should be >= 0", 6, KenKen.checkIntegrity(sixthTestKenKenThree));
        assertEquals("6. Each coordinate should be >= 0", 6, KenKen.checkIntegrity(sixthTestKenKenFour));

        int[][][] seventhTestKenKenOne = new int[][][]{{{5, 32}, {1, 1}, {2, 2}}};
        assertEquals("7. If the operator is ' ', there is only one sub entry allowed (following result,operation)", 7, KenKen.checkIntegrity(seventhTestKenKenOne));

        int[][][] eighthTestKenKenOne = new int[][][]{{{5, 45}, {1, 1}}};
        int[][][] eighthTestKenKenTwo = new int[][][]{{{5, 45}, {1, 1}, {2, 2}, {3, 3}}};
        int[][][] eighthTestKenKenThree = new int[][][]{{{5, 47}, {1, 1}}};
        int[][][] eighthTestKenKenFour = new int[][][]{{{5, 47}, {1, 1}, {2, 2}, {3, 3}}};


        assertEquals("8. If the operator is '-' or '/', then exactly two sub entries are required (following result,operation)", 8, KenKen.checkIntegrity(eighthTestKenKenOne));
        assertEquals("8. If the operator is '-' or '/', then exactly two sub entries are required (following result,operation)", 8, KenKen.checkIntegrity(eighthTestKenKenTwo));
        assertEquals("8. If the operator is '-' or '/', then exactly two sub entries are required (following result,operation)", 8, KenKen.checkIntegrity(eighthTestKenKenThree));
        assertEquals("8. If the operator is '-' or '/', then exactly two sub entries are required (following result,operation)", 8, KenKen.checkIntegrity(eighthTestKenKenFour));

        int[][][] ninthTestKenKenOne = new int[][][]{{{5, 43}, {1, 1}}};
        int[][][] ninthTestKenKenTwo = new int[][][]{{{5, 42}, {1, 1}}};

        assertEquals("9. If the operator is '+' or '*', then at least two sub entries are required (following result,operation)", 9, KenKen.checkIntegrity(ninthTestKenKenOne));
        assertEquals("9. If the operator is '+' or '*', then at least two sub entries are required (following result,operation)", 9, KenKen.checkIntegrity(ninthTestKenKenTwo));

    }

    /**
     * Tests most ways in which a KenKen can be invalid (validity wise)
     */
    @Test(timeout = 500)
    public void customTest_checkValidity_allFailures() {
        int[][][] sixthTestKenKenOne = new int[][][]{{{0, 42}, {-1, 1}}};
        assertEquals("1. Structural integrity (6. Each coordinate should be >= 0)", 1, KenKen.checkValidity(sixthTestKenKenOne));

        //<editor-fold defaultstate="collapsed" desc="KenKen">
        int[][][] badKenKenNotASquareTileMissing = new int[][][]{
                {{4, 45}, {0, 0}, {1, 1}},
                {{24, 42}, {0, 1}, {0, 2}, {0, 3}},
                {{15, 42}, {0, 4}, {0, 5}},
                {{12, 42}, {1, 1}, {1, 2}, {2, 2}},
                {{20, 42}, {1, 4}, {1, 5}},
                {{1, 45}, {2, 0}, {2, 1}},
                {{11, 43}, {2, 3}, {2, 4}},
                {{3, 47}, {2, 5}, {3, 5}},
                {{1, 45}, {3, 0}, {3, 1}},
                {{120, 42}, {3, 2}, {4, 1}, {4, 2}, {5, 1}},
                {{2, 47}, {3, 3}, {3, 4}},
                {{3, 47}, {4, 0}, {5, 0}},
                {{36, 42}, {4, 3}, {4, 4}, {4, 5}},
                {{11, 43}, {5, 2}, {5, 3}},
                {{2, 45}, {5, 4}, {5, 5}},
        };
        //</editor-fold>
        assertEquals("2. KenKen does not fully cover square field", 2, KenKen.checkValidity(badKenKenNotASquareTileMissing));

        //<editor-fold defaultstate="collapsed" desc="KenKen">
        int[][][] badKenKenNotASquareTileTooMuchInX = new int[][][]{
                {{4, 45}, {0, 0}, {1, 1}},
                {{24, 42}, {0, 1}, {0, 2}, {0, 3}},
                {{15, 42}, {0, 4}, {0, 5}},
                {{12, 42}, {1, 1}, {1, 2}, {2, 2}},
                {{20, 42}, {1, 3}, {1, 4}, {1, 5}},
                {{1, 45}, {2, 0}, {2, 1}},
                {{11, 43}, {2, 3}, {2, 4}},
                {{3, 47}, {2, 5}, {3, 5}},
                {{1, 45}, {3, 0}, {3, 1}},
                {{120, 42}, {3, 2}, {4, 1}, {4, 2}, {5, 1}},
                {{2, 47}, {3, 3}, {3, 4}},
                {{3, 47}, {4, 0}, {5, 0}},
                {{36, 42}, {4, 3}, {4, 4}, {4, 5}, {4, 6}},
                {{11, 43}, {5, 2}, {5, 3}},
                {{2, 45}, {5, 4}, {5, 5}},
        };
        //</editor-fold>
        assertEquals("2. KenKen does not fully cover square field (one square too much)", 2, KenKen.checkValidity(badKenKenNotASquareTileTooMuchInX));

        //<editor-fold defaultstate="collapsed" desc="KenKen">
        int[][][] badKenKenNotASquareTileTooMuchInY = new int[][][]{
                {{4, 45}, {0, 0}, {1, 1}},
                {{24, 42}, {0, 1}, {0, 2}, {0, 3}},
                {{15, 42}, {0, 4}, {0, 5}},
                {{12, 42}, {1, 1}, {1, 2}, {2, 2}},
                {{20, 42}, {1, 3}, {1, 4}, {1, 5}},
                {{1, 45}, {2, 0}, {2, 1}},
                {{11, 43}, {2, 3}, {2, 4}},
                {{3, 47}, {2, 5}, {3, 5}},
                {{1, 45}, {3, 0}, {3, 1}},
                {{120, 42}, {3, 2}, {4, 1}, {4, 2}, {5, 1}},
                {{2, 47}, {3, 3}, {3, 4}},
                {{3, 47}, {4, 0}, {5, 0}},
                {{36, 42}, {4, 3}, {4, 4}, {4, 5}},
                {{11, 43}, {5, 2}, {5, 3}},
                {{2, 45}, {5, 4}, {7, 5}},
        };
        //</editor-fold>
        assertEquals("2. KenKen does not fully cover square field (one square too much)", 2, KenKen.checkValidity(badKenKenNotASquareTileTooMuchInY));

        //<editor-fold defaultstate="collapsed" desc="KenKen">
        int[][][] badKenKenOverlappingOne = new int[][][]{
                {{4, 45}, {0, 0}, {1, 1}},
                {{24, 42}, {0, 1}, {0, 2}, {0, 3}},
                {{15, 42}, {0, 4}, {0, 5}},
                {{12, 42}, {1, 1}, {1, 2}, {2, 2}},
                {{20, 42}, {1, 3}, {1, 4}, {1, 5}, {2, 2}},
                {{1, 45}, {2, 0}, {2, 1}},
                {{11, 43}, {2, 3}, {2, 4}},
                {{3, 47}, {2, 5}, {3, 5}},
                {{1, 45}, {3, 0}, {3, 1}},
                {{120, 42}, {3, 2}, {4, 1}, {4, 2}, {5, 1}},
                {{2, 47}, {3, 3}, {3, 4}},
                {{3, 47}, {4, 0}, {5, 0}},
                {{36, 42}, {4, 3}, {4, 4}, {4, 5}},
                {{11, 43}, {5, 2}, {5, 3}},
                {{2, 45}, {5, 4}, {5, 5}},
        };
        //</editor-fold>
        assertEquals("2. KenKen does have overlapping partitions (one overlap)", 2, KenKen.checkValidity(badKenKenOverlappingOne));

        //<editor-fold defaultstate="collapsed" desc="KenKen">
        int[][][] badKenKenOverlappingMultiple = new int[][][]{
                {{4, 45}, {0, 0}, {1, 1}},
                {{24, 42}, {0, 1}, {0, 2}, {0, 3}},
                {{15, 42}, {0, 4}, {0, 5}},
                {{12, 42}, {1, 1}, {1, 2}, {2, 2}},
                {{20, 42}, {1, 3}, {1, 4}, {1, 5}, {2, 2}},
                {{1, 45}, {2, 0}, {2, 1}},
                {{11, 43}, {2, 3}, {2, 4}},
                {{3, 47}, {2, 5}, {3, 5}},
                {{1, 45}, {3, 0}, {3, 1}},
                {{120, 42}, {3, 2}, {4, 1}, {4, 2}, {5, 1}, {3, 2}},
                {{2, 47}, {3, 3}, {3, 4}},
                {{3, 47}, {4, 0}, {5, 0}},
                {{36, 42}, {4, 3}, {4, 4}, {4, 5}},
                {{11, 43}, {5, 2}, {5, 3}},
                {{2, 45}, {5, 4}, {5, 5}},
        };
        //</editor-fold>
        assertEquals("2. KenKen does have overlapping partitions (two overlaps)", 2, KenKen.checkValidity(badKenKenOverlappingMultiple));

        //<editor-fold defaultstate="collapsed" desc="KenKen">
        int[][][] badKenKenOverlappingOverlapInSamePartition = new int[][][]{
                {{4, 45}, {0, 0}, {1, 1}},
                {{24, 42}, {0, 1}, {0, 2}, {0, 3}},
                {{15, 42}, {0, 4}, {0, 5}},
                {{12, 42}, {1, 1}, {1, 2}, {2, 2}},
                {{20, 42}, {1, 3}, {1, 4}, {1, 5}},
                {{1, 45}, {2, 0}, {2, 1}},
                {{11, 43}, {2, 3}, {2, 4}},
                {{3, 47}, {2, 5}, {3, 5}},
                {{1, 45}, {3, 0}, {3, 1}},
                {{120, 42}, {3, 2}, {4, 1}, {4, 2}, {5, 1}, {3, 2}},
                {{2, 47}, {3, 3}, {3, 4}},
                {{3, 47}, {4, 0}, {5, 0}},
                {{36, 42}, {4, 3}, {4, 4}, {4, 5}},
                {{11, 43}, {5, 2}, {5, 3}},
                {{2, 45}, {5, 4}, {5, 5}},
        };
        //</editor-fold>
        assertEquals("2. KenKen does have overlapping partitions (one overlap)", 2, KenKen.checkValidity(badKenKenOverlappingOverlapInSamePartition));

    }

}
