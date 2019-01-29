package view;

import javafx.scene.layout.Pane;

public class GamePane extends Pane {

    /*
    needs to consist of all elements on screen during gameplay, such as
    the racetrack grid and any other surrounding on-screen components
     */

    private TileGridPane tileGridPane;
    private int tileSize;

    public GamePane(int prefWidth, int prefHeight){
        setPrefSize(prefWidth, prefHeight);
        createGrid(10, 10, 40);
    }

    public void createGrid(int rows, int cols, int tileSize){
        tileGridPane = new TileGridPane(rows, cols, tileSize);
        this.getChildren().add(tileGridPane);
    }

}
