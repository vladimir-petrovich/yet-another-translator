package util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * RomanToDecimal Tester.
 */
public class RomanToDecimalTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: transform(String romanNumber)
     */
    @Test
    public void testRomanToDecimal() throws Exception {
        assertEquals(RomanToDecimal.transform("XIV"), 14);
        assertEquals(RomanToDecimal.transform("XII"), 12);
    }

} 
