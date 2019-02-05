package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.ModelAPI;

public class View {

    // Model Backend
    private ModelAPI model;

    // Primary Stage and Root Content Node
    private Stage primaryStage;
    private Parent root;

    // Racetrack Game Area Pane
    private RacetrackPane racetrackPane;    // could maybe just be one of the stock layout panes?

    // Views (Full Screen Content Panes)
    private GridPane mainMenuPane;
    private GridPane playMenuPane;
    private BorderPane gamePane;

    public View(ModelAPI model, Stage primaryStage){
        // Model Backend
        this.model = model;

        // Primary Stage
        this.primaryStage = primaryStage;

        // Racetrack Game Area Pane
        racetrackPane = new RacetrackPane();

        // Views (Full Screen Content Panes)
        createMainMenuPane();
        createPlayMenuPane();
        createGamePane();


    }

    private void createMainMenuPane(){
        mainMenuPane = new GridPane();
        Button playButton

        mainMenuPane.getChildren().addAll();
    }

    private void createPlayMenuPane(){
        playMenuPane = new GridPane();
    }

    private void createGamePane(){
        gamePane = new BorderPane();
    }

    public GridPane getMainMenuPane(){
        return mainMenuPane;
    }

    public GridPane getPlayMenuPane(){
        return playMenuPane;
    }

    public BorderPane getGamePane() {
        return gamePane;
    }
}
