package com.harrison.springer.model.command;

import java.util.Arrays;
import java.util.Optional;

public enum CommandType {
    CreateCanvas("C"),
    DrawLine("L"),
    DrawBox("R"),
    BucketFill("B"),
    Quit("Q");

    private final String commandText;

    CommandType(String commandText) {
        this.commandText = commandText;
    }

    public static Optional<CommandType> toCommandType(String text) {
        return Arrays.stream(values()).filter(c -> c.commandText.equalsIgnoreCase(text)).findFirst();
    }
}
