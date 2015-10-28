package code;

import code.model.Cell;
import code.model.LayoutMap;
import code.service.ImageMapper;
import code.service.LevelParser;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


/**
 * Created by eric on 10/26/15.
 */
public class Main extends Application {
    private double sceneWidth = 800;
    private double sceneHeight = 800;

    public static void main(String[] args) {
        /*Gson gson = new Gson();
        LayoutMap map = new LayoutMap();
        ArrayList<Cell> cells = new ArrayList<Cell>();

        for(int i=0; i< 25; i++) {
            for(int j=0; j<25; j++) {
                cells.add(new Cell(new Point(i,j), new Type()));
            }
        }
        Layout layout = new Layout();
        layout.setCells(cells);
        map.setLayout(layout);

        File file = new File("./default.json");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gson.toJson(map));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chips Challenge - Created by Eric Pfeiffer");

        GridPane gridPane = new GridPane();

        int n = 25;
        int m = 25;

        double gridWidth = sceneWidth / n;
        double gridHeight = sceneHeight / m;

        Cell[][] playfield = new Cell[n][m];

        // parse level file and display
        LayoutMap map = LevelParser.parse("./src/main/resources/levels/default.json");

        // Initialize Playfield from parsed map
        for (Cell cell : map.getLayout().getCells()) {
            int x = cell.getPoint().x,
                    y = cell.getPoint().y;

            MyNode node = new MyNode(cell);
            gridPane.add(node, x, y);
            playfield[x][y] = cell;
        }

        Scene scene = new Scene(gridPane, sceneWidth, sceneHeight);
        stage.setScene(scene);
        stage.show();
    }

    private void addKeyListeners(final Scene scene) {
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

    /**
     * Abstraction for cells to be able to be displayed on screen as Image
     */
    private class MyNode extends ImageView {
        public MyNode(Cell cell) {
            try {
                setImage(ImageMapper.getImage(cell.getType()));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}