package com.harrison.springer.model.action;

import com.harrison.springer.model.Canvas;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.harrison.springer.util.CanvasUtil.*;

public class BucketFillActionTest {
    public static final int CANVAS_WIDTH = 20;
    public static final int CANVAS_HEIGHT = 4;
    private Canvas canvas;

    @BeforeMethod
    public void setUp() {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    @Test
    public void bucketFillEntireCanvas() {
        canvas = new BucketFillAction(newPos(1, 1), 'o').applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("oooooooooooooooooooo",
                                                    "oooooooooooooooooooo",
                                                    "oooooooooooooooooooo",
                                                    "oooooooooooooooooooo"));
    }

    @Test
    public void bucketFillBox() {
        canvas = new BucketFillAction(newPos(1, 1), 'o').applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("oooooooooooooooooooo",
                                                    "oooooooooooooooooooo",
                                                    "oooooooooooooooooooo",
                                                    "oooooooooooooooooooo"));
    }

    @Test
    public void bucketFillLeftHalfBox() {
        canvas.setPixel(10, 1, '|');
        canvas.setPixel(10, 2, '|');
        canvas.setPixel(10, 3, '|');
        canvas.setPixel(10, 4, '|');
        canvas = new BucketFillAction(newPos(1, 1), 'o').applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("ooooooooo|          ",
                                                    "ooooooooo|          ",
                                                    "ooooooooo|          ",
                                                    "ooooooooo|          "));
    }

    @Test
    public void bucketFillRightHalfBox() {
        canvas.setPixel(10, 1, '|');
        canvas.setPixel(10, 2, '|');
        canvas.setPixel(10, 3, '|');
        canvas.setPixel(10, 4, '|');
        canvas = new BucketFillAction(newPos(15, 3), 'o').applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("         |oooooooooo",
                                                    "         |oooooooooo",
                                                    "         |oooooooooo",
                                                    "         |oooooooooo"));
    }

    @Test
    public void bucketFillTopRightCornerBox() {
        canvas.setPixel(10, 1, '|');
        canvas.setPixel(10, 2, '|');
        canvas.setPixel(10, 3, '+');
        canvas.setPixel(11, 3, '-');
        canvas.setPixel(12, 3, '-');
        canvas.setPixel(13, 3, '-');
        canvas.setPixel(14, 3, '-');
        canvas.setPixel(15, 3, '-');
        canvas.setPixel(16, 3, '-');
        canvas.setPixel(17, 3, '-');
        canvas.setPixel(18, 3, '-');
        canvas.setPixel(19, 3, '-');
        canvas.setPixel(20, 3, '-');
        canvas = new BucketFillAction(newPos(15, 2), 'o').applyOnto(canvas);
        dumpCanvas(canvas);
        assertCanvasContent(canvas, newCanvasContent("         |oooooooooo",
                                                    "         |oooooooooo",
                                                    "         +----------",
                                                    "                    "));
    }
}