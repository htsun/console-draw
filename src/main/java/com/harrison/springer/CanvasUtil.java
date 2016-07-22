package com.harrison.springer;

import com.harrison.springer.model.Canvas;

public class CanvasUtil {

    public static void drawVerticalLine(Canvas canvas, int x, int startY, int endY, char pixelValue) {
        for (int y = startY; y <= endY; y++) {
            canvas.setPixel(x, y, pixelValue);
        }
    }

    public static void drawHorizontalLine(Canvas canvas, int y, int startX, int endX, char pixelValue) {
        for (int x = startX; x <= endX; x++) {
            canvas.setPixel(x, y, pixelValue);
        }
    }
}
