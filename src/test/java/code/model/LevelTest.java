package code.model;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by eric on 11/23/15.
 */
public class LevelTest {

    @Test
    public void testGettingTileByGridCoordinate() throws Exception {
        Level level = new Level(null);
        level.addTile(0, 0, new EmptyTile());

        Entity tile = level.getTile(0, 0);
        Assert.assertNotNull(tile);
        Assert.assertTrue(tile.getEntityType() == Type.EMPTY);
        Assert.assertTrue(tile.getPosition().getX() == 0);
        Assert.assertTrue(tile.getPosition().getY() == 0);
    }

    @Test
    public void testGettingTileByGridPoint2D() throws Exception {
        Level level = new Level(null);
        level.addTile(0, 0, new EmptyTile());

        Entity tile = level.getTile(new Point2D(0, 0));

        Assert.assertNotNull(tile);
        Assert.assertEquals(tile.getEntityType(), Type.EMPTY);
        Assert.assertEquals(tile.getPosition(), new Point2D(0, 0));
        Assert.assertEquals(tile.getPosition().getX(), 0.0);
        Assert.assertEquals(tile.getPosition().getY(), 0.0);
    }
}