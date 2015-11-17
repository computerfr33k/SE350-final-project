package code.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by eric on 11/16/15.
 */
public class EnemyTest {

    /**
     * Needs to be collidable since this is an enemy and player will die when colliding with enemies.
     */
    @Test
    public void testIsCollidable() {
        Enemy enemy = new Enemy();

        assertTrue(enemy.isCollidable());
    }

}