package com.harrison.springer.model.command;

import com.harrison.springer.model.Canvas;

public class CreateCanvasCommand implements Command {
    private final int width;
    private final int height;

    public CreateCanvasCommand(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CreateCanvas;
    }

    @Override
    public boolean isValid() {
        return width > 0 && height > 0;
    }

    @Override
    public Canvas applyOnto(Canvas canvas) {
        return new Canvas(width, height);
    }

    @Override
    public String toString() {
        return String.format("Canvas[%d x %d]", width, height);
    }
}
