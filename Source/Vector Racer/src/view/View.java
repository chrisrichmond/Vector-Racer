package view;

import controller.Controller;
import controller.GameController;
import controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import model.ModelAPI;

public class View extends Application {

    private static final String MAINMENU_FXML = "mainmenu.fxml";
    private static final String GAME_FXML = "game.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    private ModelAPI model;
    private Controller mainMenuController;
    private Controller gameController;
    private FXMLLoader loader;
    private Parent root;

    public View(){
        model = new Model();
        mainMenuController = new MainMenuController(model, this);
        gameController = new GameController(model);   // ???? multiple controllers need to be used
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        loader = new FXMLLoader(getClass().getResource(MAINMENU_FXML));
        loader.setController(mainMenuController);
        root = loader.load();

        primaryStage.setTitle("Vector Racer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        System.out.println("test test blah");

        // initial setup done, application running and displayed to user after this point

        // the main game screen size is dynamically set dependent on the user's selection of racetrack and so must be dynamically set using JavaFX code rather than in the static (GAME_FXML)fxml file
        // do this in the controller for the main game fxml??
        // OR give the controller reference to this class and have it call a method in here to do the dynamic Scene setup??  (THIS OPTION IS PROBABLY BETTER DUE TO KEEPING THE VISUAL DESIGN OUT OF THE CONTROLLER ITSELF)



    }

    public void gotoPlayScreen(){
        loader = new FXMLLoader(getClass().getResource(MAINMENU_FXML));
        loader.setController(mainMenuController);
        root = loader.load();
    }

}