package root.sqrt;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class AdvSqrtTest {
    AdvSqrt advSqrt;
    Sqrt defaultSqrt;


    @BeforeSuite
    public void setUp() {
        advSqrt = new AdvSqrt();
        defaultSqrt = new Sqrt();
    }

    /**
     * 1.
     */
    @Test
    public void testSqrtDefault() {
        advSqrt.sqrt(29);
//        for (double i = 10; i <= 100; ++i) {
//            System.out.println(i);
//                advSqrt.sqrt(i);
//        }
    }

    /**
     * 2. Checking result of AdvSqrt sqrt for negative numbers (-0 doesn't count) is NaN
     */
    @Test
    public void testSqrtNegative() {
        assertTrue(Double.isNaN(advSqrt.sqrt(-12194743148.316078)));
        assertTrue(Double.isNaN(advSqrt.sqrt(-71579819527.4548)));
        assertTrue(Double.isNaN(advSqrt.sqrt(-264135639469.93443928173321321)));
        assertTrue(Double.isNaN(advSqrt.sqrt(-9382176)));
        assertTrue(Double.isNaN(advSqrt.sqrt(-3253443653213L)));
        assertTrue(Double.isNaN(advSqrt.sqrt(Double.NEGATIVE_INFINITY)));
        assertFalse(Double.isNaN(advSqrt.sqrt(-0)));
    }

    /**
     * 3. Checking result of AdvSqrt sqrt for -0 is -0
     */
    @Test
    public void testSqrtMinusZero() {
        assertEquals(advSqrt.sqrt(-0), -0.0);
    }

    /**
     * 4. Checking result of AdvSqrt sqrt for Positive Infinity is Positive Infinity
     */
    @Test
    public void testSqrtPositiveInfinity() {
        assertEquals(advSqrt.sqrt(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
    }

    /**
     * 5. Checking result of AdvSqrt sqrt for NaN is NaN
     */
    @Test
    public void testSqrtNaN() {
        assertEquals(advSqrt.sqrt(Double.NaN), Double.NaN);
    }

}