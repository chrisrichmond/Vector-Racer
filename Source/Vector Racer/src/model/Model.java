package model;

import javafx.beans.property.ObjectProperty;
import model.geometry.Point;
import utilities.Observer;
import utilities.VectorFileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Model implements ModelAPI{

    private VectorFileHandler fileHandler;
    private List<Observer> observers;
    private boolean changed;
    private Stack<State> history;
    private State currentState;
    private RacetrackAPI racetrack;

    public Model(){
        fileHandler = new VectorFileHandler(this);
        history = new Stack<>();
    }

    @Override
    public void setup(File filename, boolean isPvp, String player1name, String player2name) throws FileNotFoundException{
        loadFile(filename);

        // todo AI diversion here ??

        Queue<Player> players = new LinkedList<Player>();
        players.add(new HumanPlayer(player1name, new Racer(racetrack.getStartPosition())));
        players.add(new HumanPlayer(player2name, new Racer(racetrack.getStartPosition())));

        currentState = new State(players, racetrack, 0);
    }

    @Override
    public void start(){
        // TODO -- CALL THIS EXTERNALLY (MAIN CLASS?) AFTER MODEL OBJECT HAS BEEN CREATED AND ALL MVC INTERFACING CONNECTIONS HAVE BEEN MADE


    }

    @Override
    public RacetrackAPI getRacetrack() {
        return racetrack;
    }

    @Override
    public State getCurrentState() {
        return null;
    }

    @Override
    public void loadFile(File filename) throws FileNotFoundException {
        fileHandler.loadFromFile(filename);
        // currentRacetrack.clear();
    }

    @Override
    public void createEmptyRacetrack(int rows, int cols) {
        racetrack = new Racetrack(rows, cols);
    }

    @Override
    public void addAirTile(int row, int col) {
        if(racetrack.addAirTile(new AirTile(col, row))){
            System.out.println("Successfully added new air tile to model at row "+row+", col "+col);
        }

    }

    @Override
    public void addSandTile(int row, int col) {
        if(racetrack.addSandTile(new SandTile(col, row))){
            System.out.println("Successfully added sand air tile to model at row "+row+", col "+col);
        }
    }

    @Override
    public void addIceTile(int row, int col) {
        if(racetrack.addIceTile(new IceTile(col, row))){
            System.out.println("Successfully added new ice tile to model at row "+row+", col "+col);
        }
    }

    @Override
    public void addWallTile(int row, int col) {
        if(racetrack.addWallTile(new WallTile(col, row))){
            System.out.println("Successfully added new wall tile to model at row "+row+", col "+col);
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

        for(Point currentPoint: currentState.getCurrentPlayer().getPossibleNextPoints()){
            double rowLow = currentPoint.getY() - 0.5;
            double rowHigh = currentPoint.getY() + 0.5;
            double colLow = currentPoint.getX() - 0.5;
            double colHigh = currentPoint.getX() + 0.5;

            if( (row >= rowLow) && (row <= rowHigh) && (col >= colLow) && (col <= colHigh) ){
                history.push(currentState);
                currentState = currentState.makeMove(new Move(currentState.getCurrentPlayer(), new Point((int)row, (int)col)));
            }
        }
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
