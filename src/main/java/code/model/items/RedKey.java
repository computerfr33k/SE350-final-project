package code.model.items;

import code.AbstractTile;
import code.controller.Assets;
import code.model.Type;
import com.almasb.fxgl.entity.RenderLayer;

/**
 * Created by eric on 11/3/15.
 */
public class RedKey extends AbstractTile {
    public RedKey() {
        super(Type.RED_KEY);

        setCollidable(true);
        // We have to add a hitbox since we are not attaching an object to the entity and so it cannot automatically
        // generate the hitbox for us.
        addHitBox(generateTileHitBox());

        try {
            view.addNode(Assets.getInstance().getAssetManager().cache().getTexture("redKey.png"));
            // Override the render layer to be on the same as the player for collision detection
            view.setRenderLayer(new RenderLayer() {
                public String name() {
                    return "Chip (item)";
                }

                public int index() {
                    return 10;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
