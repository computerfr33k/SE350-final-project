package code;

import code.asset.AssetManager;
import code.model.BlankTile;
import code.model.LayoutMap;
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
        ArrayList<BlankTile> cells = new ArrayList<BlankTile>();

        for(int i=0; i< 25; i++) {
            for(int j=0; j<25; j++) {
                cells.add(new BlankTile(new Point(i,j), new Type()));
            }
        }
        Layout layout = new Layout();
        layout.setBlankTiles(cells);
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
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chips Challenge - Created by Eric Pfeiffer");
        AssetManager.getInstance().loadAssets("./src/main/resources/images");
        AssetManager.getInstance().logCached();

        GridPane gridPane = new GridPane();

        int n = 25;
        int m = 25;

        double gridWidth = sceneWidth / n;
        double gridHeight = sceneHeight / m;

        BlankTile[][] playfield = new BlankTile[n][m];

        // parse level file and display
        LayoutMap map = LevelParser.parse("./src/main/resources/levels/default.json");

        // Initialize Playfield from parsed map
        for (BlankTile blankTile : map.getLayout().getBlankTiles()) {
            int x = blankTile.getPoint().x,
                    y = blankTile.getPoint().y;

            MyNode node = new MyNode(blankTile);
            gridPane.add(node, x, y);
            playfield[x][y] = blankTile;
        }

        Scene scene = new Scene(gridPane, sceneWidth, sceneHeight);
        addKeyListeners(scene);
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
        public MyNode(BlankTile blankTile) {
            try {
                setImage(AssetManager.getInstance().getImage(blankTile.getClass()));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}