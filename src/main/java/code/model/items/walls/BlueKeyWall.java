package code.model.items.walls;

import code.AbstractTile;
import code.controller.Assets;
import code.model.Type;

/**
 * Created by eric on 11/17/15.
 */
public class BlueKeyWall extends AbstractTile {
    public BlueKeyWall() {
        super(Type.BLUE_KEY_WALL);

        try {
            view.addNode(Assets.getInstance().getAssetManager().cache().getTexture("blueKeyWall.png"));
            addHitBox(generateTileHitBox());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setCollidable(true);
    }
}
