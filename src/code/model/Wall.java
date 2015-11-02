package code.model;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by eric on 10/26/15.
 */
public class Wall extends Entity {

    public Wall() {
        super(Type.WALL);

        Rectangle rectangle = new Rectangle(Tile.getBlockSize(), Tile.getBlockSize());
        rectangle.setFill(Color.BLACK);
        setSceneView(rectangle);
    }
}
