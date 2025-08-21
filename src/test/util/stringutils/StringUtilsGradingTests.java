package stringutils;

import static org.junit.Assert.*;
import static stringutils.StringUtils.*;

import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.rules.TestWatcher;

public class StringUtilsGradingTests {

    // =================================================
    // Test Configuration
    // =================================================

    // Global timeout to prevent infinite loops from
    // crashing the test suite
    @Rule
    public Timeout globalTimeout = Timeout.seconds(1);

    // Each time you pass a test, you get a point! Yay!
    // [!] Requires JUnit 4+ to run
    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            passed++;
        }
    };

    // Grade record-keeping
    static int possible = 0, passed = 0;

    // The @Before mmethod is run before every @Test
    @Before
    public void init () {
        possible++;
    }

    // Used for grading, reports the total number of tests
    // passed over the total possible
    @AfterClass
    public static void gradeReport () {
        System.out.println("============================");
        System.out.println("String Utils Tests Complete");
        System.out.println(passed + " / " + possible + " passed!");
        if ((1.0 * passed / possible) >= 0.9) {
            System.out.println("[!] Nice job!"); // Automated acclaim!
        }
        System.out.println("============================");
    }


    // =================================================
    // Unit Tests
    // =================================================
    // For grading purposes, every method and unit is
    // weighted equally and totaled for the score.
    // The tests increase in difficulty such that the
    // basics are unlabeled and harder tiers are tagged
    // t0, t1, t2, t3, ... easier -> harder
    //
    // Typically, you cluster tests for each method
    // by particular types of inputs, e.g., empty String
    // inputs vs. single words vs. multiple words (the
    // "zero-one-infinity" rule of testing) or tests for
    // proper error handling. Some of the below may be
    // organized for grading purposes instead.


    // hasSequence Tests
    // -------------------------------------------------

    @Test
    public void hasSequence_t0() {
        assertTrue(hasSequence("GGGG", "GG"));
        assertTrue(hasSequence("GTGTGTG", "GG"));
    }

    @Test
    public void hasSequence_t1() {
        assertFalse(hasSequence("GTC", "CGT"));
        assertFalse(hasSequence("TTT", "TTTT"));
    }

    @Test
    public void hasSequence_t2() {
        assertTrue(hasSequence("CCCC", "CC"));
        assertTrue(hasSequence("CGATTAGC", "CATTC"));
    }

    @Test
    public void hasSequence_t3() {
        assertTrue(hasSequence("CCCC", ""));
        assertTrue(hasSequence("", ""));
    }

    @Test
    public void hasSequence_t4() {
        assertFalse(hasSequence("", "A"));
        assertTrue(hasSequence("T", "T"));
    }

    @Test
    public void hasSequence_t5() {
        assertFalse(hasSequence("CAT", "CATA"));
        assertTrue(hasSequence("CAT", "T"));
    }

    @Test
    public void hasSequence_t6() {
        assertFalse(hasSequence("ACT", "TCA"));
        assertTrue(hasSequence("GGTAC", "TAC"));
    }


    // sentCap Tests
    // -------------------------------------------------

    @Test
    public void sentCap_t0() {
        assertEquals("Yo.", sentCap("Yo."));
    }

    @Test
    public void sentCap_t1() {
        assertEquals("Hello. My name is Java.", sentCap("Hello. my name is Java."));
    }

    @Test
    public void sentCap_t2() {
        assertEquals("I.   Like.    Spaces.", sentCap("I.   like.    spaces."));
    }

    @Test
    public void sentCap_t3() {
        assertEquals("I.   Like.    Spaces", sentCap("I.   like.    spaces"));
    }

    @Test
    public void sentCap_t4() {
        assertEquals("", sentCap(""));
    }

    @Test
    public void sentCap_t5() {
        assertEquals("   ", sentCap("   "));
    }

    @Test
    public void sentCap_t6() {
        assertEquals(".", sentCap("."));
        assertEquals("  .  ", sentCap("  .  "));
    }

    @Test
    public void sentCap_t7() {
        assertEquals("    Test.  With some. Spaces.    ", sentCap("    Test.  with some. spaces.    "));
    }


    // getNthMatch Tests
    // -------------------------------------------------

    @Test
    public void getNthMatch_t0() {
        assertEquals("test", getNthMatch("test test test", "test", 0));
        assertEquals("test", getNthMatch("test test test", "test", 1));
        assertEquals("test", getNthMatch("test test test", "test", 2));
        assertEquals(null, getNthMatch("test test test", "test", 3));
    }

    @Test
    public void getNthMatch_t1() {
        assertEquals("test", getNthMatch("test Test tEsT", "test", 0));
        assertEquals("Test", getNthMatch("test Test tEsT", "test", 1));
        assertEquals("tEsT", getNthMatch("test Test tEsT", "test", 2));
        assertEquals(null, getNthMatch("test Test tEsT", "test", 3));
    }

    @Test
    public void getNthMatch_t2() {
        assertEquals(null, getNthMatch("test Test tEsT", "notest", 1));
    }

    @Test
    public void getNthMatch_t3() {
        assertEquals("test", getNthMatch("test this Test This tEsT", "test", 0));
        assertEquals("Test", getNthMatch("test this Test This tEsT", "test", 1));
        assertEquals("tEsT", getNthMatch("test this Test This tEsT", "test", 2));
        assertEquals(null, getNthMatch("test this Test This tEsT", "test", 3));
    }

    @Test
    public void getNthMatch_t4() {
        assertEquals("this", getNthMatch("test this Test This tEsT", "tHiS", 0));
        assertEquals("This", getNthMatch("test this Test This tEsT", "tHiS", 1));
        assertEquals(null, getNthMatch("test this Test This tEsT", "tHiS", 1000));
    }

    @Test
    public void getNthMatch_t5() {
        assertEquals("test", getNthMatch("test", "test", 0));
        assertEquals(null, getNthMatch("test", "test", 1));
    }

    @Test
    public void getNthMatch_t6() {
        assertThrows(IllegalArgumentException.class, () -> getNthMatch("test", "", 0));
        assertThrows(IllegalArgumentException.class, () -> getNthMatch("test", "test", -1));
    }


}

