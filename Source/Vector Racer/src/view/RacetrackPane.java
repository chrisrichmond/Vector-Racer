package view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import model.Player;
import model.RacerAPI;
import model.RacetrackAPI;
import model.Tile;
import model.geometry.Point;
import utilities.Observer;
import utilities.VectorConstants;

import java.util.*;

public class RacetrackPane extends Pane {

    private Rectangle trackBorder;
    private int rows;
    private int cols;
    private int tileSize;
    private RacetrackAPI racetrack;
    private List<TileSprite> tileSprites;
    private List<RacerSprite> racerSprites;

    public RacetrackPane(RacetrackAPI racetrack){
        this.rows = racetrack.getRows();
        this.cols = racetrack.getCols();
        this.tileSize = VectorConstants.TILESIZE;
        this.racetrack = racetrack;
        this.trackBorder = new Rectangle(cols*tileSize, rows*tileSize);
        this.tileSprites = new ArrayList<>();
        this.racerSprites = new ArrayList<>();

        trackBorder.setStroke(Color.BLACK);
        trackBorder.setFill(null);
        getChildren().add(trackBorder);

        drawTiles();


//        RacerSprite racerTest = new RacerSprite(5, 5, Color.CYAN, Color.CYAN, 1);
//        getChildren().addAll(racerTest);

//        CircleSprite test = new CircleSprite(1, 1, Color.GREEN, null, 1);
//        CircleSprite test2 = new CircleSprite(1, 2, Color.GREEN, Color.GREEN, 0.25);
//        CircleSprite test3 = new CircleSprite(2, 2, Color.GREEN, Color.RED, 1);
//
//        LineSprite test4 = new LineSprite(1, 1, 4, 7, Color.CYAN);
//
//        getChildren().addAll(test,test2,test3, test4);


    }

    private void drawTiles(){
        for (Tile currentTile: racetrack.getTiles()){
            TileSprite currentTileSprite = new TileSprite(currentTile.getStartY(), currentTile.getStartX(), currentTile.getColor());
            tileSprites.add(currentTileSprite);
            getChildren().add(currentTileSprite);
        }
    }

    public void drawNextPossiblePositions(Player player){
        clearCircleSelectorsFromTrack();

        List<CircleSprite> circleSelectors = new ArrayList<>();

        for(int i = 0; i < player.getPossibleNextPoints().size(); i++){
            int row = player.getPossibleNextPoints().get(i).getY();
            int col = player.getPossibleNextPoints().get(i).getX();
            circleSelectors.add(new CircleSprite(row, col, VectorConstants.CIRCLESELECTOR_COLOR, null, 1, 2.0));
        }

        getChildren().addAll(circleSelectors);
    }

    public void drawRacerSprites(List<Player> players){
        clearRacerSpritesFromTrack();

        for(Player currentPlayer: players){
            RacerAPI currentRacer = currentPlayer.getRacer();
            int row = currentRacer.getPosition().getY();
            int col = currentRacer.getPosition().getX();
            Color color = currentPlayer.getColor();

            List<LineSprite> lineSprites = new ArrayList<>();
            Stack<Point> pointsStack = (Stack<Point>) currentRacer.getPointRoute().clone();
            List<Point> pointsList = new ArrayList<>();
            while(!(pointsStack.empty())){
                pointsList.add(pointsStack.pop());
            }

            for(int i = 1; i < pointsList.size(); i++){
                LineSprite line = new LineSprite(pointsList.get(i-1).getY(), pointsList.get(i-1).getX(), pointsList.get(i).getY(), pointsList.get(i).getX(), currentPlayer.getColor(), VectorConstants.RACERTRAIL_THICKNESS);
                System.out.println("adding line: ");
                System.out.println(line.startRow);
                System.out.println(line.startCol);
                System.out.println(line.endRow);
                System.out.println(line.endCol);
                lineSprites.add(line);
            }
            getChildren().addAll(lineSprites);
            racerSprites.add(new RacerSprite(row, col, color, color, 1));
        }
        getChildren().addAll(racerSprites);
    }

    private void clearRacerSpritesFromTrack(){

        for(Iterator<Node> iterator = getChildren().iterator(); iterator.hasNext();){
            Node currentChild = iterator.next();
            if(currentChild instanceof RacerSprite){
                iterator.remove();
            }
        }

        racerSprites.clear();
    }

    private void clearCircleSelectorsFromTrack(){

        for(Iterator<Node> iterator = getChildren().iterator(); iterator.hasNext();){
            Node currentChild = iterator.next();

            if(currentChild instanceof CircleSprite){
                if(!(currentChild instanceof RacerSprite)){
                    iterator.remove();
                }
            }
        }

    }

    private class RacerSprite extends CircleSprite {

        public RacerSprite(int row, int col, Color color, Color fill, double opacity) {
            super(row, col, color, fill, opacity, 1.0);
        }
    }

    private class LineSprite extends StackPane {

        private int startRow, startCol;
        private int endRow, endCol;
        private Line line;

        public LineSprite(int startRow, int startCol, int endRow, int endCol, Color color, double thickness){
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
            this.line = new Line(startCol*tileSize, startRow*tileSize, endCol*tileSize, endRow*tileSize);

            line.setStroke(color);
            line.setStrokeWidth(thickness);

            getChildren().add(line);

            setTranslateX(startCol * tileSize);
            setTranslateY(startRow * tileSize);
        }

    }

    private class CircleSprite extends StackPane {

        private int row;
        private int col;
        private Ellipse circle;

        public CircleSprite(int row, int col, Color color, Color fill, double opacity, double borderThickness){
            this.row = row;
            this.col = col;
            this.circle = new Ellipse((tileSize/2), (tileSize/2));

            circle.setStroke(color);
            circle.setStrokeWidth(borderThickness);
            circle.setFill(fill);
            circle.setOpacity(opacity);

            getChildren().add(circle);

            setTranslateX((col * tileSize)-(tileSize/2));
            setTranslateY((row * tileSize)-(tileSize/2));
        }
    }

    private class TileSprite extends StackPane {

        private int row;
        private int col;
        private Rectangle tile;

        public TileSprite(int row, int col, Color color){
            this.row = row;
            this.col = col;
            this.tile = new Rectangle(tileSize, tileSize);

            tile.setStroke(VectorConstants.GRIDLINE_COLOR); // todo look into css or other global styling
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
