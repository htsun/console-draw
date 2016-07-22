package com.harrison.springer.model.command;

import com.harrison.springer.model.Pos;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.harrison.springer.util.CanvasUtil.newPos;
import static org.testng.Assert.*;

public class DrawLineCommandTest {

    @Test
    public void commandNeverQuit() {
        assertFalse(new DrawLineCommand(newPos(1, 1), newPos(1, 2)).isQuit());
    }

    @Test
    public void checkCommandType() {
        assertEquals(new DrawLineCommand(newPos(1, 1), newPos(1, 2)).getCommandType(), CommandType.DrawLine);
    }

    @DataProvider(name = "validLinePosTestData")
    public Object[][] validLinePosTestData() {
        return new Object[][]{
                { newPos(1, 4), newPos(7, 4), "horizontal line"},
                { newPos(1, 4), newPos(1, 10), "vertical line"},

                { newPos(7, 4), newPos(1, 4), "horizontal line, but with start and end reversed"},
                { newPos(1, 10), newPos(1, 4), "vertical line, but with start and end reversed"},
        };
    }

    @Test(dataProvider = "validLinePosTestData")
    public void verifyPositionsAreValid(Pos start, Pos end, String comment) {
        assertTrue(new DrawLineCommand(start, end).isValid());
    }

    @DataProvider(name = "invalidLinePosTestData")
    public Object[][] invalidLinePosTestData() {
        return new Object[][]{
                { newPos(1, 4), newPos(2, 9), "diagonal line"},
                { newPos(0, 4), newPos(0, 9), "x is less than 1"},
                { newPos(5, -4), newPos(7, -4), "y is less than 1"},
        };
    }

    @Test(dataProvider = "invalidLinePosTestData")
    public void verifyPositionsAreInvalid(Pos start, Pos end, String comment) {
        assertFalse(new DrawLineCommand(start, end).isValid());
    }

}