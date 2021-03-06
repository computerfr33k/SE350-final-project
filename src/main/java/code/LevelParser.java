package code;

import code.model.EmptyTile;
import code.model.Enemy;
import code.model.Level;
import code.model.Portal;
import code.model.items.BlueKey;
import code.model.items.Chip;
import code.model.items.GreenKey;
import code.model.items.RedKey;
import code.model.walls.BlueKeyWall;
import code.model.walls.GreenKeyWall;
import code.model.walls.RedKeyWall;
import code.model.walls.Wall;
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
                    level.setTotalChips(level.getTotalChips().add(1).get());

                } else if (c == '5') {
                    entity = new Portal();
                } else if (c == '6') {
                    entity = new RedKeyWall();
                } else if (c == '7') {
                    entity = new BlueKeyWall();
                } else if (c == '8') {

                } else if (c == '9') {
                    entity = new GreenKeyWall();
                } else if (c == 'g') {
                    entity = new GreenKey();
                } else if (c == 'b') {
                    entity = new BlueKey();
                }

                if (entity != null) {
                    level.addTile(j, i - 1, entity);
                }
            }
        }

        return level;
    }
}
