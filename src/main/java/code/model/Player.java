package code.model;

import code.AbstractTile;
import code.controller.Assets;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.physics.HitBox;
import javafx.geometry.BoundingBox;

/**
 * Created by eric on 11/4/15.
 */
public class Player extends AbstractTile {

    public Player() {
        super(Type.PLAYER);

        setCollidable(true);
        setPosition(Tile.BLOCK_SIZE, Tile.BLOCK_SIZE);
        setGenerateHitBoxesFromView(false);
        addHitBox(generatePlayerHitbox());

        try {
            view.addNode(Assets.getInstance().getAssetManager().cache().getTexture("chipDown.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.setRenderLayer(new RenderLayer() {
            public String name() {
                return "Player";
            }

            public int index() {
                return 10;
            }
        });
    }

    private HitBox generatePlayerHitbox() {
        return new HitBox("PLAYER", new BoundingBox(1, 1, Tile.BLOCK_SIZE - 2, Tile.BLOCK_SIZE - 2));
    }
}
