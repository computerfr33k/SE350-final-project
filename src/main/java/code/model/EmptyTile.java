package code.model;

import code.AbstractTile;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by eric on 11/3/15.
 */
public final class EmptyTile extends AbstractTile {

    public EmptyTile() {
        super(Type.EMPTY);

        Rectangle rectangle = new Rectangle(Tile.BLOCK_SIZE, Tile.BLOCK_SIZE);
        rectangle.setFill(Color.GREY);
        rectangle.setStroke(Color.BLACK);
        setCollidable(false);

        view.addNode(rectangle);
    }
}
