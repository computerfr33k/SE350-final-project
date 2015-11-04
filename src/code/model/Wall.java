package code.model;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by eric on 10/26/15.
 */
public class Wall extends Entity implements Cloneable {

    public Wall() {
        super(Type.WALL);

        Rectangle rectangle = new Rectangle(Tile.BLOCK_SIZE, Tile.BLOCK_SIZE);
        rectangle.setFill(Color.BLACK);
        setSceneView(rectangle);
    }

    public Wall clone() throws CloneNotSupportedException {
        Wall clone = (Wall) super.clone();

        return clone;
    }
}
