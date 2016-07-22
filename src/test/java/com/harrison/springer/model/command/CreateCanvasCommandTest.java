package com.harrison.springer.model.command;

import com.harrison.springer.model.Pos;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CreateCanvasCommandTest {

    @Test
    public void commandNeverQuit() {
        assertFalse(new CreateCanvasCommand(20, 4).isQuit());
    }

    @Test
    public void checkCommandType() {
        assertEquals(new CreateCanvasCommand(20, 4).getCommandType(), CommandType.CreateCanvas);
    }

    @DataProvider(name = "validCanvasDimensionTestData")
    public Object[][] validCanvasDimensionTestData() {
        return new Object[][]{
                { 1, 1, "single pixel box"},
                { 99999, 99999, "very large canvas"},
        };
    }

    @Test(dataProvider = "validCanvasDimensionTestData")
    public void verifyDimensionIsValid(int width, int height, String comment) {
        assertTrue(new CreateCanvasCommand(width, height).isValid());
    }

    @DataProvider(name = "invalidCanvasDimensionTestData")
    public Object[][] invalidCanvasDimensionTestData() {
        return new Object[][]{
                { 0, 1, "width cannot be <= 1"},
                { 99, 0, "height cannot be <= 1"},
        };
    }

    @Test(dataProvider = "invalidCanvasDimensionTestData")
    public void verifyDimensionIsInvalid(int width, int height, String comment) {
        assertFalse(new CreateCanvasCommand(width, height).isValid());
    }

}