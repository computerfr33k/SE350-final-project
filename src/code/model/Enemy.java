package code.model;

import code.controller.Assets;
import com.almasb.fxgl.entity.Entity;

/**
 * Created by eric on 11/3/15.
 */
public class Enemy extends Entity {
    public Enemy() {
        super(Type.ENEMY);

        try {
            setSceneView(Assets.getInstance().getAssetManager().cache().getTexture("bugUp.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCollidable(true);
    }
}
