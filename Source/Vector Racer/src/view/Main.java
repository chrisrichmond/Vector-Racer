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

public class Main extends Application {

    static final String MAINMENU_FXML = "mainmenu.fxml";
    static final String GAME_FXML = "game.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        ModelAPI model = new Model();
        Controller mainMenuController = new MainMenuController(model);
        Controller gameController = new GameController(model);   // ???? multiple controllers need to be used

        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAINMENU_FXML));
        loader.setController(mainMenuController);
        Parent root = loader.load();

        primaryStage.setTitle("Vector Racer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}