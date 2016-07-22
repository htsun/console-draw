package com.harrison.springer.model.command;

import com.harrison.springer.model.Pos;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BucketFillCommandTest {

    @Test
    public void commandNeverQuit() {
        assertFalse(new BucketFillCommand(newPos(1, 1), 'o').isQuit());
    }

    @Test
    public void checkCommandType() {
        assertEquals(new BucketFillCommand(newPos(1, 1), 'o').getCommandType(), CommandType.BucketFill);
    }

    private Pos newPos(int x, int y) {
        return new Pos(x, y);
    }
}