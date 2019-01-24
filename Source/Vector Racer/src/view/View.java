package view;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import model.ModelAPI;

import java.net.URL;
import java.util.ResourceBundle;

public class View extends Application {

    private static final String ERROR_FXML = "errorscreen.fxml";
    private static final String MAINMENU_FXML = "mainmenu.fxml";
    private static final String PLAYMENU_FXML = "playmenu.fxml";
    private static final String GAME_FXML = "game.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    private ModelAPI model;
    private Controller screenController;
    private Controller mainMenuController;
    private Controller playMenuController;
    private Controller gameController;
    private FXMLLoader mainMenuLoader;
    private FXMLLoader playMenuLoader;
    private FXMLLoader gameLoader;
    private Parent root;
    private Stage primaryStage;

    public View() {
        model = new Model();
        mainMenuController = new MainMenuController(model, this);
        playMenuController = new PlayMenuController(model, this);
        gameController = new GameController(model);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainMenuLoader = new FXMLLoader(getClass().getResource(MAINMENU_FXML));
        mainMenuLoader.setController(mainMenuController);
        root = mainMenuLoader.load();

        playMenuLoader = new FXMLLoader(getClass().getResource(PLAYMENU_FXML));
        playMenuLoader.setController(playMenuController);

        gameLoader = new FXMLLoader(getClass().getResource(GAME_FXML));
        gameLoader.setController(gameController);

        primaryStage.setTitle("Vector Racer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        this.primaryStage = primaryStage;
        screenController = new FXMLController(primaryStage.getScene());
        ((FXMLController) screenController).add("main menu", mainMenuLoader);
        ((FXMLController) screenController).add("play menu", playMenuLoader);
        ((FXMLController) screenController).add("game", gameLoader);

        // initial setup done, application running and displayed to user after this point

        // the main game screen size is dynamically set dependent on the user's selection of racetrack and so must be dynamically set using JavaFX code rather than in the static (GAME_FXML)fxml file
        // do this in the controller for the main game fxml??
        // OR give the controller reference to this class and have it call a method in here to do the dynamic Scene setup??  (THIS OPTION IS PROBABLY BETTER DUE TO KEEPING THE VISUAL DESIGN OUT OF THE CONTROLLER ITSELF)


    }

    public void display(String screenName) {
        ((FXMLController) screenController).activate(screenName);
    }

    public void setGameGridSize(int rows, int cols){

        ((GameController)gameController).setGridSize();
    }

}