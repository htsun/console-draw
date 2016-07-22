package com.harrison.springer;

import com.harrison.springer.model.Canvas;
import com.harrison.springer.model.command.Command;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser();
        PictureBuilder pictureBuilder = new PictureBuilder();
        InputStream input = System.in;

        while (true) {
            System.out.print("enter command: ");
            try {
                Command command = commandParser.readCommand(input);
                if (command.isQuit()) {
                    break;
                }

                pictureBuilder.apply(command);
                dumpCanvas(pictureBuilder.toCanvas());
            } catch (IllegalArgumentException e) {
                displayError(e);
            }
        }

        System.out.println("Bye ..." );
    }

    private static void displayError(Exception e) {
        System.out.printf(">>> ERROR >>> %s\n", e.getMessage());
    }

    private static void dumpCanvas(Canvas canvas) {
        canvas.toDisplay(true).stream().forEach(System.out::println);
    }
}
