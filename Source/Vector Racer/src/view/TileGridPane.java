package view;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileGridPane extends Pane {

    private Tile[][] grid;
    private Rectangle gridBorder;
    private int rows;
    private int cols;
    private int tileSize;

    public TileGridPane(int rows, int cols, int tileSize, double parentWidth, double parentHeight){
        setPrefSize(parentWidth/2, parentHeight/2);
        //setTranslateX(parentWidth/4);

        this.gridBorder = new Rectangle(getWidth(), getHeight());
        this.grid = new Tile[rows][cols]; // todo rows and cols or X and Y??
        this.rows = rows;
        this.cols = cols;
        this.tileSize = tileSize;

        this.gridBorder.setFill(null);
        this.gridBorder.setStroke(Color.BLACK);
        getChildren().add(this.gridBorder);

//        for(int col = 0; col < cols; col++){
//            for(int row = 0; row < rows; row++){
//                Tile tile = new Tile(row, col);
//
//                grid[row][col] = tile;
//                getChildren().add(tile);
//            }
//        }
    }

    private class Tile extends StackPane {

        private int row;
        private int col;

        private Rectangle tileBorder;

        public Tile(int row, int col){
            this.row = row;
            this.col = col;
            this.tileBorder = new Rectangle(tileSize, tileSize);

            tileBorder.setStroke(Color.LIGHTGRAY); // todo look into css or other global styling

            getChildren().add(tileBorder);

            setTranslateX(col * tileSize);
            setTranslateY(row * tileSize);

        }

    }

}
