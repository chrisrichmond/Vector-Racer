package model;

import javafx.beans.property.ObjectProperty;
import utilities.VectorFileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Model implements ModelAPI{

    private VectorFileHandler fileHandler;
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

        Queue<HumanPlayer> players = new LinkedList<HumanPlayer>();
        players.add(new HumanPlayer(player1name, new Racer(racetrack.getStartPosition())));
        players.add(new HumanPlayer(player2name, new Racer(racetrack.getStartPosition())));

        //currentState = new State(players, racetrack); // todo ?????


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

}
