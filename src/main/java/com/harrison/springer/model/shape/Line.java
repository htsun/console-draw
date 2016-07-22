package com.harrison.springer.model.shape;

import com.harrison.springer.model.Canvas;
import com.harrison.springer.model.Pos;

import static com.harrison.springer.CanvasUtil.drawHorizontalLine;
import static com.harrison.springer.CanvasUtil.drawVerticalLine;

public class Line implements Shape {
    private static final char LINE_PIXEL_VALUE = 'x';
    private final Pos start;
    private final Pos end;

    public Line(Pos start, Pos end) {
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

        if (topLeftX == bottomRightX) {
            drawVerticalLine(canvas, topLeftX, topLeftY, bottomRightY, LINE_PIXEL_VALUE);
        } else {
            drawHorizontalLine(canvas, topLeftY, topLeftX, bottomRightX, LINE_PIXEL_VALUE);
        }

        return canvas;
    }
}
