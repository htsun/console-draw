package com.harrison.springer.model;

import java.util.ArrayList;
import java.util.List;

// NB : x and y are 1-based
public class Canvas {
    public static final Canvas EMPTY = new Canvas(0, 0);
    private static final char UNFILLED_PIXEL_VALUE = ' ';
    private final int width;
    private final int height;
    private final char[][] pixels;          // TODO : use a sparse array for large canvas ...

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new char[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char getPixel(Pos pos) {
        return getPixel(pos.getX(), pos.getY());
    }

    public char getPixel(int x, int y) {
        if (isWithinBound(x, y)) {
            return pixels[y - 1][x - 1] == 0 ? ' ' : pixels[y - 1][x - 1];
        } else {
            return '?';
        }
    }

    public void setPixel(Pos pos, char value) {
        setPixel(pos.getX(), pos.getY(), value);
    }

    public void setPixel(int x, int y, char value) {
        if (isWithinBound(x, y)) {
            pixels[y - 1][x - 1] = value;
        }
    }

    public boolean isVacant(Pos pos) {
        return getPixel(pos) == UNFILLED_PIXEL_VALUE;
    }

    private boolean isWithinBound(int x, int y) {
        return (x > 0 && x <= width) && (y > 0 && y <= height);
    }

    public List<String> toDisplay(boolean withFrame) {
        List<String> display = new ArrayList<>();
        String horzBoundary = repeat('-', width + 2);

        if (withFrame) {
            display.add(horzBoundary);
        }

        for (int y = 1; y <= height; y++) {
            if (withFrame) {
                display.add("|" + toLine(y) + "|");
            } else {
                display.add(toLine(y));
            }
        }

        if (withFrame) {
            display.add(horzBoundary);
        }

        return display;
    }

    private String toLine(int y) {
        StringBuilder sb = new StringBuilder(width);

        for (int x = 1; x <= width; x++) {
            sb.append(getPixel(x, y));
        }

        return sb.toString();
    }

    private String repeat(char c, int count) {
        StringBuilder sb = new StringBuilder(count);

        for (int i = 0; i < count; i++) {
            sb.append(c);
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("Canvas[%d x %d]", width, height);
    }
}
