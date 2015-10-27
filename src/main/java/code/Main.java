package code;

import code.service.LevelParser;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


/**
 * Created by eric on 10/26/15.
 */
public class Main extends Application {
    private double sceneWidth = 1024;
    private double sceneHeight = 768;

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

        Group root = new Group();

        int n = 10;
        int m = 10;

        double gridWidth = sceneWidth / n;
        double gridHeight = sceneHeight / m;

        MyNode[][] playfield = new MyNode[n][m];

        // initialize playfield
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // create node
                MyNode node = new MyNode("Item " + i + "/" + j, i * gridWidth, j * gridHeight, gridWidth, gridHeight);

                // add node to group
                root.getChildren().add(node);

                // add to playfield for further reference using an array
                playfield[i][j] = node;

            }
        }

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setScene(scene);
        stage.show();
    }

    public static class MyNode extends StackPane {
        public MyNode(String name, double x, double y, double width, double height) {
            // Create Rectangle
            Rectangle rectangle = new Rectangle(width, height);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.LIGHTBLUE);

            // Create Label
            Label label = new Label(name);

            // set Position
            setTranslateX(x);
            setTranslateY(y);

            getChildren().addAll(rectangle, label);
        }
    }
}