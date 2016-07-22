package com.harrison.springer.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PosTest {

    @Test
    public void validPosIfBothXAndYAreGreaterThanZero() {
        assertTrue(new Pos(1, 1).isValid());
    }

    @Test
    public void invalidXIfLessThanOne() {
        assertFalse(new Pos(0, 1).isValid());
    }

    @Test
    public void invalidYIfLessThanOne() {
        assertFalse(new Pos(1, 0).isValid());
    }

    @Test
    public void equalPos() {
        assertEquals(new Pos(1, 1), new Pos(1, 1));
    }
}