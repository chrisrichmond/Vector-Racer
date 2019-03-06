package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ModelAPI;
import utilities.VectorConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class View implements ViewAPI{

    // File Handling
    private FileChooser fileChooser;
    private File vrDir;

    // Model Backend
    private ModelAPI model;

    // Game Controller
    private EventHandler gameController;

    // Primary Stage and Root Content Node
    private Stage primaryStage;
    private Parent root;

    // Racetrack Game Area Pane and Info TextArea
    private RacetrackPane racetrackPane;
    private TextArea infoTextArea;

    // Views (Full Screen Content Panes)
    private GridPane mainMenuPane;
    private GridPane playMenuPane;
    private BorderPane gamePane;

    public View(ModelAPI model, Stage primaryStage){

        // File Handling
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Vector Racer Files (*.vrff)", "*.vrff"));
        vrDir = new File(VectorConstants.VR_PATH);
        fileChooser.setInitialDirectory(vrDir);

        // Model Backend
        this.model = model;
        model.attach(this);

        // Primary Stage
        this.primaryStage = primaryStage;

        // Racetrack Game Area Pane
//        racetrackPane = new RacetrackPane();

        // Views (Full Screen Content Panes)
        createMainMenuPane();
        createPlayMenuPane();

        // Create main scene with main menu as default root content, and show
        primaryStage.setMaximized(true);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(mainMenuPane));
        primaryStage.show();


    }

    @Override
    public void createMainMenuPane(){
        mainMenuPane = new GridPane();

        Button playButton = new Button("Play");
        Button testButton = new Button("test");
        Button quitButton = new Button("Quit");
        playButton.setOnAction(e -> changeRootContent(playMenuPane));
        testButton.setOnAction(e -> System.out.println("TEST BUTTON CLICK"));
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
            fileChooser.setTitle("Choose a Racetrack to load");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            try{
                model.setup(selectedFile, true, "player1", "player2");
                createGamePane();
                changeRootContent(gamePane);
                model.start();
            }catch(FileNotFoundException ex){
                System.out.println("file not found");
                ex.printStackTrace();
            }
        });

        playMenuPane.getChildren().addAll(pvpButton);
    }

    @Override
    public void createGamePane(){
        gamePane = new BorderPane();
        racetrackPane = new RacetrackPane(model.getRacetrack());
        infoTextArea = new TextArea();
        infoTextArea.setText(model.getRacetrack().getStartPosition().toString());
        BorderPane.setAlignment(racetrackPane, Pos.CENTER);
        BorderPane.setAlignment(infoTextArea, Pos.BASELINE_CENTER);

        Reflection reflection = new Reflection();
        reflection.setFraction(0.25);
        //racetrackPane.setEffect(reflection);
        gamePane.getChildren().add(racetrackPane);
        gamePane.getChildren().add(infoTextArea);
        racetrackPane.addEventFilter(MouseEvent.MOUSE_PRESSED, gameController);

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
        primaryStage.getScene().setRoot(pane);
    }

    @Override
    public Pane getRacetrackPane() {
        return racetrackPane;
    }

    @Override
    public void setGameController(EventHandler gameController){
        this.gameController = gameController;
    }

    @Override
    public void update() {
        racetrackPane.drawNextPossiblePositions(model.getCurrentState().getCurrentPlayer());
        racetrackPane.drawRacerSprites((List) model.getCurrentState().getPlayers());
    }
}
