package com.harrison.springer.model.shape;

import com.harrison.springer.model.Canvas;
import com.harrison.springer.model.Pos;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.harrison.springer.util.CanvasUtil.*;
import static org.testng.Assert.*;

public class LineTest {
    public static final int CANVAS_WIDTH = 20;
    public static final int CANVAS_HEIGHT = 4;
    private Canvas canvas;

    @BeforeMethod
    public void setUp() {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    @Test
    public void drawLeftmostVerticalLine() {
        canvas = new Line(newPos(1, 1), newPos(1, CANVAS_HEIGHT)).applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("x                   ",
                                                    "x                   ",
                                                    "x                   ",
                                                    "x                   "));
    }

    @Test
    public void drawRightmostVerticalLine() {
        canvas = new Line(newPos(CANVAS_WIDTH, 1), newPos(CANVAS_WIDTH, CANVAS_HEIGHT)).applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("                   x",
                                                    "                   x",
                                                    "                   x",
                                                    "                   x"));
    }

    @Test
    public void drawTopmostHorizontalLine() {
        canvas = new Line(newPos(1, 1), newPos(CANVAS_WIDTH, 1)).applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("xxxxxxxxxxxxxxxxxxxx",
                                                    "                    ",
                                                    "                    ",
                                                    "                    "));
    }

    @Test
    public void drawBottommostHorizontalLine() {
        canvas = new Line(newPos(1, CANVAS_HEIGHT), newPos(CANVAS_WIDTH, CANVAS_HEIGHT)).applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("                    ",
                                                    "                    ",
                                                    "                    ",
                                                    "xxxxxxxxxxxxxxxxxxxx"));
    }

    @Test
    public void drawADot() {
        canvas = new Line(newPos(5, 2), newPos(5, 2)).applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("                    ",
                "    x               ",
                "                    ",
                "                    "));
    }

    @Test
    public void drawOutsideOfCanvas() {
        canvas = new Line(newPos(5, CANVAS_HEIGHT), newPos(CANVAS_WIDTH + 5, CANVAS_HEIGHT)).applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("                    ",
                "                    ",
                "                    ",
                "    xxxxxxxxxxxxxxxx"));       // so remaining part of line is clipped
    }

}