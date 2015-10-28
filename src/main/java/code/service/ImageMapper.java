package code.service;

import code.model.Type;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by eric on 10/27/15.
 */
public class ImageMapper {
    private static final String basePath = "./src/main/resources/images/";

    public static Image getImage(Type type) throws FileNotFoundException {
        switch (type.getId()) {
            case Type.emptyTile:
                Image tile = new Image(new FileInputStream(basePath + "blankTile.png"));
                return tile;

            case Type.emptyTileBottomEdge:
                return new Image(new FileInputStream(basePath + "blankTileBottom.png"));

            default:
                return null;
        }
    }
}
