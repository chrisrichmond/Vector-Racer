package controller;

import javafx.stage.Stage;
import model.ModelAPI;
import utilities.Observer;
import view.ViewAPI;

public class Controller implements Observer {

    private ModelAPI model;
    private ViewAPI view;
    private Stage primaryStage;

    // Controllers
    private GameController gameController;

    public Controller(ModelAPI model, ViewAPI view, Stage primaryStage){
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;

        model.attach(this);

        gameController = new GameController(model, view, primaryStage);

    }


    @Override
    public void update() {

    }
}
