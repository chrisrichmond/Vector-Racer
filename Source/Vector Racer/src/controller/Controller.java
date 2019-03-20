package controller;

import javafx.stage.Stage;
import model.ModelAPI;
import utilities.Observer;
import view.ViewAPI;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Observer {

    private ModelAPI model;
    private ViewAPI view;
    private Stage primaryStage;
    // Active Content Handler Pointer
    private Handler activeHandler;

    // Controllers
    private GameHandler gameHandler;

    public Controller(ModelAPI model, ViewAPI view, Stage primaryStage){
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;
        model.attach(this);

        gameHandler = new GameHandler(model, view, primaryStage);

        /*
            Main Menu starts as current handler attach/detach handlers as required
            either in this Controller object or inside Handler objects themselves
         */
        view.setGameHandler(gameHandler);
    }

    @Override
    public void update() {

    }

}
