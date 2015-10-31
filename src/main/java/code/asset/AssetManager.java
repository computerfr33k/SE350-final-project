package code.asset;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by eric on 10/29/15.
 */
public class AssetManager {
    private static final Logger log = Logger.getLogger("Assets");
    private static AssetManager ourInstance = new AssetManager();
    private HashMap<String, String> assets;

    private AssetManager() {
        assets = new HashMap<>();
    }

    public static AssetManager getInstance() {
        return ourInstance;
    }

    public Image getImage(Class klass) throws FileNotFoundException {
        return new Image(new FileInputStream(assets.get(klass.getSimpleName())));
    }

    public void loadAssets(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles(new ImageFileFilter());
        for (File file : files) {
            if (file.isFile()) {
                addAsset(file.getName().substring(0, file.getName().lastIndexOf(".")), file.getPath());
            }
        }
    }

    public void logCached() {
        log.info("Logging Cached Assets");
        assets.forEach((name, path) -> log.info("Texture: " + name));
    }

    private void addAsset(String className, String filename) {
        assets.put(className, filename);
    }

    @Override
    public String toString() {
        return "AssetManager{" +
                "assets=" + assets +
                '}';
    }

    // Use For filtering out non image files.
    private static class ImageFileFilter implements FileFilter {
        private final String[] exts = new String[]{"png"};

        @Override
        public boolean accept(File file) {
            for (String ext : exts) {
                if (file.getName().toLowerCase().endsWith(ext)) {
                    return true;
                }
            }

            return false;
        }
    }
}
