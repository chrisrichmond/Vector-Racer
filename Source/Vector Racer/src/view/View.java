package view;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import model.ModelAPI;
import utilities.ScreenManager;
import utilities.VectorConstants;

public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /*
     External Model and Controller Declarations
      */
    private ModelAPI model;
    private ScreenManager screenManager;    // used for switching views
    private Controller mainMenuController;  // provides methods for handling main menu user input
    private Controller playMenuController;  // provides methods for handling play menu user input
    private Controller gameController;      // provides methods for handling user input on the main game pane

    /*
     Internal View Declarations
      */
    // FXML Views
    private FXMLLoader mainMenuLoader;      // content loader for the main menu fxml
    private FXMLLoader playMenuLoader;      // content loader for the play menu fxml

    // Pure Java Views
    private GamePane gamePane;              // content container for the main game
    private TileGridPane tileGridPane;      // content container for the tile grid inside the main game pane

    // Stage and Root Content Node
    private Parent root;
    private Stage primaryStage;

    public View() {
        model = new Model();
        mainMenuController = new MainMenuController(model, this);
        playMenuController = new PlayMenuController(model, this);

        gamePane = new GamePane(VectorConstants.GP_PREFWIDTH, VectorConstants.GP_PREFHEIGHT);
        gameController = new GameController(model, gamePane, this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        mainMenuLoader = new FXMLLoader(getClass().getResource(VectorConstants.MAINMENU_FXML));
        mainMenuLoader.setController(mainMenuController);
        root = mainMenuLoader.load();

        playMenuLoader = new FXMLLoader(getClass().getResource(VectorConstants.PLAYMENU_FXML));
        playMenuLoader.setController(playMenuController);

        primaryStage.setTitle("Vector Racer");
        primaryStage.setScene(new Scene(root));

//        primaryStage.setX(PRIMARY_SCREEN_BOUNDS.getMinX());
//        primaryStage.setY(PRIMARY_SCREEN_BOUNDS.getMinY());
//        primaryStage.setWidth(PRIMARY_SCREEN_BOUNDS.getWidth());
//        primaryStage.setHeight(PRIMARY_SCREEN_BOUNDS.getHeight());
        primaryStage.setMaximized(true);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.show();

        screenManager = new ScreenManager(primaryStage.getScene());
        screenManager.add("main menu", mainMenuLoader);
        screenManager.add("play menu", playMenuLoader);
        screenManager.add("game", gamePane);

        // initial setup done, application running and displayed to user after this point

        // the main game screen size is dynamically set dependent on the user's selection of racetrack and so must be dynamically set using JavaFX code rather than in the static (GAME_FXML)fxml file
        // do this in the controller for the main game fxml??
        // OR give the controller reference to this class and have it call a method in here to do the dynamic Scene setup??  (THIS OPTION IS PROBABLY BETTER DUE TO KEEPING THE VISUAL DESIGN OUT OF THE CONTROLLER ITSELF)


    }

    /**
     * Gets reference to the primary stage
     * @return the primary stage
     */
    public Stage getPrimaryStage(){
        return primaryStage;
    }

    /**
     * Gets reference to the screen manager
     * @return the screen manager
     */
    public ScreenManager getScreenManager(){
        return screenManager;
    }

}