package code.model.items;

import code.AbstractTile;
import code.controller.Assets;
import code.model.Type;

/**
 * Created by eric on 11/17/15.
 */
public class GreenKey extends AbstractTile {
    public GreenKey() {
        super(Type.GREEN_KEY);

        try {
            view.addNode(Assets.getInstance().getAssetManager().cache().getTexture("greenKey.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        addHitBox(generateTileHitBox());
        setCollidable(true);
    }
}
