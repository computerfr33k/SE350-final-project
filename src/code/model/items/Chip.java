package code.model.items;

import code.controller.Assets;
import code.model.Type;
import com.almasb.fxgl.entity.Entity;

/**
 * Created by eric on 11/3/15.
 */
public class Chip extends Entity implements Cloneable {
    public Chip() {
        super(Type.CHIP);

        try {
            setSceneView(Assets.getInstance().getAssetManager().cache().getTexture("chipItem.png"));
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
