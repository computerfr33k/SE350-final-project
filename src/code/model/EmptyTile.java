package code.model;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by eric on 11/3/15.
 */
public class EmptyTile extends Entity {
    public EmptyTile() {
        super(Type.EMPTY);

        Rectangle rectangle = new Rectangle(Tile.BLOCK_SIZE, Tile.BLOCK_SIZE);
        rectangle.setFill(Color.GREY);
        rectangle.setStroke(Color.BLACK);
        setCollidable(false);

        setSceneView(rectangle);
    }
}
