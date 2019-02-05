package controller;

import javafx.stage.Stage;
import model.ModelAPI;
import view.View;

public class Controller {

    private ModelAPI model;
    private View view;
    private Stage primaryStage;

    // Controllers
    private MainMenuController mainMenuController;
    private PlayMenuController playMenuController;
    private GameController gameController;

    public Controller(ModelAPI model, View view, Stage primaryStage){
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;

        mainMenuController = new MainMenuController(model, view)
    }



}
