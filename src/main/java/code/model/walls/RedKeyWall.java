package code.model.walls;

import code.AbstractTile;
import code.controller.Assets;
import code.model.Type;
import javafx.beans.property.ReadOnlyBooleanWrapper;

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

        isKeyedEntrance = new ReadOnlyBooleanWrapper(true);
        setCollidable(true);
    }
}
