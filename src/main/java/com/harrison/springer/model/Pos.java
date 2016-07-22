package com.harrison.springer.model;

public final class Pos {
    private final int x;
    private final int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isValid() {
        return x > 0 && y > 0;
    }

    @Override
    public int hashCode() {
        return x >> 37 + y;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null || !(other instanceof Pos)) {
            return false;
        }

        Pos target = (Pos)other;

        return target.getX() == this.getX() && target.getY() == this.getY();
    }

    @Override
    public String toString() {
        return String.format("POS[%d x %d]", x, y);
    }
}
