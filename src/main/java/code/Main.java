package code;

import code.model.Cell;
import code.model.LayoutMap;
import code.service.LevelParser;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Created by eric on 10/26/15.
 */
public class Main extends Application {
    private double sceneWidth = 800;
    private double sceneHeight = 800;

    public static void main(String[] args) {
        try {
            LevelParser.parse("./src/main/resources/level1.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chips Challenge - Created by Eric Pfeiffer");

        // Keyboard Events

        Group root = new Group();

        int n = 25;
        int m = 25;

        double gridWidth = sceneWidth / n;
        double gridHeight = sceneHeight / m;

        MyNode[][] playfield = new MyNode[n][m];

        Image image = new Image(new BufferedInputStream(new FileInputStream(new File("./src/main/resources/block1.png"))));

        // parse level file and display
        LayoutMap map = LevelParser.parse("./src/main/resources/level1.json");

        // Initialize Playfield from parsed map
        for (Cell cell : map.getLayout().getCells()) {
            int x = cell.getPoint().x, y = cell.getPoint().y;

            System.out.println(cell.getType());
            if (cell.getType().getId() == 7) {
                image = new Image(
                        new BufferedInputStream(
                                new FileInputStream(
                                        new File("./src/main/resources/blockBottomRight.png")
                                )
                        )
                );
            }

            MyNode node = new MyNode(null, x * gridWidth, y * gridHeight, gridWidth, gridHeight, image);
            root.getChildren().add(node);

            playfield[x][y] = node;
        }

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setScene(scene);
        moveChipOnKeyPress(scene);
        stage.show();
    }

    private void moveChipOnKeyPress(final Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        System.out.println("Move Up");
                        break;
                    case DOWN:
                        System.out.println("Move Down");
                        break;
                    case RIGHT:
                        System.out.println("Move Right");
                        break;
                    case LEFT:
                        System.out.println("Move Left");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public static class MyNode extends StackPane {
        public MyNode(String name, double x, double y, double width, double height, Image image) {
            // Create Rectangle
            Rectangle rectangle = new Rectangle(width, height);
            rectangle.setStroke(Color.BLACK);

            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(height);
            imageView.setFitWidth(width);

            // set Position
            setTranslateX(x);
            setTranslateY(y);

            getChildren().addAll(rectangle, imageView);
        }
    }
}