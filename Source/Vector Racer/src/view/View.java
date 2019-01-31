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

    // External Model and Controller Declarations
    private ModelAPI model;
    private ScreenManager screenManager;
    private Controller mainMenuController;
    private Controller playMenuController;
    private Controller gameController;

    // Internal View Declarations
    private FXMLLoader mainMenuLoader;
    private FXMLLoader playMenuLoader;
    private GamePane gamePane;
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

    public void setGameGridSize(int rows, int cols){

//        ((GameController)gameController).setGridSize();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public ScreenManager getScreenManager(){
        return screenManager;
    }

}