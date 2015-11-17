package code.model.walls;

import code.AbstractTile;
import code.model.Tile;
import code.model.Type;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by eric on 10/26/15.
 */
public class Wall extends AbstractTile implements Cloneable {

    public Wall() {
        super(Type.WALL);

        Rectangle rectangle = new Rectangle(Tile.BLOCK_SIZE, Tile.BLOCK_SIZE);
        rectangle.setFill(Color.BLACK);

        view.addNode(rectangle);
    }

    public Wall clone() throws CloneNotSupportedException {
        Wall clone = (Wall) super.clone();

        return clone;
    }
}
