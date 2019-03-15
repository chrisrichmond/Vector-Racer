package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ModelAPI;
import utilities.Observer;
import utilities.VectorConstants;
import view.ViewAPI;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameHandler implements Handler {

    private ModelAPI model;
    private ViewAPI view;
    private Stage primaryStage;

    public GameHandler(ModelAPI model, ViewAPI view, Stage primaryStage){
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;

        model.attach(this);
    }

    @Override
    public void handle(Event event) {
        // only handle mouse events if current player is NOT an AI
        if(!(model.getCurrentState().getCurrentPlayer().isAI())) {
            double x = ((MouseEvent) event).getX();
            double y = ((MouseEvent) event).getY();

            if (event instanceof MouseEvent) {
                double row = screenToModelCoord(y);
                double col = screenToModelCoord(x);

                model.gridPointInput(row, col);
            }
        }
    }

    private double screenToModelCoord(double coord){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        double twoDecimalPlaces = Double.parseDouble(df.format((coord / VectorConstants.TILESIZE)));

        return Math.floor((twoDecimalPlaces +  (double)0.5 / 2) / 0.5) * 0.5;
    }

    @Override
    public void update() {
        view.getRacetrackPane().drawNextPossiblePositions(model.getCurrentState().getCurrentPlayer());
        view.getRacetrackPane().drawRacerSprites((List) model.getCurrentState().getPlayers());
    }
}
