package com.harrison.springer.model.shape;

import com.harrison.springer.model.Canvas;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.harrison.springer.util.CanvasUtil.*;
import static org.testng.Assert.*;

public class BoxTest {
    public static final int CANVAS_WIDTH = 20;
    public static final int CANVAS_HEIGHT = 4;
    private Canvas canvas;

    @BeforeMethod
    public void setUp() {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    @Test
    public void boxEntireCanvas() {
        canvas = new Box(newPos(1, 1), newPos(CANVAS_WIDTH, CANVAS_HEIGHT)).applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("xxxxxxxxxxxxxxxxxxxx",
                                                    "x                  x",
                                                    "x                  x",
                                                    "xxxxxxxxxxxxxxxxxxxx"));
    }

    @Test
    public void boxAsADot() {
        canvas = new Box(newPos(3, 3), newPos(3, 3)).applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("                    ",
                                                    "                    ",
                                                    "  x                 ",
                                                    "                    "));
    }

    @Test
    public void drawOutsideOfCanvas() {
        canvas = new Box(newPos(5, 2), newPos(CANVAS_WIDTH + 5, CANVAS_HEIGHT + 1)).applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("                    ",
                "    xxxxxxxxxxxxxxxx",
                "    x               ",
                "    x               "));       // so remaining part of box is clipped
    }
}