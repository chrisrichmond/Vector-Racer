package model;

import javafx.beans.property.ObjectProperty;

import java.io.File;
import java.io.FileNotFoundException;

public interface ModelAPI {

    void setup();

    void start();

    //public ObjectProperty<RacetrackAPI> racetrackProperty();

    RacetrackAPI getRacetrack();

    State getCurrentState();

    void loadFile(File filename) throws FileNotFoundException;

    void createEmptyRacetrack(int rows, int cols);

    void addAirTile(int row, int col);

    void addSandTile(int row, int col);

    void addIceTile(int row, int col);

}
