package model;

import javafx.beans.property.ObjectProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Model implements ModelAPI{

    private boolean changed;
    private List<State> history;
    private State currentState;
    private RacetrackAPI racetrack;

    public Model(){
        history = new ArrayList<>();
    }

    public void gameSetup(){

    }

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
        // currentRacetrack.clear();
    }

    @Override
    public void createEmptyRacetrack(int rows, int cols) {
        racetrack = new Racetrack(rows, cols);
    }

    @Override
    public void addAirTile(int row, int col) {
        racetrack.addAirTile(new Tile(col, row, true));
    }

    @Override
    public void addSandTile(int row, int col) {
        racetrack.addSandTile(new SandTile(col, row));
    }

    @Override
    public void addIceTile(int row, int col) {
        racetrack.addIceTile(new IceTile(col, row));
    }

}
