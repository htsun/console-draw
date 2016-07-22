package com.harrison.springer.model.command;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class QuitCommandTest {

    @Test
    public void commandWillAlwaysQuit() {
        assertTrue(new QuitCommand().isQuit());
    }

    @Test
    public void checkCommandType() {
        assertEquals(new QuitCommand().getCommandType(), CommandType.Quit);
    }

    @Test
    public void commandIsAlwaysValid() {
        assertTrue(new QuitCommand().isValid());
    }
}