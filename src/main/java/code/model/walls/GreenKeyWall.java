package code.model.walls;

import code.AbstractTile;
import code.controller.Assets;
import code.model.Type;

/**
 * Created by eric on 11/17/15.
 */
public class GreenKeyWall extends AbstractTile {
    public GreenKeyWall() {
        super(Type.GREEN_KEY_WALL);

        try {
            addHitBox(generateTileHitBox());
            view.addNode(Assets.getInstance().getAssetManager().cache().getTexture("greenKeyWall.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setCollidable(true);
    }
}
