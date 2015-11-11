package code.model;

import code.AbstractTile;
import code.controller.Assets;

/**
 * Created by eric on 11/11/15.
 */
public class Portal extends AbstractTile {
    public Portal() {
        super(Type.PORTAL);

        setCollidable(true);
        addHitBox(generateTileHitBox());
        try {
            view.addNode(Assets.getInstance().getAssetManager().loadTexture("portal.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
