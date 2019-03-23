package view;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ModelAPI;
import utilities.VectorConstants;
import java.io.File;

/**
 * Class for representing the ViewAPI, responsible in some way for all user interface components.
 */
public class View implements ViewAPI{

    /**
     * Object for allowing file selection to take place.
     */
    private FileChooser fileChooser;

    /**
     * The default directory when choosing files.
     */
    private File vrDir;

    /**
     * The ModelAPI associated with this View.
     */
    private ModelAPI model;

    /**
     * The object for handling user interaction at game time.
     * Is not set by the view itself, should be dictated externally.
     */
    private EventHandler gameHandler;

    /**
     * The main Stage window on which it holds a Scene with display content.
     */
    private Stage primaryStage;

    /**
     * The RacetrackPane used to display racetrack data on screen.
     */
    private RacetrackPane racetrackPane;

    /**
     * The Label used to display information to the user during gameplay.
     */
    private Label infoLabel;

    /**
     * The main menu screen content.
     */
    private AnchorPane mainMenuPane;

    /**
     * The game screen content.
     */
    private AnchorPane gamePane;

    /**
     * The main menu splash image.
     */
    private Image mainMenuSplash;

    /**
     * The main menu splash image container.
     */
    private ImageView backgroundImageView;

    /**
     * Creates a new instance of View
     * @param model the ModelAPI to associate with this View
     * @param primaryStage the main Stage
     */
    public View(ModelAPI model, Stage primaryStage) {

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

    /**
     * Displays the first set of options on the main menu.
     */
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

    /**
     * Displays the second set of options on the main menu.
     */
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
        gamePane = new AnchorPane();
        racetrackPane = new RacetrackPane(model.getRacetrack());
        Reflection reflection = new Reflection();
        reflection.setFraction(0.25);
//        racetrackPane.setEffect(reflection);

        VBox vbox  = new VBox();
        infoLabel = new Label("You are now playing on '"+model.getRacetrack().getName()+"'");
//        infoLabel.setEffect(reflection);

        vbox.getChildren().addAll(racetrackPane, infoLabel);
        gamePane.getChildren().add(vbox);
        vbox.setAlignment(Pos.CENTER);

        gamePane.setPrefWidth(model.getRacetrack().getCols()*VectorConstants.TILESIZE);
        racetrackPane.addEventFilter(MouseEvent.MOUSE_PRESSED, gameHandler);

    }

    @Override
    public AnchorPane getMainMenuPane(){ // fixme
        return mainMenuPane;
    }

    @Override
    public AnchorPane getGamePane() {
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

    @Override
    public Label getInfoLabel() {
        return infoLabel;
    }
}
