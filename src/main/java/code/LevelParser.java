package code;

import code.model.EmptyTile;
import code.model.Enemy;
import code.model.Level;
import code.model.Portal;
import code.model.items.Chip;
import code.model.items.RedKey;
import code.model.items.walls.BlueKeyWall;
import code.model.items.walls.GreenKeyWall;
import code.model.items.walls.RedKeyWall;
import code.model.items.walls.Wall;
import com.almasb.fxgl.asset.AssetManager;

import java.util.List;

/**
 * Created by eric on 11/2/15.
 */
public class LevelParser {
    public static Level parse(AssetManager assetManager, List<String> levelData) throws Exception {
        Level level = new Level(assetManager);

        int x = Integer.parseInt(levelData.get(0).split(",")[0]);
        int y = Integer.parseInt(levelData.get(0).split(",")[1]);

        for (int i = 1; i <= y; i++) {
            String line = levelData.get(i);
            for (int j = 0; j < x; j++) {
                char c = line.charAt(j);
                AbstractTile entity = null;

                if (c == '1') {
                    entity = new Wall();
                } else if (c == '0') {
                    entity = new EmptyTile();
                } else if (c == '2') {
                    entity = new Enemy();
                } else if (c == 'r') {
                    entity = new RedKey();
                } else if (c == '4') {
                    entity = new Chip();
                } else if (c == '5') {
                    entity = new Portal();
                } else if (c == '6') {
                    entity = new RedKeyWall();
                } else if (c == '7') {
                    entity = new BlueKeyWall();
                } else if (c == '8') {

                } else if (c == '9') {
                    entity = new GreenKeyWall();
                }

                if (entity != null) {
                    level.addTile(j, i - 1, entity);
                }
            }
        }

        return level;
    }
}
