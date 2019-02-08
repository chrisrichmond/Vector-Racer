package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ModelAPI;
import utilities.VectorConstants;
import view.ViewAPI;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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

        if(event instanceof MouseEvent){
            double row = screenToModelCoord(y);
            double col = screenToModelCoord(x);
            System.out.println("SCREEN");
            System.out.println("row "+y);
            System.out.println("col "+x);
            System.out.println("MODEL");
            System.out.println("row "+row);
            System.out.println("col "+col);
        }
    }

    private double screenToModelCoord(double coord){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        double twoDecimalPlaces = Double.parseDouble(df.format((coord / VectorConstants.TILESIZE)));

        return Math.floor((twoDecimalPlaces +  (double)0.5 / 2) / 0.5) * 0.5;
    }
}
