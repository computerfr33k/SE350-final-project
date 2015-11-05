package code.model.items;

import code.AbstractTile;
import code.controller.Assets;
import code.model.Type;

/**
 * Created by eric on 11/3/15.
 */
public class Chip extends AbstractTile implements Cloneable {
    public Chip() {
        super(Type.CHIP);

        // We have to add a hitbox since we are not attaching an object to the entity and so it cannot automatically
        // generate the hitbox for us.
        addHitBox(generateTileHitBox());
        try {
            view.addNode(Assets.getInstance().getAssetManager().cache().getTexture("chipItem.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCollidable(true);
    }

    public Chip clone() throws CloneNotSupportedException {
        Chip clone = (Chip) super.clone();

        return clone;
    }
}
