package controller;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ModelAPI;
import model.PlayerAPI;
import utilities.VectorConstants;
import view.ViewAPI;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a handler for game events.
 */
public class GameHandler implements Handler {

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
     * The index governing which PlayerAPI's information to display in the credits reel at the end of the game.
     */
    private int playerInfoIndex;

    /**
     * The credits reel to cycle through PlayerAPI info at the end of the game
     */
    private List<PlayerAPI> credits;

    /**
     * Whether or not the game has been overridden as being over.
     */
    private boolean gameOverOverride;

    /**
     * Creates a new instance of GameHandler.
     * @param model the ModelAPI to associate with this GameHandler
     * @param view the ViewAPI to associate with this GameHandler
     * @param primaryStage the main Stage on which UI data is being displayed and received
     */
    public GameHandler(ModelAPI model, ViewAPI view, Stage primaryStage){
        this.model = model;
        this.view = view;
        this.primaryStage = primaryStage;
        this.playerInfoIndex = 0;

        model.attach(this);
    }

    @Override
    public void handle(Event event) {
        if((!gameOverOverride) && (!model.getCurrentState().isGameOver())) {
            // only handle mouse events if current player is NOT an AI
            if (!(model.getCurrentState().getCurrentPlayer().isAI())) {
                double x = ((MouseEvent) event).getX();
                double y = ((MouseEvent) event).getY();
                if (event instanceof MouseEvent) {
                    double row = screenToModelCoord(y);
                    double col = screenToModelCoord(x);

                    model.gridPointInput(row, col);
                }
            }
        }else{
            if(credits == null){
                credits = new ArrayList<>(model.getCurrentState().getPlayers());
            }
            endGame();
        }
        if(model.isPlayer2Ai()){
            for(PlayerAPI player: model.getCurrentState().getPlayers()){
                if((player.isFinished())){
                    view.getInfoLabel().setText("GAME OVER - " + player.getName() + " wins!");
                    view.getInfoLabel().setTextFill(player.getColor());
                    gameOverOverride = true;
                }
            }
        }
    }

    /**
     * Converts and returns screen coordinates to their equivalent model positions .
     * @param coord the screen coordinate
     * @return the model coordinate
     */
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

        if(model.getCurrentState().isGameOver()){
            if(model.getWinner() != null) {
                view.getInfoLabel().setText("GAME OVER - " + model.getWinner().getName() + " wins!");
                view.getInfoLabel().setTextFill(model.getWinner().getColor());
            }else{
                view.getInfoLabel().setText("GAME OVER - it was a draw");
                view.getInfoLabel().setTextFill(Color.BLACK);
            }
        }else {
            String message;
            if(model.getCurrentState().getCurrentPlayer().getRacer().getCurrentZone() == model.getRacetrack().getFinalZone()){
                view.getInfoLabel().setText(model.getCurrentState().getCurrentPlayer().getName() + " to move - next checkpoint is the finish line!");
            }else{
                view.getInfoLabel().setText(model.getCurrentState().getCurrentPlayer().getName() + " to move - next checkpoint is "+(model.getCurrentState().getCurrentPlayer().getRacer().getCurrentZone()+1));
            }
            view.getInfoLabel().setTextFill(model.getCurrentState().getCurrentPlayer().getColor());
        }
    }

    /**
     * Cycles through player stats at the end of the game.
     */
    public void endGame(){
        PlayerAPI player = credits.get(playerInfoIndex);
        String message = "";
        if ((model.getWinner() != null) && (model.getWinner().equals(player))) {
            message = "WINNNER: ";
        }
        view.getInfoLabel().setText(message + player.getName() + ", Score: " + player.getRacer().getScore() + ", Crashes: " + player.getRacer().getCrashCount());
        view.getInfoLabel().setTextFill(player.getColor());
        if(playerInfoIndex == 0){
            playerInfoIndex = 1;
        }else if(playerInfoIndex == 1){
            playerInfoIndex = 0;
        }
    }
}
