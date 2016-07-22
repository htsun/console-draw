package com.harrison.springer.model.command;

import com.harrison.springer.model.Pos;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.harrison.springer.util.CanvasUtil.newPos;
import static org.testng.Assert.*;

public class DrawBoxCommandTest {

    @Test
    public void commandNeverQuit() {
        assertFalse(new DrawBoxCommand(newPos(1, 1), newPos(1, 2)).isQuit());
    }

    @Test
    public void checkCommandType() {
        assertEquals(new DrawBoxCommand(newPos(1, 1), newPos(1, 2)).getCommandType(), CommandType.DrawBox);
    }

    @DataProvider(name = "validBoxPosTestData")
    public Object[][] validBoxPosTestData() {
        return new Object[][]{
                { newPos(1, 4), newPos(7, 4), "horizontal line"},
                { newPos(1, 4), newPos(1, 10), "vertical line"},

                { newPos(7, 4), newPos(1, 4), "horizontal line, but with start and end reversed"},
                { newPos(1, 10), newPos(1, 4), "vertical line, but with start and end reversed"},
        };
    }

    @Test(dataProvider = "validBoxPosTestData")
    public void verifyPositionsAreValid(Pos start, Pos end, String comment) {
        assertTrue(new DrawBoxCommand(start, end).isValid());
    }

    @DataProvider(name = "invalidBoxPosTestData")
    public Object[][] invalidBoxPosTestData() {
        return new Object[][]{
                { newPos(0, 4), newPos(0, 9), "x is less than 1"},
                { newPos(5, -4), newPos(7, -4), "y is less than 1"},
        };
    }

    @Test(dataProvider = "invalidBoxPosTestData")
    public void verifyPositionsAreInvalid(Pos start, Pos end, String comment) {
        assertFalse(new DrawBoxCommand(start, end).isValid());
    }

}