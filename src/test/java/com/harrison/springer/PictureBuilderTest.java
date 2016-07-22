package com.harrison.springer;

import com.harrison.springer.model.Canvas;
import com.harrison.springer.model.command.BucketFillCommand;
import com.harrison.springer.model.command.CreateCanvasCommand;
import com.harrison.springer.model.command.DrawBoxCommand;
import com.harrison.springer.model.command.DrawLineCommand;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.harrison.springer.util.CanvasUtil.*;
import static org.testng.Assert.assertEquals;

public class PictureBuilderTest {
    private PictureBuilder builder;

    @BeforeMethod
    public void setUp() {
        builder = new PictureBuilder();
    }

    @Test
    public void builderHasNoCanvasInitially() {
        assertEquals(builder.toCanvas(), Canvas.EMPTY);
    }

    @Test
    public void createCanvas() {
        builder.apply(new CreateCanvasCommand(20, 10));

        Canvas canvas = builder.toCanvas();
        assertEquals(canvas.getWidth(), 20);
        assertEquals(canvas.getHeight(), 10);

        assertBlankCanvas(canvas.toDisplay(false));
        dumpCanvas(canvas);
    }

    @Test
    public void drawHorizontalLineOnCanvas() {
        builder.apply(new CreateCanvasCommand(20, 4));
        builder.apply(new DrawLineCommand(newPos(1, 2), newPos(6, 2)));

        Canvas canvas = builder.toCanvas();
        assertCanvasContent(canvas, newCanvasContent("                    ",
                                                     "xxxxxx              ",
                                                     "                    ",
                                                     "                    "));
        dumpCanvas(canvas);
    }

    @Test
    public void drawVerticalLineOnCanvas() {
        builder.apply(new CreateCanvasCommand(20, 4));
        builder.apply(new DrawLineCommand(newPos(4, 1), newPos(4, 4)));

        Canvas canvas = builder.toCanvas();
        assertCanvasContent(canvas, newCanvasContent("   x                ",
                                                     "   x                ",
                                                     "   x                ",
                                                     "   x                "));
        dumpCanvas(canvas);
    }

    @Test
    public void drawBoxOnCanvas() {
        builder.apply(new CreateCanvasCommand(20, 4));
        builder.apply(new DrawBoxCommand(newPos(1, 1), newPos(4, 4)));

        Canvas canvas = builder.toCanvas();
        assertCanvasContent(canvas, newCanvasContent("xxxx                ",
                                                     "x  x                ",
                                                     "x  x                ",
                                                     "xxxx                "));
        dumpCanvas(canvas);
    }



    @Test
    public void fillCanvas() {
        builder.apply(new CreateCanvasCommand(20, 4));
        builder.apply(new BucketFillCommand(newPos(1, 1), 'o'));

        Canvas canvas = builder.toCanvas();
        assertCanvasContent(canvas, newCanvasContent("oooooooooooooooooooo",
                                                    "oooooooooooooooooooo",
                                                    "oooooooooooooooooooo",
                                                    "oooooooooooooooooooo"));
        dumpCanvas(canvas);
    }

    @Test
    public void drawComplicatedCanvas() {
        builder.apply(new CreateCanvasCommand(20, 4));
        builder.apply(new DrawLineCommand(newPos(1, 2), newPos(6, 2)));
        builder.apply(new DrawLineCommand(newPos(6, 3), newPos(6, 4)));
        builder.apply(new DrawBoxCommand(newPos(16, 1), newPos(20, 3)));
        builder.apply(new BucketFillCommand(newPos(10, 3), 'o'));

        Canvas canvas = builder.toCanvas();
        assertCanvasContent(canvas, newCanvasContent("oooooooooooooooxxxxx",
                                                    "xxxxxxooooooooox   x",
                                                    "     xoooooooooxxxxx",
                                                    "     xoooooooooooooo"));
        dumpCanvas(canvas);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void illegalToDrawOnUninitializedCanvas() {
        builder.apply(new DrawBoxCommand(newPos(1, 1), newPos(4, 4)));
    }

    private void assertBlankCanvas(List<String> canvasLines) {
        canvasLines.stream().allMatch(StringUtils::isBlank);
    }

}