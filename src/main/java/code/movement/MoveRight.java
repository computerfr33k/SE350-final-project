package code.movement;

import code.model.Tile;
import javafx.geometry.Point2D;

/**
 * Created by eric on 11/22/15.
 */
public class MoveRight implements IMovement {

    public Point2D move(Point2D currentPosition) {
        currentPosition.add(Tile.BLOCK_SIZE, 0);

        return currentPosition;
    }
}
