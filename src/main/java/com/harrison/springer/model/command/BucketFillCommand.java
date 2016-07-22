package com.harrison.springer.model.command;

import com.harrison.springer.model.Canvas;
import com.harrison.springer.model.Pos;
import com.harrison.springer.model.action.BucketFillAction;

public class BucketFillCommand implements Command {
    private final Pos pos;
    private final char fillChar;

    public BucketFillCommand(Pos pos, char fillChar) {
        this.pos = pos;
        this.fillChar = fillChar;
    }

    public Pos getPos() {
        return pos;
    }

    public char getFillChar() {
        return fillChar;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.BucketFill;
    }

    @Override
    public boolean isValid() {
        return pos.isValid();
    }

    @Override
    public Canvas applyOnto(Canvas canvas) {
        return new BucketFillAction(pos, fillChar).applyOnto(canvas);
    }

    @Override
    public String toString() {
        return String.format("Fill[%s <- %c]", pos, fillChar);
    }
}
