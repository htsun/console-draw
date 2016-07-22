package com.harrison.springer.util;

import com.harrison.springer.model.Canvas;
import com.harrison.springer.model.Pos;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class CanvasUtil {

    public static Pos newPos(int x, int y) {
        return new Pos(x, y);
    }

    public static List<String> newCanvasContent(String... lines) {
        List<String> canvasContent = new ArrayList<>();

        for (String line : lines) {
            canvasContent.add(line);
        }

        return canvasContent;
    }

    public static void dumpCanvas(Canvas canvas) {
        canvas.toDisplay(true).stream().forEach(System.out::println);
    }

    public static void assertCanvasContent(Canvas canvas, List<String> expectedCanvasContent) {
        List<String> actualCanvasContent = canvas.toDisplay(false);

        assertTrue(ListUtils.isEqualList(actualCanvasContent, expectedCanvasContent));
    }
}
