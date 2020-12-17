import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class VektorCustomTest {

    @Test
    public void customTest_constructor_dimension() {
        double[] bigExpected = new double[Integer.MAX_VALUE / 32];
        Assert.assertArrayEquals(bigExpected, new Vektor(Integer.MAX_VALUE / 32).komponenten, 0);

        double[] shortExpected = new double[0];
        Assert.assertArrayEquals(shortExpected, new Vektor(0).komponenten, 0);
    }

    @Test
    public void customTest_constructor_komponenten() {
        double[] bigExpected = new double[Integer.MAX_VALUE / 32];
        Vektor de = new Vektor(bigExpected);
        Assert.assertArrayEquals(bigExpected, de.komponenten, 0);
        Assert.assertNotSame(bigExpected, de.komponenten);

        double[] shortExpected = new double[0];
        Assert.assertArrayEquals(shortExpected, new Vektor(shortExpected).komponenten, 0);

        double[] highValues = new double[]{Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
        double[] temp = highValues.clone();
        Vektor gen = new Vektor(highValues);
        Assert.assertArrayEquals(highValues, gen.komponenten, 0);
        highValues[0] = -1;
        highValues[2] = -1;
        Assert.assertArrayEquals(temp, gen.komponenten, 0);


        double[] lowValues = new double[]{Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};
        temp = lowValues.clone();
        gen = new Vektor(lowValues);
        Assert.assertArrayEquals(lowValues, gen.komponenten, 0);
        lowValues[0] = -1;
        lowValues[2] = -1;
        Assert.assertArrayEquals(temp, gen.komponenten, 0);
    }

    @Test
    public void customTest_constructor_vektor() {

        Vektor low = new Vektor(new double[]{Double.MIN_VALUE, Double.MIN_VALUE});
        double[] temp = low.komponenten.clone();
        Vektor gen = new Vektor(low);
        Assert.assertArrayEquals(low.komponenten, gen.komponenten, 0);
        low.komponenten[0] = -1;
        Assert.assertArrayEquals(temp, gen.komponenten, 0);


        Vektor high = new Vektor(new double[]{Double.MAX_VALUE, Double.MAX_VALUE});
        temp = high.komponenten.clone();
        gen = new Vektor(high);
        Assert.assertArrayEquals(high.komponenten, gen.komponenten, 0);
        high.komponenten[0] = -1;
        Assert.assertArrayEquals(temp, gen.komponenten, 0);


    }

    @Test
    public void customTest_dimension() {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();

        for (int i = 0; i < 10; i++)
            Assert.assertEquals(i, new Vektor(i).dimension());

        for (int i = 0; i < 4096; i++) {
            int currentRandom = tlr.nextInt(131072);
            Assert.assertEquals(currentRandom, new Vektor(currentRandom).dimension());
        }

        double[] bigExpected = new double[Integer.MAX_VALUE / 32];
        Assert.assertEquals(bigExpected.length, new Vektor(bigExpected.length).dimension());

        double[] shortExpected = new double[0];
        Assert.assertEquals(shortExpected.length, new Vektor(shortExpected.length).dimension());
    }

    @Test
    public void customTest_komponente() {
        Vektor low = new Vektor(new double[]{Double.MIN_VALUE, Double.MIN_VALUE});
        Assert.assertEquals(low.komponente(0), Double.MIN_VALUE, 0);
        Assert.assertEquals(low.komponente(1), Double.MIN_VALUE, 0);


        Vektor high = new Vektor(new double[]{Double.MAX_VALUE, Double.MAX_VALUE});
        Assert.assertEquals(high.komponente(0), Double.MAX_VALUE, 0);
        Assert.assertEquals(high.komponente(1), Double.MAX_VALUE, 0);

        Vektor magic = new Vektor(new double[]{Math.PI, Math.E});
        Assert.assertEquals(magic.komponente(0), Math.PI, 0);
        Assert.assertEquals(magic.komponente(1), Math.E, 0);
    }

    @Test
    public void customTest_initialisiere() {
        double[] aA = new double[]{1, 10, 20, 30, 50};
        Vektor a = new Vektor(aA);

        Assert.assertNotSame(aA, a.komponenten);
        Assert.assertArrayEquals(aA, a.komponenten, 0);

        double[] bA = new double[]{Double.MAX_VALUE, 10, 20, 2, 50};
        Vektor b = new Vektor(bA);

        Assert.assertNotSame(bA, b.komponenten);
        Assert.assertArrayEquals(bA, b.komponenten, 0);

        double[] cA = new double[]{1, 30, Double.MIN_VALUE};
        Vektor c = new Vektor(cA);

        Assert.assertNotSame(cA, c.komponenten);
        Assert.assertArrayEquals(cA, c.komponenten, 0);

        a.initialisiere(bA);
        Assert.assertNotSame(bA, a.komponenten);
        Assert.assertArrayEquals(bA, a.komponenten, 0);

        a.initialisiere(cA);
        Assert.assertArrayEquals(bA, a.komponenten, 0);

        b.initialisiere(aA);
        Assert.assertNotSame(aA, b.komponenten);
        Assert.assertArrayEquals(aA, b.komponenten, 0);

        b.initialisiere(cA);
        Assert.assertArrayEquals(aA, b.komponenten, 0);

        c.initialisiere(aA);
        Assert.assertNotSame(cA, c.komponenten);
        Assert.assertArrayEquals(cA, c.komponenten, 0);

    }

    @Test
    public void customTest_skalarMultiplikation() {
        Vektor a = new Vektor(new double[]{28231, 9540, 273, 0});
        a.skalarMultiplikation(5.5);
        Assert.assertArrayEquals(new double[]{28231 * 5.5, 9540 * 5.5, 273 * 5.5, 0}, a.komponenten, 0);
        a.skalarMultiplikation(0);
        Assert.assertArrayEquals(new double[4], a.komponenten, 0);

        Vektor b = new Vektor(new double[]{Double.MAX_VALUE, Double.MIN_VALUE, Math.PI, Math.E, 1});
        b.skalarMultiplikation(0);
        Assert.assertArrayEquals(new double[5], b.komponenten, 0);
        b = new Vektor(new double[]{Integer.MAX_VALUE, Double.MIN_VALUE, Math.PI, Math.E, 1});
        b.skalarMultiplikation(-2391.2);
        Assert.assertArrayEquals(new double[]{Integer.MAX_VALUE * -2391.2, Double.MIN_VALUE * -2391.2, Math.PI * -2391.2, Math.E * -2391.2, -2391.2}, b.komponenten, 0);

        Vektor c = new Vektor(new double[]{-1, -2, -3, 21831287});
        c.skalarMultiplikation(-99.9999);
        Assert.assertArrayEquals(new double[]{99.9999, 199.9998, 299.9997, -2.1831265168713e9}, c.komponenten, 0.0001);
        c.skalarMultiplikation(0);
        Assert.assertArrayEquals(new double[4], c.komponenten, 0);

    }

    @Test
    public void customTest_skalarProdukt() {

        Vektor a = new Vektor(new double[]{0, 2, 3, 4});
        Vektor b = new Vektor(new double[]{9, 2, 5, 4});

        Assert.assertEquals(a.skalarProdukt(b), Vektor.skalarProdukt(a, b), 0);
        Assert.assertEquals(35, a.skalarProdukt(b), 0);
        Assert.assertEquals(Double.NaN, a.skalarProdukt(new Vektor(0)), 0);
        Assert.assertEquals(Double.NaN, a.skalarProdukt(new Vektor(a.komponenten.length + 1)), 0);
        Assert.assertEquals(Double.NaN, a.skalarProdukt(new Vektor(10000)), 0);

        a = new Vektor(new double[]{2831283, 3271752735d, 123, 92});
        b = new Vektor(new double[]{12343213, 2831, 129319, 238823});
        Assert.assertEquals(Vektor.skalarProdukt(a, b), a.skalarProdukt(b), 0);
        Assert.assertEquals(44209499003017d, a.skalarProdukt(b), 0);
        Assert.assertEquals(Double.NaN, a.skalarProdukt(new Vektor(0)), 0);
        Assert.assertEquals(Double.NaN, a.skalarProdukt(new Vektor(a.komponenten.length + 1)), 0);
        Assert.assertEquals(Double.NaN, a.skalarProdukt(new Vektor(10000)), 0);

        b = new Vektor(new double[]{0, 0, 0, 0});
        Assert.assertEquals(a.skalarProdukt(b), Vektor.skalarProdukt(a, b), 0);
        Assert.assertEquals(a.skalarProdukt(b), 0, 0);
    }

    @Test
    public void customTest_addition() {
        Vektor a = new Vektor(new double[]{0, 0, 0});
        Vektor b = new Vektor(new double[]{0, 0, 0});
        a.addition(b);
        Assert.assertArrayEquals(new double[]{0, 0, 0}, a.komponenten, 0);
        b.initialisiere(new double[]{2, 3, 5});
        a.addition(b);
        Assert.assertArrayEquals(new double[]{2, 3, 5}, a.komponenten, 0);


        a = new Vektor(new double[]{Double.MIN_VALUE, Double.MIN_VALUE});
        b = new Vektor(new double[]{Double.MAX_VALUE / 2, Double.MAX_VALUE / 2});
        a.addition(b);
        Assert.assertArrayEquals(new double[]{8.988465674311579e307, 8.988465674311579e307}, a.komponenten, 0);

        b = new Vektor(new double[]{100, 100, 100});
        a.addition(b);
        Assert.assertArrayEquals(new double[]{8.988465674311579e307, 8.988465674311579e307}, a.komponenten, 0);

    }

    @Test
    public void customTest_addition_varargs() {
        Vektor a = new Vektor(new double[]{0, 0, 0});
        Vektor b = new Vektor(new double[]{0, 0, 0});
        Vektor c = Vektor.addition(a, b);
        Assert.assertArrayEquals(new double[]{0, 0, 0}, c.komponenten, 0);
        b.initialisiere(new double[]{2, 3, 5});
        c = Vektor.addition(a, b);
        Assert.assertArrayEquals(new double[]{2, 3, 5}, c.komponenten, 0);

        a = new Vektor(new double[]{Double.MIN_VALUE, Double.MIN_VALUE});
        b = new Vektor(new double[]{Double.MAX_VALUE / 2, Double.MAX_VALUE / 2});
        c = Vektor.addition(a, b);
        Assert.assertArrayEquals(new double[]{8.988465674311579e307, 8.988465674311579e307}, c.komponenten, 0);

        a = new Vektor(new double[]{1, 2, 3});
        c = Vektor.addition(a, b);
        Assert.assertNull(c);
    }

    @Test
    public void customTest_matrixVektorProdukt() {

        double[][] matrix = new double[][]{
                {10, 203, 1283213, 21381283, 28},
                {29391, 1283, 1238, 213, 12222222},
                {1239, 12123, 1, 0, 239}
        };

        Vektor v = new Vektor(new double[]{200, 2381283, 1293, 123123, 9923});

        Vektor r = v.matrixVektorProdukt(matrix);

        Assert.assertArrayEquals(new double[]{2634670581511d, 124369999128d, 28870914499d}, r.komponenten, 0);

        matrix = new double[0][0];
        r = v.matrixVektorProdukt(matrix);
        Assert.assertNull(r);

        matrix = new double[][]{
                {10, -1, 1283213, 21381283, -28},
                {-99999999999d, 1283, 91466, 213, -4520},
                {0, -696969, -32, 8222222, -23123}
        };
        v = new Vektor(new double[]{-1023213, -238, -2828282, 23823, -9923});
        r = v.matrixVektorProdukt(matrix);
        Assert.assertArrayEquals(new double[]{-3119931879205d, 102321041356956280d, 196363827881d}, r.komponenten, 0);
        matrix = new double[0][0];
        r = v.matrixVektorProdukt(matrix);
        Assert.assertNull(r);
    }

    @Test
    public void customTest_norm() {
        Vektor a = new Vektor(new double[]{-1, -1, -1});
        Vektor b = new Vektor(new double[]{1, 1, 1});
        Assert.assertEquals(a.norm(), b.norm(), 0);

        a = new Vektor(0);
        Assert.assertEquals(0, a.norm(), 0);

        ThreadLocalRandom tlr = ThreadLocalRandom.current();

        for (int i = 0; i < 1024; i++) {
            double v1 = tlr.nextDouble(-4096, 4096), v2 = tlr.nextDouble(-4096, 4096), v3 = tlr.nextDouble(-4096, 4096);
            a = new Vektor(new double[]{v1, v2, v3});
            b = new Vektor(new double[]{-v1, -v2, -v3});
            Assert.assertEquals(a.norm(), b.norm(), 0);
        }

        a = new Vektor(2);
        Assert.assertEquals(0, a.norm(), 0);

        a = new Vektor(new double[]{-12838123, 1823812381238d});
        Assert.assertEquals(1.82381238128318485669266309716425701374440597468956972548062e12, a.norm(), 0);

    }

    @Test
    public void customTest_normiere() {
        Vektor a = new Vektor(new double[]{-12838123, 1823812381238d});
        double[] exp = new double[]{-12838123 / 1.82381238128318485669266309716425701374440597468956972548062e12, 1823812381238d / 1.82381238128318485669266309716425701374440597468956972548062e12};
        a.normiere();
        Assert.assertArrayEquals(exp, a.komponenten, 0.000001);
    }

    @Test
    public void customTest_klone() {

        double[] a1 = new double[]{Math.PI, Math.E};
        double[] a2 = a1.clone();
        Vektor a = new Vektor(a1);
        a1[0] = -1;
        Assert.assertNotSame(a1, a.komponenten);
        Assert.assertArrayEquals(a2, a.komponenten, 0);

        double[] b1 = new double[0];
        double[] b2 = b1.clone();
        Vektor b = new Vektor(b1);
        Assert.assertNotSame(b1, b.komponenten);
        Assert.assertArrayEquals(b2, b.komponenten, 0);


    }

}