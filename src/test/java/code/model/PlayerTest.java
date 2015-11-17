package code.model;

import code.AbstractTile;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by eric on 11/16/15.
 */
public class PlayerTest {

    /**
     * Player needs to be collidable for collision detection with enemies for example.
     */
    @Test
    public void testPlayerIsCollidable() {
        Player player = new Player();

        assertTrue(player.isCollidable());
    }

    /**
     * Player Render Layer should be greater than background so that the player is drawn on top.
     */
    @Test
    public void testPlayerRenderLayerIsGreaterThanBackgroundLayer() {
        Player player = new Player();
        AbstractTile tile = new EmptyTile();

        Assert.assertTrue(player.getView().getRenderLayer().index() > tile.getView().getRenderLayer().index());
    }

    /**
     * Check to make sure Player Class is set as a Player Type
     */
    @Test
    public void testPlayerClassIsTypePlayer() {
        Player player = new Player();

        assertEquals(player.getEntityType(), Type.PLAYER);
    }
}