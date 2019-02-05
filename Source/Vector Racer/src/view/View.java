package view;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ModelAPI;

import java.io.File;

public class View implements ViewAPI{

    // FileChooser
    private FileChooser fileChooser;

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

        // FileChooser
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory();

        // Model Backend
        this.model = model;

        // Primary Stage
        this.primaryStage = primaryStage;

        // Racetrack Game Area Pane
//        racetrackPane = new RacetrackPane();

        // Views (Full Screen Content Panes)
        createMainMenuPane();
        createPlayMenuPane();
        //createGamePane(); // can only be called once model has been populated with a racetrack

        // Create main scene with main menu as default root content, and show
        primaryStage.setScene(new Scene(mainMenuPane));
        primaryStage.show();


    }

    @Override
    public void createMainMenuPane(){
        mainMenuPane = new GridPane();

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> changeRootContent(playMenuPane));
        Button testButton = new Button("test");
        testButton.setOnAction(e -> System.out.println("TEST BUTTON CLICK"));
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> primaryStage.close());

        GridPane.setConstraints(playButton, 1,1);
        GridPane.setConstraints(testButton, 1,2);
        GridPane.setConstraints(quitButton, 1,3);
        ColumnConstraints column0 = new ColumnConstraints();
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column0.setPercentWidth(25);
        column1.setPercentWidth(50);
        column2.setPercentWidth(25);
        mainMenuPane.getColumnConstraints().addAll(column0, column1, column2);

        mainMenuPane.getChildren().addAll(playButton, testButton, quitButton);
    }

    @Override
    public void createPlayMenuPane(){
        playMenuPane = new GridPane();

        Button pvpButton = new Button("Player vs Player");
        pvpButton.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
        });

        playMenuPane.getChildren().addAll(pvpButton);
    }

    @Override
    public void createGamePane(){
        gamePane = new BorderPane();

        class Tile extends StackPane {
            private int row, col;

        }
    }

    @Override
    public GridPane getMainMenuPane(){
        return mainMenuPane;
    }

    @Override
    public GridPane getPlayMenuPane(){
        return playMenuPane;
    }

    @Override
    public BorderPane getGamePane() {
        return gamePane;
    }

    @Override
    public void changeRootContent(Pane pane) {
        System.out.println("Changing root content pane to: "+pane);
        primaryStage.getScene().setRoot(pane);
    }
}
