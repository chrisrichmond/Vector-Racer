package view;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ModelAPI;

public class View implements ViewAPI{

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
//        racetrackPane = new RacetrackPane();

        // Views (Full Screen Content Panes)
        createMainMenuPane();
        createPlayMenuPane();
        createGamePane();

        primaryStage.setScene(new Scene(mainMenuPane));
        primaryStage.show();


    }

    @Override
    public void createMainMenuPane(){
        mainMenuPane = new GridPane();

        Button playButton = new Button("Play");
        playButton.setOnMouseClicked(e -> changeRootContent(playMenuPane));
        Button testButton = new Button("test");
        testButton.setOnMouseClicked(e -> System.out.println("TEST BUTTON CLICK"));
        Button quitButton = new Button("Quit");
        quitButton.setOnMouseClicked(e -> primaryStage.close());

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
    }

    @Override
    public void createGamePane(){
        gamePane = new BorderPane();
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
