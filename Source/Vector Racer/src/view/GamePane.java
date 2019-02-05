package view;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.VectorConstants;

public class GamePane extends BorderPane {

    /*
    needs to consist of all elements on screen during gameplay, such as
    the racetrack grid and any other surrounding on-screen components
     */

    private RacetrackPane racetrackPane;
    private int tileSize;
    private boolean isGridInitialised = false;
    private Rectangle border;
    private Button testButton;

    public GamePane(int width, int height){
        drawBorder(width, height);
        createGrid("small", 20);

//        testButton = new Button("test");
//        testButton.setTranslateX(width/2);
//        testButton.setTranslateY(height/2);
//        getChildren().add(testButton);
//        testButton.setOnAction(event -> {
//            System.out.println(getWidth());
//            System.out.println(getHeight());
//        });
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
        racetrackPane = new RacetrackPane(rows, cols, tileSize, getWidth(), getHeight());
        setCenter(racetrackPane);
    }


    // todo
    /*
    public boolean deleteGrid(){

    }
    */

}
