package view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileGridPane extends Pane {

    private Tile[][] grid;
    private int rows;
    private int cols;
    private int tileSize;

    public TileGridPane(int rows, int cols, int tileSize){
        this.grid = new Tile[rows][cols]; // todo rows and cols or X and Y??
        this.rows = rows;
        this.cols = cols;
        this.tileSize = tileSize;

        for(int col = 0; col < cols; col++){
            for(int row = 0; row < rows; row++){
                Tile tile = new Tile(row, col);

                grid[row][col] = tile;
                getChildren().add(tile);
            }
        }
    }

    private class Tile extends StackPane {

        private int row;
        private int col;

        private Rectangle border;

        public Tile(int row, int col){
            this.row = row;
            this.col = col;

            border.setStroke(Color.LIGHTGRAY); // todo look into css or other global styling

            getChildren().addAll(border);

            setTranslateX(col * tileSize);
            setTranslateY(row * tileSize);

        }

    }

}
