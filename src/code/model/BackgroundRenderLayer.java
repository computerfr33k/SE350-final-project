package code.model;

import com.almasb.fxgl.entity.RenderLayer;

/**
 * Created by eric on 11/4/15.
 */
public class BackgroundRenderLayer {
    public static RenderLayer getLayer() {
        RenderLayer layer = new RenderLayer() {
            public String name() {
                return "Background";
            }

            public int index() {
                return 0;
            }
        };

        return layer;
    }
}
