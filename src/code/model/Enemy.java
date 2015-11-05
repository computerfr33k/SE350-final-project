package code.model;

import code.AbstractTile;
import code.controller.Assets;

/**
 * Created by eric on 11/3/15.
 */
public class Enemy extends AbstractTile {
    public Enemy() {
        super(Type.ENEMY);

        // We have to add a hitbox since we are not attaching an object to the entity and so it cannot automatically
        // generate the hitbox for us.
        setCollidable(true);
        addHitBox(generateTileHitBox());
        try {
            view.addNode(Assets.getInstance().getAssetManager().cache().getTexture("bugUp.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
