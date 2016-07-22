package com.harrison.springer;

import com.harrison.springer.model.Pos;
import com.harrison.springer.model.command.*;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

public class CommandParser {

    public Command readCommand(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        Optional<String> command = readCommand(scanner);

        return command.map(c -> CommandType.toCommandType(c)
                                .map(ct -> parseCommand(ct, scanner))
                                .orElseThrow(() -> new IllegalArgumentException("unrecognised command type")))
                .orElseThrow(() -> new IllegalArgumentException("command type is missing"));
    }

    private Command parseCommand(CommandType commandType, Scanner scanner) {
        switch (commandType) {
            case Quit :
                return new QuitCommand();

            case CreateCanvas: {
                Optional<Integer> width = readNumber(scanner);
                Optional<Integer> height = readNumber(scanner);

                if (width.isPresent() && height.isPresent()) {
                    Command command = new CreateCanvasCommand(width.get(), height.get());

                    if (command.isValid()) {
                        return command;
                    }
                }

                throw new IllegalArgumentException("invalid Create Canvas arguments");
            }

            case DrawLine: {
                Optional<Pos> startPos = readPos(scanner);
                Optional<Pos> endPos = readPos(scanner);

                if (startPos.isPresent() && endPos.isPresent()) {
                    Command command = new DrawLineCommand(startPos.get(), endPos.get());

                    if (command.isValid()) {
                        return command;
                    }
                }

                throw new IllegalArgumentException("invalid Draw Line arguments");
            }

            case DrawBox: {
                Optional<Pos> startPos = readPos(scanner);
                Optional<Pos> endPos = readPos(scanner);

                if (startPos.isPresent() && endPos.isPresent()) {
                    Command command = new DrawBoxCommand(startPos.get(), endPos.get());

                    if (command.isValid()) {
                        return command;
                    }
                }

                throw new IllegalArgumentException("invalid Draw Rectangle arguments");
            }

            case BucketFill: {
                Optional<Pos> pos = readPos(scanner);
                Optional<String> fillChar = readString(scanner);

                if (pos.isPresent() && fillChar.isPresent() && fillChar.get().length() == 1) {
                    Command command = new BucketFillCommand(pos.get(), fillChar.get().charAt(0));

                    if (command.isValid()) {
                        return command;
                    }
                }

                throw new IllegalArgumentException("invalid Bucket Fill arguments");
            }

            default :
                throw new IllegalArgumentException("unrecognised command type");
        }
    }

    private Optional<Pos> readPos(Scanner scanner) {
        Optional<Integer> x = readNumber(scanner);
        Optional<Integer> y = readNumber(scanner);

        return (x.isPresent() && y.isPresent()) ? Optional.of(new Pos(x.get(), y.get())) : Optional.empty();
    }

    private Optional<String> readCommand(Scanner scanner) {
        return scanner.hasNext() ? Optional.of(scanner.next()) : Optional.empty();
    }

    private Optional<String> readString(Scanner scanner) {
        return scanner.hasNext() ? Optional.of(scanner.next()) : Optional.empty();
    }

    private Optional<Integer> readNumber(Scanner scanner) {
        return scanner.hasNextInt() ? Optional.of(scanner.nextInt()) : Optional.empty();
    }

}
