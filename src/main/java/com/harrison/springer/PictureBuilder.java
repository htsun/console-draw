package com.harrison.springer;

import com.harrison.springer.model.Canvas;
import com.harrison.springer.model.command.Command;
import com.harrison.springer.model.command.CommandType;

import java.util.ArrayList;
import java.util.List;

public class PictureBuilder {
    private final List<Command> commandHistory = new ArrayList<>();       // TODO : we can use this history to handle UNDO in the future ???

    public PictureBuilder apply(Command command) {
        if (command.getCommandType() == CommandType.CreateCanvas) {
            resetHistory();
        } else if (commandHistory.isEmpty()) {
            throw new IllegalArgumentException("please create canvas before applying any commands");
        }

        addToHistory(command);
        return this;
    }

    private void resetHistory() {
        commandHistory.clear();
    }

    private void addToHistory(Command command) {
        commandHistory.add(command);
    }

    public Canvas toCanvas() {
        Canvas canvas = Canvas.EMPTY;

        for (Command command : commandHistory) {
            canvas = command.applyOnto(canvas);
        }

        return canvas;
    }
}
