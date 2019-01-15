package prototypes.circleselection;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class CircleSelectApp extends Application{

    private static final int TILE_SIZE = 40;
    private static final int CIRCLE_SIZE = 20;
    private static final int W = 800;
    private static final int H = 600;

    private static final int X_TILES = W / TILE_SIZE;
    private static final int Y_TILES = H / TILE_SIZE;

    /*
    private Tile[][] grid = new Tile[X_TILES][Y_TILES];
    private Scene scene;

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(W, H);

        for(int y = 0; y < Y_TILES; y++){
            for(int x = 0; x < X_TILES; x++){
                Tile tile = new Tile(x, y);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }
    }
*/
    @Override
    public void start(Stage stage) throws Exception {

    }
}
