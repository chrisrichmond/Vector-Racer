package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ModelAPI;
import view.ViewAPI;

public class GameController implements EventHandler {

    private ModelAPI model;
    private ViewAPI view;
    private Stage primaryStage;

    public GameController(ModelAPI model, ViewAPI view, Stage primaryStage){
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;

        view.setGameController(this);
    }

    @Override
    public void handle(Event event) {
        double x = ((MouseEvent)event).getX();
        double y = ((MouseEvent)event).getY();
        System.out.println(x);
        System.out.println(y);
    }
}
