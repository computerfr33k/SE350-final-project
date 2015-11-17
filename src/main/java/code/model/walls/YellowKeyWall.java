package code.model.walls;

import code.AbstractTile;
import code.controller.Assets;
import com.almasb.fxgl.entity.EntityType;
import javafx.beans.property.ReadOnlyBooleanWrapper;

/**
 * Created by eric on 11/17/15.
 */
public class YellowKeyWall extends AbstractTile {
    public YellowKeyWall(EntityType type) {
        super(type);

        try {
            addHitBox(generateTileHitBox());
            view.addNode(Assets.getInstance().getAssetManager().cache().getTexture("yellowKeyWall.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        isKeyedEntrance = new ReadOnlyBooleanWrapper(true);
        setCollidable(true);
    }
}
