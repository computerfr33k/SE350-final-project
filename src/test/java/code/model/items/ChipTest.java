package code.model.items;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by eric on 11/16/15.
 */
public class ChipTest {
    @Test
    public void testIsCollidable() {
        Chip chip = new Chip();

        assertTrue(chip.isCollidable());
    }
}