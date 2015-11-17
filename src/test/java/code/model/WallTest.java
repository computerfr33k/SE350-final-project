package code.model;

import code.model.items.walls.Wall;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

/**
 * Created by eric on 11/16/15.
 */
public class WallTest {

    @Test
    public void testIsNotCollidable() {
        Wall wall = new Wall();

        assertFalse(wall.isCollidable());
    }

}