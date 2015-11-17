package code;

import code.model.BackgroundRenderLayer;
import code.model.Tile;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityType;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.physics.HitBox;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.geometry.BoundingBox;

/**
 * Created by eric on 11/4/15.
 */
public abstract class AbstractTile extends Entity {
    protected EntityView view;
    protected BooleanProperty isKeyedEntrance;

    public AbstractTile(EntityType type) {
        super(type);

        // So That we can have this rendered on the lowest layer (Allows the player to always be on top)
        view = new EntityView(this);
        view.setRenderLayer(BackgroundRenderLayer.getLayer());
        view.translateXProperty().bind(this.xProperty());
        view.translateYProperty().bind(this.yProperty());

        isKeyedEntrance = new ReadOnlyBooleanWrapper(false);
    }

    protected HitBox generateTileHitBox() {
        return new HitBox("__TILE__",
                new BoundingBox(1, 1, Tile.BLOCK_SIZE - 2, Tile.BLOCK_SIZE - 2));
    }

    public final EntityView getView() {
        return view;
    }

    public final ReadOnlyBooleanProperty isKeyedEntrance() {
        return isKeyedEntrance;
    }
}
