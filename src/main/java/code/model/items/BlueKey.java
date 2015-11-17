package code.model.items;

import code.AbstractTile;
import code.controller.Assets;
import code.model.Type;

/**
 * Created by eric on 11/17/15.
 */
public class BlueKey extends AbstractTile {
    public BlueKey() {
        super(Type.BLUE_KEY);

        try {
            view.addNode(Assets.getInstance().getAssetManager().cache().getTexture("blueKey.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        addHitBox(generateTileHitBox());
        setCollidable(true);
    }
}
