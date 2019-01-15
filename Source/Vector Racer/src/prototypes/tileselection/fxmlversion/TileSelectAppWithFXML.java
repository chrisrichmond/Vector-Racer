package prototypes.tileselection.fxmlversion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TileSelectAppWithFXML extends Application {

    private static final int TILE_SIZE = 40;
    private static final int W = 800;
    private static final int H = 600;

    private static final int X_TILES = W / TILE_SIZE;
    private static final int Y_TILES = H / TILE_SIZE;

    private Tile[][] grid = new Tile[X_TILES][Y_TILES];
    private Scene scene;

    private Parent createTiles(){
        Pane root = new Pane();
        root.setPrefSize(W, H);

        for(int y = 0; y < Y_TILES; y++){
            for(int x = 0; x < X_TILES; x++){
                Tile tile = new Tile(x, y);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        return root;
    }

    private class Tile extends StackPane{
        // note StackPane is used as EACH individual Tile is using its own layout and things may wish to be displayed on top of them
        // the overall layout of the app is not a StackPane, just each individual Tile's internals

        private int x, y;
        private boolean isOpen = false;

        private Rectangle border = new Rectangle(TILE_SIZE , TILE_SIZE );
        private Text text  = new Text();

        public Tile(int x, int y){
            this.x = x;
            this.y = y;

            border.setStroke(Color.LIGHTGRAY);

            text.setFont(Font.font(18));
            text.setVisible(false);

            getChildren().addAll(border, text);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);

            setOnMouseClicked(e -> open());
        }

        public void open(){
            if(isOpen)
                return;

            isOpen = true;
            text.setVisible(true);
            border.setFill(null);

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("tileselectview.fxml"));

        primaryStage.setTitle("Tile Select Prototype with FXML");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        /*
        Scene scene = new Scene(createContent());

        primaryStage.setScene(scene);
        primaryStage.show();
        */

    }

    public static void main(String[] args){
        launch(args);
    }

}