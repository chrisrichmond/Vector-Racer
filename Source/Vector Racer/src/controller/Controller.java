package controller;

import javafx.stage.Stage;
import model.ModelAPI;
import view.ViewAPI;

public class Controller {

    private ModelAPI model;
    private ViewAPI view;
    private Stage primaryStage;

    // Controllers
    private GameController gameController;

    public Controller(ModelAPI model, ViewAPI view, Stage primaryStage){
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;

        gameController = new GameController(model, view, primaryStage);

    }



}
