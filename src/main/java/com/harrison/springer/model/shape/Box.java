package com.harrison.springer.model.shape;

import com.harrison.springer.model.Canvas;
import com.harrison.springer.model.Pos;

import static com.harrison.springer.CanvasUtil.drawHorizontalLine;
import static com.harrison.springer.CanvasUtil.drawVerticalLine;

public class Box implements Shape {
    private static final char BOX_PIXEL_VALUE = 'x';
    private final Pos start;
    private final Pos end;

    public Box(Pos start, Pos end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Canvas applyOnto(Canvas canvas) {
        // TODO : normalize so we have top-left to bottom-right
        int topLeftX = start.getX();
        int topLeftY = start.getY();
        int bottomRightX = end.getX();
        int bottomRightY = end.getY();

        drawHorizontalLine(canvas, topLeftY, topLeftX, bottomRightX, BOX_PIXEL_VALUE);
        drawVerticalLine(canvas, topLeftX, topLeftY, bottomRightY, BOX_PIXEL_VALUE);
        drawVerticalLine(canvas, bottomRightX, topLeftY, bottomRightY, BOX_PIXEL_VALUE);
        drawHorizontalLine(canvas, bottomRightY, topLeftX, bottomRightX, BOX_PIXEL_VALUE);

        return canvas;
    }

}
