package code.model.items;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by eric on 11/16/15.
 */
public class RedKeyTest {
    @Test
    public void testIsCollidable() {
        RedKey key = new RedKey();

        assertTrue(key.isCollidable());
    }
}