package com.harrison.springer.model.command;

import com.harrison.springer.model.Canvas;
import com.harrison.springer.model.Pos;
import com.harrison.springer.model.shape.Box;

public class DrawBoxCommand implements Command {
    private final Pos start;
    private final Pos end;

    public DrawBoxCommand(Pos start, Pos end) {
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
        return CommandType.DrawBox;
    }

    @Override
    public boolean isValid() {
        return start.isValid() && end.isValid();
    }

    @Override
    public Canvas applyOnto(Canvas canvas) {
        return new Box(start, end).applyOnto(canvas);
    }

    @Override
    public String toString() {
        return String.format("Box[%s -> %s]", start, end);
    }
}
