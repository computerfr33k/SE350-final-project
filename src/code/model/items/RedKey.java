package code.model.items;

import code.controller.Assets;
import code.model.Type;
import com.almasb.fxgl.entity.Entity;

/**
 * Created by eric on 11/3/15.
 */
public class RedKey extends Entity {
    public RedKey() {
        super(Type.RED_KEY);

        try {
            setSceneView(Assets.getInstance().getAssetManager().cache().getTexture("redKey.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setCollidable(true);
    }
}
