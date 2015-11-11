package code;

import code.controller.Assets;
import code.model.*;
import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.effect.ExplosionEmitter;
import com.almasb.fxgl.effect.ParticleEntity;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.event.InputManager;
import com.almasb.fxgl.event.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsManager;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.util.ApplicationMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Main extends GameApplication {
    private static int BLOCK_SIZE = 32;
    Enemy enemy;
    private Player player;
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
        gameSettings.setHeight(800);
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

                    startTimer = true;
                }
            }
        }, KeyCode.DOWN);

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onActionBegin() {
                Point2D nextCoord = new Point2D(player.getX() + Tile.BLOCK_SIZE, player.getY());
                if (!getGameWorld().getEntityAt(nextCoord).get().isType(Type.WALL)) {
                    player.translate(Tile.BLOCK_SIZE, 0);
                    startTimer = true;
                }
            }
        }, KeyCode.RIGHT);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onActionBegin() {
                Point2D nextCoord = new Point2D(player.getX() - Tile.BLOCK_SIZE, player.getY());
                if (!getGameWorld().getEntityAt(nextCoord).get().isType(Type.WALL)) {
                    player.translate(-Tile.BLOCK_SIZE, 0);
                    startTimer = true;
                }
            }
        }, KeyCode.LEFT);

        input.addAction(new UserAction("Take ScreenShot") {
            @Override
            protected void onAction() {
                getSceneManager().saveScreenshot();
            }
        }, KeyCode.SPACE);
    }

    @Override
    protected void initAssets() throws Exception {
        getAssetManager().cache().logCached();

        Assets.getInstance().setAssetManager(getAssetManager());
    }

    @Override
    protected void initGame() {

        enemy = new Enemy();

        // Parse Level to show on the screen
        Level grid = null;
        try {
            grid = LevelParser.parse(getAssetManager(), getAssetManager().cache().getText("default_level.txt"));
            for (AbstractTile entity : grid.getGrid().values()) {
                addTile(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        initPlayer();
    }

    public void addTile(AbstractTile entity) {

        getGameScene().addGameView(entity.getView());
        getGameWorld().addEntity(entity);
    }

    private void initPlayer() {
        player = new Player();
        player.setPosition(new Point2D(Tile.BLOCK_SIZE * 2, Tile.BLOCK_SIZE * 3));

        getGameScene().addGameView(player.getView());
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
            }
        });

        physicsManager.addCollisionHandler(new CollisionHandler(Type.PLAYER, Type.RED_KEY) {
            @Override
            protected void onCollision(Entity a, Entity b) {
                pickUpItem("Red Key", b);
            }
        });

        physicsManager.addCollisionHandler(new CollisionHandler(Type.PLAYER, Type.CHIP) {
            @Override
            protected void onCollision(Entity a, Entity b) {
                pickUpItem("Chip", b);
            }
        });

        physicsManager.addCollisionHandler(new CollisionHandler(Type.PLAYER, Type.PORTAL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                getSceneManager().showMessageBox("Congratulations, You Won!");
            }
        });
    }

    private void pickUpItem(String nameOfItem, Entity b) {
        hbox.getItems().add(nameOfItem);

        // Replace tile with empty one
        EmptyTile tile = new EmptyTile();
        tile.setPosition(b.getPosition());

        b.removeFromWorld();
        getGameScene().addGameView(tile.getView());
        getGameWorld().addEntity(tile);
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
        timeLabel.setTranslateX(Tile.BLOCK_SIZE * 30);
        timeLabel.setFont(Font.font(18));
        timeLabel.setTranslateY(40);

        time = new Text("0");
        /*time.setFill(Color.WHITE);*/
        time.setFont(Font.font(18));
        time.setTranslateX(timeLabel.getTranslateX() + 55);
        time.setTranslateY(40);

        inventory = new Text("Inventory: ");
        inventory.setTranslateX(Tile.BLOCK_SIZE * 30);
        inventory.setTranslateY(Tile.BLOCK_SIZE * 9);
        inventory.setFont(Font.font(18));

        ObservableList<String> list = FXCollections.observableArrayList();
        hbox = new ListView();
        hbox.setTranslateX(Tile.BLOCK_SIZE * 30);
        hbox.setTranslateY(Tile.BLOCK_SIZE * 9.5);
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
}