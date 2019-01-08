package prototypes.tileselection;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.awt.*;

public class TileSelectApp extends Application {

    private static final int TILE_SIZE = 40;
    private static final int W = 800;
    private static final int H = 600;

    private static final int X_TILES = W / TILE_SIZE;
    private static final int Y_TILES = H / TILE_SIZE;

    private Tile[][] grid = new Tile[X_TILES][Y_TILES];
    private Scene scene;

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(W, H);

        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = new Tile(x, y);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        return root;
    }

    private class Tile extends StackPane {
        private int x, y;
        private boolean hasBomb;

        private Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
        //private Text text  = new Text();

        public Tile(int x, int y){
            this.x = x;
            this.y = y;

            border.setStroke()
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
