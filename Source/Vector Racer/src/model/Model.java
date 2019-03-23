package model;

import model.geometry.Point;
import utilities.Observer;
import utilities.VectorConstants;
import utilities.VectorFileHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class representing an instance of the ModelAPI, all backend data and logic, the game itself.
 */
public class Model implements ModelAPI {

    /**
     * The file handler object for loading racetrack data into the Model.
     */
    private VectorFileHandler fileHandler;

    /**
//     * The list of all objects observing this instance of Model for change.
     */
    private List<Observer> observers;

    /**
     * A boolean flag denoting change in this instance of Model.
     */
    private boolean changed;

    /**
     * The Deque representing a history of past States that have been associated with this Model.
     */
    private Deque<State> history;

    /**
     * The current State associated with this Model.
     */
    private State currentState;

    /**
     * The RacetrackAPI associated with this Model.
     */
    private RacetrackAPI racetrack;

    /**
     * The PlayerAPI who has won the game, null if there is currently no winner.
     */
    private PlayerAPI winner;

    /**
     * Whether or not the second player in this Model is an AI.
     */
    private boolean player2ai;

    /**
     * Creates a new instance of model.
     */
    public Model(){
        fileHandler = new VectorFileHandler(this);
        observers = new ArrayList<>();
        history = new LinkedList<>();
        winner = null;
    }

    @Override
    public void setup(File filename, String player1name, String player2name, boolean player2ai) throws FileNotFoundException{
        loadFile(filename);

        Queue<PlayerAPI> players = new LinkedList<PlayerAPI>();
        System.out.println("Adding Player 1: "+player1name+ " "+VectorConstants.P1_COLOR.toString());
        players.add(new Player(player1name, new Racer(racetrack.getStartPosition()), VectorConstants.P1_COLOR, false));

        this.player2ai = player2ai;
        if(player2ai){
            System.out.println("Adding Player 2 (AI): "+player2name+ " "+VectorConstants.P2_COLOR.toString());
            PlayerAPI p2 = new AIPlayer(player2name, new Racer(racetrack.getStartPosition()), VectorConstants.P2_COLOR, VectorConstants.AI_ALGORITHM);
            PlayerAPI p1 = players.poll();
            players.add(p2);
            players.add(p1);
        }else{
            System.out.println("Adding Player 2 (Human): "+player2name+ " "+VectorConstants.P2_COLOR.toString());
            players.add(new Player(player2name, new Racer(racetrack.getStartPosition()), VectorConstants.P2_COLOR, player2ai));
        }

        currentState = new State(players, racetrack, 0, null, null, false);
        for(PlayerAPI currentPlayer: currentState.getPlayers()){
            System.out.println(currentPlayer.getName());
            if(currentPlayer.isAI()){
                ((AIPlayer)currentPlayer).findSolution(currentState);
            }
        }
        PlayerAPI p2 = currentState.getPlayers().poll();
        currentState.getPlayers().add(p2);
    }

    @Override
    public void start(){
        notifyObservers();
    }

    @Override
    public RacetrackAPI getRacetrack() {
        return racetrack;
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public void loadFile(File filename) throws FileNotFoundException {
        fileHandler.loadFromFile(filename);
    }

    @Override
    public void createEmptyRacetrack(int rows, int cols, int startPosRow, int startPosCol, int finalZone) {
        racetrack = new Racetrack(rows, cols, new Point(startPosCol, startPosRow), finalZone);
    }

    @Override
    public void addAirTile(int row, int col) {
        if(racetrack.addAirTile(new AirTile(col, row))){
            System.out.println("Successfully added new AIR tile to model at row "+row+", col "+col);
        }
    }

    @Override
    public void addSandTile(int row, int col) {
        if(racetrack.addSandTile(new SandTile(col, row))){
            System.out.println("Successfully added new SAND tile to model at row "+row+", col "+col);
        }
    }

    @Override
    public void addIceTile(int row, int col) {
        if(racetrack.addIceTile(new IceTile(col, row))){
            System.out.println("Successfully added new ICE tile to model at row "+row+", col "+col);
        }
    }

    @Override
    public void addWallTile(int row, int col) {
        if(racetrack.addWallTile(new WallTile(col, row))){
            System.out.println("Successfully added new WALL tile to model at row "+row+", col "+col);
        }
    }

    @Override
    public void addCheckpointTile(int row, int col, int zoneNumber) {
        if(racetrack.addCheckpointTile(new CheckpointTile(col, row, zoneNumber))){
            System.out.println("Successfully added new CHECKPOINT tile to model at row"+row+", col "+col+" - zone "+zoneNumber);
        }
    }

    @Override
    public void fillRemainderWith(String tileType) {
        for(int row = 0; row < racetrack.getRows(); row++){
            for(int col = 0; col < racetrack.getCols(); col++){
                if(tileType.equals("air")){
                    addAirTile(row, col);
                }else if(tileType.equals("sand")){
                    addSandTile(row, col);
                }else if(tileType.equals("ice")){
                    addIceTile(row, col);
                }else if(tileType.equals("wall")){
                    addWallTile(row, col);
                }
            }
        }
    }

    @Override
    public void gridPointInput(double row, double col) {
        for(Point currentPoint: currentState.getCurrentPlayer().getRacer().getPossibleNextPoints(racetrack, currentState.getCurrentPlayer().isAI())){
            // this could be improved through usage of a structured array rather than arraylist so as to make collection searching more efficient
            double rowLow = currentPoint.getY() - 0.5;
            double rowHigh = currentPoint.getY() + 0.5;
            double colLow = currentPoint.getX() - 0.5;
            double colHigh = currentPoint.getX() + 0.5;

            if( (row >= rowLow) && (row <= rowHigh) && (col >= colLow) && (col <= colHigh) ){

                history.push(currentState);
                currentState = currentState.makeMove(new Move(currentState.getCurrentPlayer(), new Point((int)col, (int)row)));

                try {
                    if (currentState.getCurrentPlayer().isAI()) {
                        history.push(currentState);
                        currentState = currentState.makeMove(((AIPlayer) currentState.getCurrentPlayer()).getMove());
                    }
                }catch (NullPointerException e){
                    System.out.println("AI is finished");
                }

                if(currentState.isGameOver()){
                    System.out.println("GAME OVER ALL PLAYERS HAVE FINISHED");
                    int lowestScore = 100000;
                    for(PlayerAPI player: currentState.getPlayers()){
                        if(player.getRacer().getScore() == lowestScore){
                            winner = null;
                        }else if(player.getRacer().getScore() < lowestScore){
                            winner = player;
                            lowestScore = player.getRacer().getScore();
                        }
                    }
                }
                notifyObservers();
                break;
            }
        }
    }

    @Override
    public void setStartPosition(int row, int col) {
        racetrack.setStartPosition(new Point(col, row));
    }

    @Override
    public PlayerAPI getWinner() {
        return winner;
    }

    @Override
    public boolean isPlayer2Ai() {
        return player2ai;
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void setChanged() {
        changed = true;
    }

    @Override
    public void notifyObservers() {
        for(Observer o: observers){
            o.update();
        }
        changed = false;
    }
}
