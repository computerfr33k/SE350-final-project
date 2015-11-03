package code.model;

import com.almasb.fxgl.asset.AssetManager;
import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eric on 11/2/15.
 */
public class Level {
    private Map<Point2D, Entity> grid;
    private AssetManager assets;

    public Level(AssetManager assets) {
        this.assets = assets;
        grid = new HashMap<Point2D, Entity>();
    }

    public void addTile(int x, int y, Entity tile) throws Exception {
        // set tile location
        tile.setX(Tile.getBlockSize() * x);
        tile.setY(Tile.getBlockSize() * y);

        if (tile.isType(Type.EMPTY)) {

            Rectangle rectangle = new Rectangle(Tile.getBlockSize(), Tile.getBlockSize());
            rectangle.setFill(Color.GREY);
            rectangle.setStroke(Color.BLACK);
            tile.setCollidable(false);

            tile.setSceneView(rectangle);
        } else if (tile.isType(Type.ENEMY)) {
            Rectangle rect = new Rectangle(Tile.getBlockSize(), Tile.getBlockSize());
            rect.setFill(Color.RED);

            tile.setSceneView(rect);
            tile.setCollidable(true);
        }

        grid.put(new Point2D(x, y), tile);
    }

    public Map<Point2D, Entity> getGrid() {
        return grid;
    }

    public Entity getTile(Point2D point2D) {
        return grid.get(point2D);
    }

    public Entity getTile(int x, int y) {
        return grid.get(new Point2D(x, y));
    }

    @Override
    public String toString() {
        return "Level{" +
                "grid=" + grid +
                '}';
    }
}
