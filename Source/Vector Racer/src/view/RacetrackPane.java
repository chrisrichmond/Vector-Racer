package view;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.*;
import model.geometry.Point;
import utilities.VectorConstants;

import java.util.*;

/**
 * Class representing a racetrack display panel in the view.
 */
public class RacetrackPane extends Pane implements RacetrackPaneAPI {

    /**
     * The border of the RacetrackPane.
     */
    private Rectangle trackBorder;

    /**
     * The number of rows of display tiles.
     */
    private int rows;

    /**
     * The number of columns of display tiles.
     */
    private int cols;

    /**
     * The size of display tiles.
     */
    private int tileSize;

    /**
     * The RacetrackAPI to draw for.
     */
    private RacetrackAPI racetrack;

    /**
     * The list of TileSprites.
     */
    private List<TileSprite> tileSprites;

    /**
     * The list of RacerSprites.
     */
    private List<RacerSprite> racerSprites;

    /**
     * A drop shadown effect that can be applied to sprites.
     */
    private DropShadow dropShadow;

    /**
     * An inner shadow effect that can be applied to sprites.
     */
    private InnerShadow innerShadow = new InnerShadow();

    /**
     * Creates a new instance of RacetrackPane
     * @param racetrack the RacetrackAPI to draw
     */
    public RacetrackPane(RacetrackAPI racetrack){
        this.rows = racetrack.getRows();
        this.cols = racetrack.getCols();
        this.tileSize = VectorConstants.TILESIZE;
        this.racetrack = racetrack;
        this.trackBorder = new Rectangle(cols*tileSize, rows*tileSize);
        this.tileSprites = new ArrayList<>();
        this.racerSprites = new ArrayList<>();
        this.dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        innerShadow.setOffsetX(2.0f);
        innerShadow.setOffsetY(2.0f);

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

    @Override
    public void drawTiles(){
        for (Tile currentTile: racetrack.getTiles()){
            TileSprite currentTileSprite = new TileSprite(currentTile.getStartY(), currentTile.getStartX(), currentTile.getColor());
            if(currentTile instanceof CheckpointTile){
                Color zoneColor = (VectorConstants.CHECKPOINT_COLORS[((CheckpointTile)currentTile).getZoneNumber()]);
                Rectangle topCorner = new Rectangle(tileSize/2, tileSize/2);
                Rectangle bottomCorner = new Rectangle(tileSize/2, tileSize/2);
                Text zoneText = new Text(""+(((CheckpointTile) currentTile).getZoneNumber()));

                topCorner.setStroke(zoneColor);
                topCorner.setFill(zoneColor);
                bottomCorner.setStroke(zoneColor);
                bottomCorner.setFill(zoneColor);

                topCorner.setTranslateX(tileSize/4);
                topCorner.setTranslateY(-(tileSize/4));
                bottomCorner.setTranslateX(-(tileSize/4));
                bottomCorner.setTranslateY(tileSize/4);

                currentTileSprite.getChildren().addAll(topCorner, bottomCorner);
                if(((CheckpointTile) currentTile).getZoneNumber() != 0) {
                    topCorner.setOpacity(0.5);
                    bottomCorner.setOpacity(0.5);
                    currentTileSprite.getChildren().add(zoneText);
                }

                currentTileSprite.getTile().toFront();
            }

            tileSprites.add(currentTileSprite);
            getChildren().add(currentTileSprite);
        }
    }

    @Override
    public void drawNextPossiblePositions(PlayerAPI player){
        clearCircleSelectorsFromTrack();

        List<CircleSprite> circleSelectors = new ArrayList<>();

        for(int i = 0; i < player.getRacer().getPossibleNextPoints(racetrack, player.isAI()).size(); i++){
            int row = player.getRacer().getPossibleNextPoints(racetrack, player.isAI()).get(i).getY();
            int col = player.getRacer().getPossibleNextPoints(racetrack, player.isAI()).get(i).getX();
            circleSelectors.add(new CircleSprite(row, col, VectorConstants.CIRCLESELECTOR_COLOR, null, 1, 2.0));
        }

        // draw impossible circles
        for(int i = 0; i < player.getRacer().getImpossibleNextPoints(racetrack, player.isAI()).size(); i++){
            int row = player.getRacer().getImpossibleNextPoints(racetrack, player.isAI()).get(i).getY();
            int col = player.getRacer().getImpossibleNextPoints(racetrack, player.isAI()).get(i).getX();
            circleSelectors.add(new CircleSprite(row, col, VectorConstants.CIRCLEIMPOSSIBLE_COLOR, null, 0.8, 1.2));
        }

        getChildren().addAll(circleSelectors);
    }

    @Override
    public void drawRacerSprites(List<PlayerAPI> players){
        clearRacerSpritesFromTrack();

        for(PlayerAPI currentPlayer: players){
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
                    LineSprite line = new LineSprite(previous.getY(), previous.getX(), currentPoint.getY(), currentPoint.getX(), currentPlayer.getColor(), VectorConstants.RACERTRAIL_THICKNESS);
//                    line.setEffect(dropShadow);
                    lineSprites.add(line);
                }
                previous = currentPoint;
            }

            getChildren().addAll(lineSprites);
            RacerSprite racerSprite = new RacerSprite(row, col, color, color, 1);
            racerSprite.setEffect(dropShadow);
//            racerSprite.setEffect(innerShadow);
            racerSprites.add(racerSprite);
        }
        getChildren().addAll(racerSprites);
    }

    /**
     * Clears all RacerSprites from this RacetrackPane.
     */
    private void clearRacerSpritesFromTrack(){

        for(Iterator<Node> iterator = getChildren().iterator(); iterator.hasNext();){
            Node currentChild = iterator.next();
            if(currentChild instanceof RacerSprite){
                iterator.remove();
            }
        }

        racerSprites.clear();
    }

    /**
     * Clears all next possible position circle selectors from this RacetrackPane.
     */
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

    /**
     * Local class for representing a player's vehicle on screen.
     */
    private class RacerSprite extends CircleSprite {

        /**
         * Creates a new instance of RacerSprite.
         * @param row the row to draw on
         * @param col the column to draw on
         * @param color the Color to draw the sprite
         * @param fill the Color to fill the sprite
         * @param opacity the opacity value of the sprite
         */
        public RacerSprite(int row, int col, Color color, Color fill, double opacity) {
            super(row, col, color, fill, opacity, 1.0);
        }
    }

    /**
     * Local class for representing lines on screen.
     */
    private class LineSprite extends Line {

        /**
         * Creates a new instance of LineSprite.
         * @param startRow the row to start drawing on
         * @param startCol the column to start drawing on
         * @param endRow the row to finish drawing on
         * @param endCol the column to finish drawing on
         * @param color the Color to draw the sprite
         * @param thickness the stroke width value of the sprite
         */
        public LineSprite(int startRow, int startCol, int endRow, int endCol, Color color, double thickness){
            super(startCol*tileSize, startRow*tileSize, endCol*tileSize, endRow*tileSize);

            setStroke(color);
            setStrokeWidth(thickness);
        }
    }

    /**
     * Local class for representing circles on screen as a StackPane.
     */
    private class CircleSprite extends StackPane {

        /**
         * The row to draw on.
         */
        private int row;

        /**
         * The column to draw on.
         */
        private int col;

        /**
         * The circle representing this CircleSprite.
         */
        private Ellipse circle;

        /**
         * Creates a new instance of CircleSprite.
         * @param row the row to draw on
         * @param col the column to draw on
         * @param color the Color to draw the sprite
         * @param fill the Color to fill the sprite
         * @param opacity the opacity value of the sprite
         * @param borderThickness the stroke width value of the border of the sprite
         */
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

    /**
     * Local class representing for representing Tiles on screen as a StackPane.
     */
    private class TileSprite extends StackPane {

        /**
         * The row to draw on.
         */
        private int row;

        /**
         * The column to draw on.
         */
        private int col;

        /**
         * The Rectangle representing this TileSprite.
         */
        private Rectangle tile;

        /**
         * Debug coordinate overlay text.
         */
        private Text debugCoord;

        /**
         * Creates a new instance of TileSprite
         * @param row the row to draw on
         * @param col the column to draw on
         * @param color the Color to draw the sprite
         */
        public TileSprite(int row, int col, Color color){
            this.row = row;
            this.col = col;
            this.tile = new Rectangle(tileSize, tileSize);

            tile.setStroke(VectorConstants.GRIDLINE_COLOR);
            tile.setFill(color);

            debugCoord = new Text();
            debugCoord.setFont(Font.font(tileSize/3));
            debugCoord.setText(row + ", " + col);
            debugCoord.setVisible(VectorConstants.IS_DEBUG_MODE);

            getChildren().add(tile);
            getChildren().add(debugCoord);

            setTranslateX(col * tileSize);
            setTranslateY(row * tileSize);

        }

        /**
         * Returns the Rectangle representing this TileSprite
         * @return the tile rectangle
         */
        public Rectangle getTile() {
            return tile;
        }
    }

}
