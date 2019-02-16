package view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

        LineSprite debugVertLine = new LineSprite(0, cols/2, rows, cols/2, Color.LIMEGREEN, 3.0);
        LineSprite debugHorizLine = new LineSprite(rows/2, 0, rows/2, cols, Color.LIMEGREEN, 3.0);
        debugVertLine.setVisible(VectorConstants.IS_DEBUG_MODE);
        debugHorizLine.setVisible(VectorConstants.IS_DEBUG_MODE);
        getChildren().addAll(debugVertLine, debugHorizLine);

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

            Point previous = null;
            List<LineSprite> lineSprites = new ArrayList<>();
            for(Point currentPoint: currentRacer.getPointRoute()){
                if(previous == null){
                    previous = currentPoint;
                }else{
                    System.out.println("----------------------------------");
                    System.out.println("previous y: "+previous.getY());
                    System.out.println("previous x: "+previous.getX());
                    System.out.println("current y: "+currentPoint.getY());
                    System.out.println("current x: "+currentPoint.getX());
                    System.out.println("");
                    System.out.println("racer velocity");
                    System.out.println("yVelo(rowVelo): "+currentRacer.getVelocity().getYVelo()+ " xVelo(colVelo): "+currentRacer.getVelocity().getXVelo());
                    System.out.println("startY(startRow): "+currentRacer.getVelocity().getStart().getY());
                    System.out.println("startX(startCol): "+currentRacer.getVelocity().getStart().getX());
                    System.out.println("endY(endRow): "+currentRacer.getVelocity().getEnd().getY());
                    System.out.println("endX(endCol): "+currentRacer.getVelocity().getEnd().getX());
                    System.out.println("----------------------------------");

                    int prevYoffset = previous.getY();
                    int prevXoffset = previous.getX();
                    if(previous.getY() > currentPoint.getY()){
                        prevYoffset--;
                        System.out.println("y offset to: "+prevYoffset);
                    }
                    if(previous.getX() > currentPoint.getX()){
                        prevXoffset--;
                        System.out.println("x offset to: "+prevXoffset);
                    }

                    LineSprite line = new LineSprite(prevYoffset, prevXoffset, currentPoint.getY(), currentPoint.getX(), currentPlayer.getColor(), VectorConstants.RACERTRAIL_THICKNESS);
                    lineSprites.add(line);
                }
                previous = currentPoint;
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
        private Text debugCoord;

        public TileSprite(int row, int col, Color color){
            this.row = row;
            this.col = col;
            this.tile = new Rectangle(tileSize, tileSize);

            tile.setStroke(VectorConstants.GRIDLINE_COLOR); // todo look into css or other global styling
            tile.setFill(color);

            debugCoord = new Text();
            debugCoord.setFont(Font.font(tileSize/2));
            debugCoord.setText(row + " " + col);
            debugCoord.setVisible(VectorConstants.IS_DEBUG_MODE);

            getChildren().add(tile);
            getChildren().add(debugCoord);

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
