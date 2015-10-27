package code.model;

import java.awt.*;

/**
 * Created by eric on 10/26/15.
 */
public class Cell {
    private Point point;
    private Type type;

    public Point getPoint() {
        return point;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "point=" + point +
                ", type=" + type +
                '}';
    }
}
