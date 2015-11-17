package code.model;

import com.almasb.fxgl.entity.EntityType;

/**
 * Created by eric on 11/2/15.
 */
public enum Type implements EntityType {
    WALL, ENEMY, PLAYER, EMPTY, EXPLOSION,
    CHIP, RED_KEY, GREEN_KEY, BLUE_KEY, YELLOW_KEY,
    PORTAL,
    RED_KEY_WALL, BLUE_KEY_WALL, YELLOW_KEY_WALL, GREEN_KEY_WALL
}
