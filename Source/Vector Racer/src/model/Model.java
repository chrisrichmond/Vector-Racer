package model;

import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import model.geometry.Point;
import utilities.Observer;
import utilities.VectorConstants;
import utilities.VectorFileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Model implements ModelAPI {

    private VectorFileHandler fileHandler;
    private List<Observer> observers;
    private boolean changed;
    private Deque<State> history;
    private State currentState;
    private RacetrackAPI racetrack;

    public Model(){
        fileHandler = new VectorFileHandler(this);
        observers = new ArrayList<>();
        history = new ArrayDeque<>();
    }

    @Override
    public void setup(File filename, boolean isPvp, String player1name, String player2name, boolean player2ai) throws FileNotFoundException{
        loadFile(filename);

        Queue<PlayerAPI> players = new LinkedList<PlayerAPI>();
        players.add(new Player(player1name, new Racer(racetrack.getStartPosition()), Color.BLUE, false));
        if(player2ai){
            PlayerAPI p2 = new AIPlayer(player2name, new Racer(racetrack.getStartPosition()), Color.RED, VectorConstants.AI_ALGORITHM);
            PlayerAPI p1 = players.poll();
            players.add(p2);
            players.add(p1);
        }else{
            players.add(new Player(player2name, new Racer(racetrack.getStartPosition()), Color.RED, player2ai));
        }

        currentState = new State(players, racetrack, 0, null, null, false);
        for(PlayerAPI currentPlayer: players){
            if(currentPlayer.isAI()){

                ((AIPlayer)currentPlayer).findSolution(currentState);
            }
        }

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
        // currentRacetrack.clear();
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
        for(Point currentPoint: currentState.getCurrentPlayer().getRacer().getPossibleNextPoints(racetrack)){
            // this could be improved through usage of a structured array rather than arraylist so as to make collection searching more efficient
            double rowLow = currentPoint.getY() - 0.5;
            double rowHigh = currentPoint.getY() + 0.5;
            double colLow = currentPoint.getX() - 0.5;
            double colHigh = currentPoint.getX() + 0.5;

            if( (row >= rowLow) && (row <= rowHigh) && (col >= colLow) && (col <= colHigh) ){

                history.push(currentState); // todo incorporate mechanism for this on AI side too
                currentState = currentState.makeMove(new Move(currentState.getCurrentPlayer(), new Point((int)col, (int)row)));
                if(currentState.isGameOver()){
                    System.out.println("GAME OVER ALL PLAYERS HAVE FINISHED");
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
