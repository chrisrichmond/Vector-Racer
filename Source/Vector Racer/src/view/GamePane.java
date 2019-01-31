package view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.VectorConstants;

public class GamePane extends Pane {

    /*
    needs to consist of all elements on screen during gameplay, such as
    the racetrack grid and any other surrounding on-screen components
     */

    private TileGridPane tileGridPane;
    private int tileSize;
    private boolean isGridInitialised = false;
    private Rectangle border;

    public GamePane(int width, int height){
        System.out.println("In gamePane | width: "+width+", height:" +height);
        drawBorder(width, height);
        createGrid("small", 20);
    }

    public void drawBorder(int width, int height){
        border = new Rectangle(width, height);
        border.setStroke(Color.BLACK);
        this.border.setFill(null);
        getChildren().add(this.border);
    }

    public boolean createGrid(String size, int tileSize){
        this.tileSize = tileSize;
        boolean success = false;

        if(isGridInitialised){
            System.out.println("Grid already exists");
            return success;
        }else{
            success = true;
            switch(size.toLowerCase()){
                case("small"):
                    initGrid(VectorConstants.SMALL_ROWS, VectorConstants.SMALL_COLS, tileSize);
                    break;
                case("medium"):
                    initGrid(VectorConstants.MEDIUM_ROWS, VectorConstants.MEDIUM_COLS, tileSize);
                    break;
                case("large"):
                    initGrid(VectorConstants.LARGE_ROWS, VectorConstants.LARGE_COLS, tileSize);
                    break;
                default:
                        System.out.println("Invalid size String parameter passed to GamePane.createGrid()");
                        success = false;
            }
        }
        return success;
    }

    private void initGrid(int rows, int cols, int tileSize){
        System.out.println("Initialising grid");
        System.out.println("rows: "+rows);
        System.out.println("cols: "+cols);
        System.out.println("getWidth(): "+getWidth());
        System.out.println("getHeight(): "+getHeight());
        tileGridPane = new TileGridPane(rows, cols, tileSize, getWidth(), getHeight());
        this.getChildren().add(tileGridPane);
    }


    // todo
    /*
    public boolean deleteGrid(){

    }
    */

}
