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
import utilities.Observer;
import utilities.VectorConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class RacetrackPane extends Pane {

    private Rectangle trackBorder;
    private int rows;
    private int cols;
    private int tileSize;
    private List<TileSprite> tileSprites;
    private List<RacerSprite> racerSprites;
    //private HashMap<RacerSprite, List<LineSprite>> racerAndTrailSprites;

    public RacetrackPane(RacetrackAPI racetrack){
        this.rows = racetrack.getRows();
        this.cols = racetrack.getCols();
        this.tileSize = VectorConstants.TILESIZE;
        this.trackBorder = new Rectangle(cols*tileSize, rows*tileSize);
        this.tileSprites = new ArrayList<>();
        this.racerSprites = new ArrayList<>();
        //this.racerAndTrailSprites = new HashMap<>();

        trackBorder.setStroke(Color.BLACK);
        trackBorder.setFill(null);
        getChildren().add(trackBorder);

        for (Tile currentTile: racetrack.getTiles()){
            TileSprite currentTileSprite = new TileSprite(currentTile.getStartY(), currentTile.getStartX(), currentTile.getColor());
            tileSprites.add(currentTileSprite);
            getChildren().add(currentTileSprite);
        }

//        CircleSprite test = new CircleSprite(1, 1, Color.GREEN, null, 1);
//        CircleSprite test2 = new CircleSprite(1, 2, Color.GREEN, Color.GREEN, 0.25);
//        CircleSprite test3 = new CircleSprite(2, 2, Color.GREEN, Color.RED, 1);
//
//        LineSprite test4 = new LineSprite(1, 1, 4, 7, Color.CYAN);
//
//        getChildren().addAll(test,test2,test3, test4);


    }

    public void drawNextPossiblePositions(Player player){
        clearCircleSelectorsFromTrack();

        List<CircleSprite> circleSelectors = new ArrayList<>();

        for(int i = 0; i < player.getPossibleNextPoints().size(); i++){
            int row = player.getPossibleNextPoints().get(i).getY();
            int col = player.getPossibleNextPoints().get(i).getX();
            circleSelectors.add(new CircleSprite(row, col, VectorConstants.CIRCLESELECTOR_COLOR, VectorConstants.CIRCLESELECTOR_COLOR, 0.25));
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

//            racerAndTrailSprites.put(new RacerSprite(row, col, color, null, 1), new ArrayList<>());
//            int lastRow = currentRacer.getPosition().getY();
//            int lastCol = currentRacer.getPosition().getX();
//            Stack<Integer> rowRoute = currentRacer.getPointRoute()

            racerSprites.add(new RacerSprite(row, col, color, null, 1));
        }
        getChildren().addAll(racerSprites);
    }

    private void clearRacerSpritesFromTrack(){
        System.out.println("clearing racerSprites");
        for(Node currentChild: getChildren()){
            if(currentChild instanceof RacerSprite){
                getChildren().remove(currentChild);
            }
        }
        racerSprites.clear();
    }

    private void clearCircleSelectorsFromTrack(){
        System.out.println("clearing circle selectors");
        for(Node currentChild: getChildren()){
            if(currentChild instanceof CircleSprite){
                if(!(currentChild instanceof RacerSprite)){
                    getChildren().remove(currentChild);
                }
            }
        }
    }

    private class RacerSprite extends CircleSprite {

        public RacerSprite(int row, int col, Color color, Color fill, double opacity) {
            super(row, col, color, fill, opacity);
        }
    }

    private class LineSprite extends StackPane {

        private int startRow, startCol;
        private int endRow, endCol;
        private Line line;

        public LineSprite(int startRow, int startCol, int endRow, int endCol, Color color){
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
            this.line = new Line(startCol*tileSize, startRow*tileSize, endCol*tileSize, endRow*tileSize);

            line.setStroke(color);

            getChildren().add(line);

            setTranslateX(startCol * tileSize);
            setTranslateY(startRow * tileSize);
        }

    }

    private class CircleSprite extends StackPane {

        private int row;
        private int col;
        private Ellipse circle;

        public CircleSprite(int row, int col, Color color, Color fill, double opacity){
            this.row = row;
            this.col = col;
            this.circle = new Ellipse((tileSize/2), (tileSize/2));

            circle.setStroke(color);
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
