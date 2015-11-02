package code;

import code.model.Level;
import code.model.Type;
import code.model.Wall;
import com.almasb.fxgl.asset.AssetManager;
import com.almasb.fxgl.entity.Entity;

import java.util.List;

/**
 * Created by eric on 11/2/15.
 */
public class LevelParser {
    public static int BLOCK_SIZE = 32;

    public static Level parse(AssetManager assetManager, List<String> levelData) {
        Level level = new Level(assetManager);

        int x = Integer.parseInt(levelData.get(0).split(",")[0]);
        int y = Integer.parseInt(levelData.get(0).split(",")[1]);

        for (int i = 1; i <= y; i++) {
            String line = levelData.get(i);
            for (int j = 0; j < x; j++) {
                char c = line.charAt(j);
                if (c == '1') {
                    try {
                        level.addTile(j, i - 1, new Wall());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (c == '0') {
                    try {
                        level.addTile(j, i - 1, new Entity(Type.EMPTY));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (c == '2') {
                    try {
                        level.addTile(j, i - 1, new Entity(Type.ENEMY));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return level;
    }
}
