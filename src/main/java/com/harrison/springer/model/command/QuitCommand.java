package com.harrison.springer.model.command;

import com.harrison.springer.model.Canvas;

public class QuitCommand implements Command {

    @Override
    public CommandType getCommandType() {
        return CommandType.Quit;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Canvas applyOnto(Canvas canvas) {
        return canvas;
    }

    @Override
    public String toString() {
        return "QUIT";
    }
}
