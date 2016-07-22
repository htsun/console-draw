package com.harrison.springer;

import com.harrison.springer.model.Pos;
import com.harrison.springer.model.command.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class CommandParserTest {
    private CommandParser parser;

    @BeforeMethod
    public void setUp() {
        parser = new CommandParser();
    }

    @Test
    public void parseQuitCommandSuccessfully() {
        InputStream inputStream = newInputStream("Q");
        Command command = parser.readCommand(inputStream);

        assertTrue(command.isQuit());
    }

    @Test
    public void parseCreateCanvasCommandSuccessfully() {
        InputStream inputStream = newInputStream("C 20 10");
        Command command = parser.readCommand(inputStream);

        assertEquals(command.getCommandType(), CommandType.CreateCanvas);

        CreateCanvasCommand realCommand = (CreateCanvasCommand) command;
        assertEquals(realCommand.getWidth(), 20);
        assertEquals(realCommand.getHeight(), 10);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void invalidCreateCanvasCommand() {
        InputStream inputStream = newInputStream("C -20 10");
        parser.readCommand(inputStream);

        fail("we should not be here");
    }

    @Test
    public void parseDrawLineCommandSuccessfully() {
        InputStream inputStream = newInputStream("L 1 2 6 2");
        Command command = parser.readCommand(inputStream);

        assertEquals(command.getCommandType(), CommandType.DrawLine);

        DrawLineCommand realCommand = (DrawLineCommand) command;
        assertEquals(realCommand.getStartPosition(), new Pos(1, 2));
        assertEquals(realCommand.getEndPosition(), new Pos(6, 2));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void invalidDrawLineCommand() {
        InputStream inputStream = newInputStream("L 1 2 6 3");
        parser.readCommand(inputStream);

        fail("we should not be here");
    }

    @Test
    public void parseDrawBoxCommandSuccessfully() {
        InputStream inputStream = newInputStream("R 1 2 6 2");
        Command command = parser.readCommand(inputStream);

        assertEquals(command.getCommandType(), CommandType.DrawBox);

        DrawBoxCommand realCommand = (DrawBoxCommand) command;
        assertEquals(realCommand.getStartPosition(), new Pos(1, 2));
        assertEquals(realCommand.getEndPosition(), new Pos(6, 2));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void invalidDrawBoxCommand() {
        InputStream inputStream = newInputStream("R 1 2 6 -3");
        parser.readCommand(inputStream);

        fail("we should not be here");
    }

    @Test
    public void parseBucketFillCommandSuccessfully() {
        InputStream inputStream = newInputStream("B 10 3 o");
        Command command = parser.readCommand(inputStream);

        assertEquals(command.getCommandType(), CommandType.BucketFill);

        BucketFillCommand realCommand = (BucketFillCommand) command;
        assertEquals(realCommand.getPos(), new Pos(10, 3));
        assertEquals(realCommand.getFillChar(), 'o');
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void invalidPosForBucketFillCommand() {
        InputStream inputStream = newInputStream("B -10 3 o");
        parser.readCommand(inputStream);

        fail("we should not be here");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void invalidFillCharForBucketFillCommand() {
        InputStream inputStream = newInputStream("B 10 3 xy");
        parser.readCommand(inputStream);

        fail("we should not be here");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void unrecognisedCommand() {
        InputStream inputStream = newInputStream("K 1 2 6 3");
        parser.readCommand(inputStream);

        fail("we should not be here");
    }

    private InputStream newInputStream(String text) {
        return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    }
}
