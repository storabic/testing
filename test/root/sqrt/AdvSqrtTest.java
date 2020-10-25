package root.sqrt;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class AdvSqrtTest {
    private AdvSqrt advSqrt;
    private Sqrt defaultSqrt;

    @BeforeSuite
    public void setUp() {
        advSqrt = new AdvSqrt();
        defaultSqrt = new Sqrt();
    }

    /**
     * Method-helper
     * @param actual   first double value
     * @param expected second double value
     * @return boolean true if one double is equal to other or one step away in mantissa
     */
    private boolean checkEquality(double actual, double expected) {
        long advSqrtBits = Double.doubleToLongBits(actual);
        long defaultSqrtBits = Double.doubleToLongBits(expected);
        long difference = Math.abs(advSqrtBits - defaultSqrtBits);
        return difference <= 1;
    }

    /**
     * 1. Checking result of AdvSqrt sqrt for non-negative double numbers is equal to default sqrt result
     * or one step away in mantissa from default sqrt result
     */
    @Test
    public void testDefault() {
        //some dummy numbers
        for (double i = 0; i < 10087; ++i)
            assertTrue(checkEquality(advSqrt.sqrt(i), defaultSqrt.sqrt(i)));

        //normalized numbers
        double[] normalized = new double[]{Double.longBitsToDouble(0x0421234567890123L),
                Double.longBitsToDouble(0x0790000000000000L), Double.longBitsToDouble(0x0010000000000000L)};
        for (double n : normalized)
            assertTrue(checkEquality(advSqrt.sqrt(n), defaultSqrt.sqrt(n)));

        //denormalized numbers
        double[] denormalized = new double[]{Double.longBitsToDouble(0x0001234567890123L),
                Double.longBitsToDouble(0x0001234567890123L)};
        for (double d : denormalized)
            assertTrue(checkEquality(advSqrt.sqrt(d), defaultSqrt.sqrt(d)));
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