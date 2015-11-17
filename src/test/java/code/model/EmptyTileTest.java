package code.model;

import code.AbstractTile;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Created by eric on 11/16/15.
 */
public class EmptyTileTest {

    /**
     * Test to make sure that the entity type is set correctly
     */
    @Test
    public void testEmptyTileEntityType() {
        AbstractTile tile = new EmptyTile();
        assertEquals(tile.getEntityType(), Type.EMPTY);
    }

    /**
     * We want the empty tile to not be collidable since the user will be traversing over the empty tiles
     * and cannot "collide" with them.
     */
    @Test
    public void testSceneViewIsNotPresent() {
        AbstractTile tile = new EmptyTile();

        assertFalse(tile.isCollidable());
    }

    @Test
    public void testIsInBackgroundRenderLayer() {
        AbstractTile tile = new EmptyTile();

        assertEquals(tile.getView().getRenderLayer().name(), "Background");
    }

    /**
     * Render Layer should be zero, meaning it is rendered on the bottom so other things can be render over it,
     * such as the player
     */
    @Test
    public void testBackgroundRenderLayerIsZero() {
        AbstractTile tile = new EmptyTile();

        assertEquals(tile.getView().getRenderLayer().index(), 0);
    }

}