package com.harrison.springer.model.command;

import com.harrison.springer.model.Canvas;

public interface Command {

    CommandType getCommandType();

    boolean isValid();

    Canvas applyOnto(Canvas canvas);

    default boolean isQuit() {
        return getCommandType() == CommandType.Quit;
    }
}
