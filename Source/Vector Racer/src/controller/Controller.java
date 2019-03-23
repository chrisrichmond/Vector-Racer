package controller;

import javafx.stage.Stage;
import model.ModelAPI;
import utilities.Observer;
import view.ViewAPI;

/**
 * Class representing the main controller object from which user input events on the view are handled.
 */
public class Controller implements Observer {

    /**
     * The ModelAPI associated with this GameHandler.
     */
    private ModelAPI model;

    /**
     * The ViewAPI associated with this GameHandler.
     */
    private ViewAPI view;

    /**
     * The Stage associated with this GameHandler.
     */
    private Stage primaryStage;

    /**
     * The Handler for dealing with game specific input events.
     */
    private GameHandler gameHandler;

    /**
     * Creates a new instance of Controller.
     * @param model the ModelAPI to associate with this GameHandler
     * @param view the ViewAPI to associate with this GameHandler
     * @param primaryStage the main Stage on which UI data is being displayed and received
     */
    public Controller(ModelAPI model, ViewAPI view, Stage primaryStage){
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;
        model.attach(this);

        gameHandler = new GameHandler(model, view, primaryStage);
        view.setGameHandler(gameHandler);
    }

    @Override
    public void update() {

    }

}
