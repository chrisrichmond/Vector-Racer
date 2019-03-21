package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXRippler;
import controller.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ModelAPI;
import utilities.VectorConstants;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Paths;

public class View implements ViewAPI{

    // File Handling
    private FileChooser fileChooser;
    private File vrDir;

    // Model Backend
    private ModelAPI model;

    // Content Handlers
    private EventHandler gameHandler;

    // Primary Stage and Root Content Node
    private Stage primaryStage;
    private Parent root;

    // Racetrack Game Area Pane and Info TextArea
    private RacetrackPane racetrackPane;
    private TextArea infoTextArea;

    // Views (Full Screen Content Panes)
    private AnchorPane mainMenuPane;
    private BorderPane gamePane;

    private Image mainMenuSplash;
    private ImageView backgroundImageView;


    public View(ModelAPI model, Stage primaryStage) throws Exception{

        // File Handling
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Vector Racer Files (*"+VectorConstants.VR_FILE_EXTENSION+")", ("*"+VectorConstants.VR_FILE_EXTENSION)));
        vrDir = new File(VectorConstants.VR_PATH);
        fileChooser.setInitialDirectory(vrDir);

        // Model Backend
        this.model = model;

        // Primary Stage
        this.primaryStage = primaryStage;

        // Views (Full Screen Content Panes)
        createMainMenuPane();

        // Create main scene with main menu as default root content, and show
        primaryStage.setMaximized(false);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);

        Scene scene = new Scene(mainMenuPane);
        scene.getStylesheets().add(VectorConstants.STYLESHEET);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void createMainMenuPane(){

        mainMenuPane = new AnchorPane();

        mainMenuSplash = new Image(VectorConstants.MAINMENU_SPLASH);
        backgroundImageView = new ImageView(mainMenuSplash);
        backgroundImageView.setFitWidth(600.0);
        backgroundImageView.setFitHeight(400.0);
        backgroundImageView.setPickOnBounds(true);
        backgroundImageView.setPreserveRatio(true);

        mainMenuPane.getChildren().addAll(backgroundImageView);
        mainMenuPane.setStyle("-fx-padding: -5");
        showFirstMenu();
    }

    private void showFirstMenu(){
        VBox firstVbox = new VBox(15);
        firstVbox.setPrefSize(178.0, 200.0);

        JFXButton playButton = new JFXButton();
        playButton.setPrefSize(171, 37);
        playButton.setText("PLAY");
        playButton.setOnAction(e -> {
            mainMenuPane.getChildren().clear();
            mainMenuPane.getChildren().add(backgroundImageView);
            showSecondMenu();
        });

        JFXButton quitButton = new JFXButton();
        quitButton.setPrefSize(171, 37);
        quitButton.setText("QUIT");
        quitButton.setOnAction(e -> primaryStage.close());

        firstVbox.getChildren().addAll(playButton, quitButton);

        mainMenuPane.getChildren().add(firstVbox);
    }

    private void showSecondMenu(){
        VBox secondVbox = new VBox(15);
        secondVbox.setPrefSize(300, 200.0);

        JFXButton pvplayerButton = new JFXButton();
        pvplayerButton.setPrefSize(250, 37);
        pvplayerButton.setText("PLAYER vs PLAYER");
        pvplayerButton.setFont(VectorConstants.MENU_FONT);
        pvplayerButton.setOnAction(e -> {
            fileChooser.setTitle("Choose a Racetrack to load");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if(selectedFile != null) {
                try {
                    model.setup(selectedFile, "Player 1", "Player 2", false);
                    createGamePane();
                    changeRootContent(gamePane);
                    model.start();
                } catch (Exception ex) {
                    System.out.println("file not found");
                    ex.printStackTrace();
                }
            }
        });

        JFXButton pvcomputerButton = new JFXButton();
        pvcomputerButton.setPrefSize(250, 37);
        pvcomputerButton.setText("PLAYER vs COMPUTER");
        pvcomputerButton.setFont(VectorConstants.MENU_FONT);
        pvcomputerButton.setOnAction(e -> {
            fileChooser.setTitle("Choose a Racetrack to load");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if(selectedFile != null) {
                try {
                    model.setup(selectedFile, "Player 1", "Player 2", true);
                    createGamePane();
                    changeRootContent(gamePane);
                    model.start();
                } catch (Exception ex) {
                    System.out.println("file not found");
                    ex.printStackTrace();
                }
            }
        });

        JFXButton backButton = new JFXButton();
        backButton.setPrefSize(171, 37);
        backButton.setText("BACK");
        backButton.setFont(new Font("Consolas Bold Italic", 20.0));
        backButton.setOnAction(e -> {
            mainMenuPane.getChildren().clear();
            mainMenuPane.getChildren().add(backgroundImageView);
            showFirstMenu();
        });

        secondVbox.getChildren().addAll(pvplayerButton, pvcomputerButton, backButton);

        mainMenuPane.getChildren().add(secondVbox);
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
        racetrackPane.setEffect(reflection);
        gamePane.getChildren().add(racetrackPane);
        gamePane.getChildren().add(infoTextArea);
        racetrackPane.addEventFilter(MouseEvent.MOUSE_PRESSED, gameHandler);

    }

    @Override
    public AnchorPane getMainMenuPane(){ // fixme
        return mainMenuPane;
    }

    @Override
    public BorderPane getGamePane() {
        return gamePane;
    }

    @Override
    public void changeRootContent(Pane pane) {
        primaryStage.hide();
        primaryStage.getScene().setRoot(pane);
        primaryStage.show();
    }

    @Override
    public RacetrackPaneAPI getRacetrackPane() {
        return racetrackPane;
    }


    @Override
    public void setGameHandler(EventHandler gameHandler){
        this.gameHandler = gameHandler;
    }

}
