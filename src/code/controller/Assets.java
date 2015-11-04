package code.controller;

import com.almasb.fxgl.asset.AssetManager;

/**
 * Created by eric on 11/3/15.
 */
public class Assets {
    private static final Assets ourInstance = new Assets();
    private AssetManager assetManager;

    private Assets() {

    }

    public static Assets getInstance() {
        return ourInstance;
    }

    public AssetManager getAssetManager() {
        return this.assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
}
