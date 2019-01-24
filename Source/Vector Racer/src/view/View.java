package view;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Model;
import model.ModelAPI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class View extends Application implements Initializable {

    private static final String ERROR_FXML = "errorscreen.fxml";
    private static final String MAINMENU_FXML = "mainmenu.fxml";
    private static final String PLAYMENU_FXML = "playmenu.fxml";
    private static final String GAME_FXML = "game.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    private ModelAPI model;
    private Scene scene;
    private Controller screenController;
    private Controller mainMenuController;
    private Controller playMenuController;
    private Controller gameController;
    private FXMLLoader mainMenuLoader;
    private FXMLLoader playMenuLoader;
    private Parent root;
    private Stage primaryStage;

    public View(){
        model = new Model();
        mainMenuController = new MainMenuController(model, this);
        playMenuController = new PlayMenuController(model, this);
        gameController = new GameController(model);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainMenuLoader = new FXMLLoader(getClass().getResource(MAINMENU_FXML));
        mainMenuLoader.setController(mainMenuController);
        root = mainMenuLoader.load();

        playMenuLoader = new FXMLLoader(getClass().getResource(PLAYMENU_FXML));
        playMenuLoader.setController(playMenuController);

        primaryStage.setTitle("Vector Racer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        this.primaryStage = primaryStage;

        System.out.println("test test blah");

        // initial setup done, application running and displayed to user after this point

        // the main game screen size is dynamically set dependent on the user's selection of racetrack and so must be dynamically set using JavaFX code rather than in the static (GAME_FXML)fxml file
        // do this in the controller for the main game fxml??
        // OR give the controller reference to this class and have it call a method in here to do the dynamic Scene setup??  (THIS OPTION IS PROBABLY BETTER DUE TO KEEPING THE VISUAL DESIGN OUT OF THE CONTROLLER ITSELF)



    }

    public void display() throws Exception {
        System.out.println("hello from display");

        primaryStage.getScene().setRoot(playMenuLoader.load());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /*
    public void display(String screenKey){
        String fxml = ERROR_FXML;
        Controller controller = mainMenuController;



        loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setController(controller);
        try{
            root = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
/*
        switch(screenKey){
            case("mainmenu"):
                fxml = MAINMENU_FXML;
                controller = mainMenuController;
                break;
            case("playmenu"):
                screenController
                controller = playMenuController;
                break;
            case("game"):
                fxml = GAME_FXML;
                controller = gameController;
                break;
            default:
                System.out.println("invalid parameter given for View.display(String screenKey) method call");
        }

        loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setController(controller);
        try{
            root = loader.load();
            primaryStage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    */

}