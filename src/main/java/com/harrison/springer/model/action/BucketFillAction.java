package com.harrison.springer.model.action;

import com.harrison.springer.model.Canvas;
import com.harrison.springer.model.Pos;

import java.util.*;

import static java.util.stream.Collectors.toSet;

public class BucketFillAction implements Action {
    private final Pos pos;
    private final char fillChar;

    public BucketFillAction(Pos pos, char fillChar) {
        this.pos = pos;
        this.fillChar = fillChar;
    }

    @Override
    public Canvas applyOnto(Canvas canvas) {
        Set<Pos> accumulator = new HashSet<>();

        if (canvas.isVacant(pos)) {
            Set<Pos> fillableNeighboours = collectFillableNeighbours(Collections.singleton(pos), accumulator, canvas);
            fillableNeighboours.stream().forEach(pos -> canvas.setPixel(pos, fillChar));
        }

        return canvas;
    }

    private Set<Pos> collectFillableNeighbours(Set<Pos> candidatePos, Set<Pos> accumulator, Canvas canvas) {
        Set<Pos> newNeighbours = candidatePos.stream()
                                        .flatMap(p -> collectFillableNeighbours(p, canvas).stream())
                                        .filter(p -> !accumulator.contains(p))      // avoid duplicates from already traversed positions
                                        .collect(toSet());

        accumulator.addAll(candidatePos);               // marked as done for this iteration

        if (newNeighbours.isEmpty()) {
            return accumulator;
        } else {
            return collectFillableNeighbours(newNeighbours, accumulator, canvas);
        }
    }

    private Set<Pos> collectFillableNeighbours(Pos pos, Canvas canvas) {
        int x = pos.getX();
        int y = pos.getY();
        List<Pos> candidateNeighbours = Arrays.asList(new Pos(x, y - 1), new Pos(x, y + 1), new Pos(x - 1, y), new Pos(x + 1, y));
        return candidateNeighbours.stream().filter(canvas::isVacant).collect(toSet());
    }



}
