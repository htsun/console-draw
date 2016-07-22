package com.harrison.springer.model.command;

import com.harrison.springer.model.Canvas;
import com.harrison.springer.model.Pos;
import com.harrison.springer.model.shape.Line;

public class DrawLineCommand implements Command {
    private final Pos start;
    private final Pos end;

    public DrawLineCommand(Pos start, Pos end) {
        this.start = start;
        this.end = end;
    }

    public Pos getStartPosition() {
        return start;
    }

    public Pos getEndPosition() {
        return end;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DrawLine;
    }

    @Override
    public boolean isValid() {
        boolean isSameVertical = start.getX() == end.getX();
        boolean isSameHorizontal = start.getY() == end.getY();

        return start.isValid() && end.isValid() && (isSameVertical || isSameHorizontal);
    }

    @Override
    public Canvas applyOnto(Canvas canvas) {
        return new Line(start, end).applyOnto(canvas);
    }

    @Override
    public String toString() {
        return String.format("Line[%s -> %s]", start, end);
    }
}
