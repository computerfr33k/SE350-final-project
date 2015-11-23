package code.model;

import code.AbstractTile;
import com.almasb.fxgl.asset.AssetManager;
import com.almasb.fxgl.entity.Entity;
import javafx.beans.property.*;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eric on 11/2/15.
 */
public class Level {
    private Map<Point2D, AbstractTile> grid;
    private AssetManager assets;
    private SimpleIntegerProperty totalChips;

    public Level(AssetManager assets) {
        this.assets = assets;
        grid = new HashMap<Point2D, AbstractTile>();
        totalChips = new SimpleIntegerProperty(0);
    }

    /**
     * Determines if the entity can pass through the given tile
     *
     * @param entity
     * @param inventory
     * @return Wall
     */
    public static final ReadOnlyBooleanProperty isPassableEntity(AbstractTile entity, ListView inventory) {
        BooleanProperty wall = new ReadOnlyBooleanWrapper(true);

        // Check for entities that are not passable
        if (entity.getEntityType() == Type.WALL) {
            wall.setValue(false);
        } else if (entity.isKeyedEntrance().getValue()) {
            if (!inventory.getItems().contains(entity.getClass().getSimpleName().replace("Wall", ""))) {
                wall.setValue(false);
            }
        }

        return wall;
    }

    public void addTile(int x, int y, AbstractTile tile) throws Exception {
        // set tile location
        tile.setX(Tile.BLOCK_SIZE * x);
        tile.setY(Tile.BLOCK_SIZE * y);

        grid.put(new Point2D(x, y), tile);
    }

    public Map<Point2D, AbstractTile> getGrid() {
        return grid;
    }

    public Entity getTile(Point2D point2D) {
        return grid.get(point2D);
    }

    public Entity getTile(int x, int y) {
        return grid.get(new Point2D(x, y));
    }

    public ReadOnlyIntegerProperty getTotalChips() {
        return new ReadOnlyIntegerWrapper(totalChips.get());
    }

    public void setTotalChips(int totalChips) {
        this.totalChips.set(totalChips);
    }

    public SimpleIntegerProperty getTotalChipsProperty() {
        return this.totalChips;
    }

    @Override
    public String toString() {
        return "Level{" +
                "grid=" + grid +
                ", assets=" + assets +
                ", totalChips=" + totalChips +
                '}';
    }
}
