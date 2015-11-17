package code.model.items.walls;

import code.AbstractTile;
import code.controller.Assets;
import code.model.Type;

/**
 * Created by eric on 11/17/15.
 */
public class RedKeyWall extends AbstractTile {
    public RedKeyWall() {
        super(Type.RED_KEY_WALL);

        try {
            addHitBox(generateTileHitBox());
            view.addNode(Assets.getInstance().getAssetManager().cache().getTexture("redKeyWall.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        setCollidable(true);
    }
}
