package code.model;

import java.awt.*;

/**
 * Created by eric on 10/26/15.
 */
public class BlankTile {
    private Point point;
    private Type type;

    public BlankTile(Point point, Type type) {
        this.point = point;
        this.type = type;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BlankTile{" +
                "point=" + point +
                ", type=" + type +
                '}';
    }
}
