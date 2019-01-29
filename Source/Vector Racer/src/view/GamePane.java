package view;

import javafx.scene.layout.Pane;

public class GamePane extends Pane {

    /*
    needs to consist of all elements on screen during gameplay, such as
    the racetrack grid and any other surrounding on-screen components
     */

    private static final int SMALL_ROWS = 40;   // Y
    private static final int SMALL_COLS = 60;   // X
    private static final int MEDIUM_ROWS = 60;  // Y
    private static final int MEDIUM_COLS = 80;  // X
    private static final int LARGE_ROWS = 80;   // Y
    private static final int LARGE_COLS = 100;  // X

    private TileGridPane tileGridPane;
    private int tileSize;
    private boolean isGridInitialised;

    public GamePane(int prefWidth, int prefHeight){
        setPrefSize(prefWidth, prefHeight);
        isGridInitialised = false;
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
                    initGrid(SMALL_ROWS, SMALL_COLS, tileSize);
                    break;
                case("medium"):
                    initGrid(MEDIUM_ROWS, MEDIUM_COLS, tileSize);
                    break;
                case("large"):
                    initGrid(LARGE_ROWS, LARGE_COLS, tileSize);
                    break;
                default:
                        System.out.println("Invalid size String parameter passed to GamePane.createGrid()");
                        success = false;
            }
        }
        return success;
    }

    private void initGrid(int rows, int cols, int tileSize){
        tileGridPane = new TileGridPane(rows, cols, tileSize);
        this.getChildren().add(tileGridPane);
    }


    // todo
    /*
    public boolean deleteGrid(){

    }
    */

}
