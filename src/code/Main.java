package code;

import code.controller.Assets;
import code.model.EmptyTile;
import code.model.Level;
import code.model.Type;
import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.effect.ExplosionEmitter;
import com.almasb.fxgl.effect.ParticleEntity;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.event.InputManager;
import com.almasb.fxgl.event.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsManager;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.util.ApplicationMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Main extends GameApplication {
    private static int BLOCK_SIZE = 32;
    private Entity player;
    private Entity test;
    private List<Entity> enemies;
    private Text time;
    private Text timeLabel;
    private Text inventory;
    private ListView hbox;
    private boolean startTimer = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setMenuEnabled(true);
        gameSettings.setShowFPS(true);
        gameSettings.setIntroEnabled(false);
        gameSettings.setVersion("0.1");
        gameSettings.setTitle("SE 350 Final Project");
        gameSettings.setWidth(1280);
        gameSettings.setHeight(720);
        gameSettings.setApplicationMode(ApplicationMode.DEVELOPER);
    }

    @Override
    protected void initInput() {
        InputManager input = getInputManager();
        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onActionBegin() {
                Point2D nextCoord = new Point2D(player.getX(), player.getY() - BLOCK_SIZE);

                if (getGameWorld().getEntityAt(nextCoord).get().getEntityType() != Type.WALL) {
                    player.setPosition(nextCoord);

                    /**
                     * Move the "Camera" as Chip moves.
                     */
                    /*getGameScene().getRoot().setTranslateY(getGameScene().getRoot().getTranslateY() + BLOCK_SIZE);*/
                    /*time.setTranslateY(time.getTranslateY() - BLOCK_SIZE);
                    timeLabel.setTranslateY(timeLabel.getTranslateY() - BLOCK_SIZE);*/
                    startTimer = true;
                }
            }
        }, KeyCode.UP);

        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onActionBegin() {
                Point2D nextCoord = new Point2D(player.getX(), player.getY() + BLOCK_SIZE);
                if (!getGameWorld().getEntityAt(nextCoord).get().isType(Type.WALL)) {
                    player.setPosition(nextCoord);

                    /**
                     * Move the "Camera" as Chip moves.
                     */
                    /*getGameScene().getRoot().setTranslateY(getGameScene().getRoot().getTranslateY() - BLOCK_SIZE);*/
                    /*time.setTranslateY(time.getTranslateY() + BLOCK_SIZE);
                    timeLabel.setTranslateY(timeLabel.getTranslateY() + BLOCK_SIZE);*/
                    startTimer = true;
                }
            }
        }, KeyCode.DOWN);

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onActionBegin() {
                player.translate(BLOCK_SIZE, 0);
                startTimer = true;
            }
        }, KeyCode.RIGHT);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onActionBegin() {
                player.translate(-BLOCK_SIZE, 0);
                startTimer = true;
            }
        }, KeyCode.LEFT);
    }

    @Override
    protected void initAssets() throws Exception {
        getAssetManager().cache().logCached();

        Assets.getInstance().setAssetManager(getAssetManager());
    }

    @Override
    protected void initGame() {

        // Parse Level to show on the screen
        Level grid = null;
        try {
            grid = LevelParser.parse(getAssetManager(), getAssetManager().cache().getText("default_level.txt"));
            for (Entity entity : grid.getGrid().values()) {
                getGameWorld().addEntity(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        enemies = new ArrayList<Entity>();

        initPlayer();
    }

    private void initPlayer() {
        player = new Entity(Type.PLAYER);
        player.setCollidable(true);
        player.setPosition(32, 32);
        player.setGenerateHitBoxesFromView(false);
        player.addHitBox(generatePlayerHitbox());

        Rectangle triangle = new Rectangle(32, 32);
        triangle.setStroke(Color.BLUE);
        triangle.setStrokeWidth(3);

        player.setSceneView(triangle);

        getGameWorld().addEntity(player);
    }

    @Override
    protected void initPhysics() {
        PhysicsManager physicsManager = getPhysicsManager();
        physicsManager.addCollisionHandler(new CollisionHandler(Type.PLAYER, Type.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                spawnExposion(a.getCenter());
                a.removeFromWorld();
                b.removeFromWorld();
            }
        });

        physicsManager.addCollisionHandler(new CollisionHandler(Type.PLAYER, Type.RED_KEY) {
            @Override
            protected void onCollision(Entity a, Entity b) {
                hbox.getItems().add("Red Key");
                EmptyTile tile = new EmptyTile();
                tile.setPosition(b.getPosition());
                // Remove the item from the board since it was picked up
                b.removeFromWorld();
                // Now Add a empty tile in place of the item
                getGameWorld().addEntity(tile);
            }
        });

        physicsManager.addCollisionHandler(new CollisionHandler(Type.PLAYER, Type.CHIP) {
            @Override
            protected void onCollision(Entity a, Entity b) {
                hbox.getItems().add("Chip");

                // Replace tile with empty one
                EmptyTile tile = new EmptyTile();
                tile.setPosition(b.getPosition());

                b.removeFromWorld();
                getGameWorld().addEntity(tile);
            }
        });
    }

    private void spawnExposion(Point2D point2D) {
        ParticleEntity explosion = new ParticleEntity(Type.EXPLOSION);
        explosion.setPosition(point2D);
        explosion.setExpireTime(Duration.seconds(0.5));

        ExplosionEmitter emitter = new ExplosionEmitter();
        explosion.setEmitter(emitter);

        getGameWorld().addEntity(explosion);
    }

    @Override
    protected void initUI() {

        timeLabel = new Text("Time: ");
        /*timeLabel.setFill(Color.WHITE);*/
        timeLabel.setTranslateX(BLOCK_SIZE * 30);
        timeLabel.setFont(Font.font(18));
        timeLabel.setTranslateY(40);

        time = new Text("0");
        /*time.setFill(Color.WHITE);*/
        time.setFont(Font.font(18));
        time.setTranslateX(timeLabel.getTranslateX() + 55);
        time.setTranslateY(40);

        inventory = new Text("Inventory: ");
        inventory.setTranslateX(BLOCK_SIZE * 30);
        inventory.setTranslateY(BLOCK_SIZE * 9);
        inventory.setFont(Font.font(18));

        ObservableList<String> list = FXCollections.observableArrayList("test");
        hbox = new ListView();
        hbox.setTranslateX(BLOCK_SIZE * 31);
        hbox.setTranslateY(BLOCK_SIZE * 11);
        hbox.setItems(list);

        getGameScene().addUINodes(timeLabel, time, inventory, hbox);
    }

    @Override
    protected void onUpdate() {
        if (startTimer) {
            // There are 60 ticks per second
            int current = (int) getTick() / 60;
            time.setText(String.valueOf(current));
        } else {
            // Don't count ticks until we move the player
            getTimerManager().resetTicks();
        }
    }


    private HitBox generatePlayerHitbox() {
        return new HitBox("PLAYER", new BoundingBox(1, 1, BLOCK_SIZE - 2, BLOCK_SIZE - 2));
    }
}