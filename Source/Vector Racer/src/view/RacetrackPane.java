package view;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import model.RacetrackAPI;
import model.Tile;
import utilities.VectorConstants;

import java.util.ArrayList;
import java.util.List;

public class RacetrackPane extends Pane {

    private Rectangle trackBorder;
    private int rows;
    private int cols;
    private int tileSize;
    private List<TileView> tileViews;

    public RacetrackPane(RacetrackAPI racetrack){
        this.rows = racetrack.getRows();
        this.cols = racetrack.getCols();
        this.tileSize = VectorConstants.TILESIZE;
        this.trackBorder = new Rectangle(cols*tileSize, rows*tileSize);
        this.tileViews = new ArrayList<>();

        trackBorder.setStroke(Color.BLACK);
        trackBorder.setFill(null);
        getChildren().add(trackBorder);

        for (Tile currentTile: racetrack.getTiles()){
            TileView currentTileView = new TileView(currentTile.getStartY(), currentTile.getStartX(), currentTile.getColor());
            tileViews.add(currentTileView);
            getChildren().add(currentTileView);
        }

        CircleSelector test = new CircleSelector(1, 1, Color.GREEN);
        CircleSelector test2 = new CircleSelector(1, 2, Color.GREEN);
        CircleSelector test3 = new CircleSelector(2, 2, Color.GREEN);
        getChildren().addAll(test,test2,test3);


    }

    private class CircleSelector extends StackPane {

        private int row;
        private int col;

        private Ellipse circle;

        public CircleSelector(int row, int col, Color color){
            this.row = row;
            this.col = col;
            this.circle = new Ellipse((tileSize/2), (tileSize/2));

            circle.setStroke(color);
            circle.setFill(null);

            getChildren().add(circle);

            setTranslateX((col * tileSize)-(tileSize/2));
            setTranslateY((row * tileSize)-(tileSize/2));
        }
    }

    private class TileView extends StackPane {

        private int row;
        private int col;

        private Rectangle tile;

        public TileView(int row, int col, Color color){
            this.row = row;
            this.col = col;
            this.tile = new Rectangle(tileSize, tileSize);

            tile.setStroke(Color.BLACK); // todo look into css or other global styling
            tile.setFill(color);

            getChildren().add(tile);

            setTranslateX(col * tileSize);
            setTranslateY(row * tileSize);

        }

    }

    /*
    public RacetrackPane(int rows, int cols, int tileSize, double parentWidth, double parentHeight){

        System.out.println("in tileGridPane | parentWidth: "+parentWidth+", parentHeight: "+parentHeight);
        //setTranslateX(parentWidth/4);

        this.gridBorder = new Rectangle(parentWidth/2, parentHeight/2);
        this.grid = new Tile[rows][cols]; // todo rows and cols or X and Y??
        this.rows = rows;
        this.cols = cols;
        this.tileSize = tileSize;

        this.gridBorder.setFill(null);
        this.gridBorder.setStroke(Color.BLACK);
        getChildren().add(this.gridBorder);

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
    */

}
