package model;

import javafx.beans.property.ObjectProperty;

import java.io.File;
import java.io.FileNotFoundException;

public interface ModelAPI {

    void setup(File filename, boolean isPvp, String player1name, String player2name) throws FileNotFoundException;

    void start();

    //public ObjectProperty<RacetrackAPI> racetrackProperty();

    RacetrackAPI getRacetrack();

    State getCurrentState();

    void loadFile(File filename) throws FileNotFoundException;

    void createEmptyRacetrack(int rows, int cols);

    void addAirTile(int row, int col);

    void addSandTile(int row, int col);

    void addIceTile(int row, int col);

    void addWallTile(int row, int col);

    void fillRemainderWith(String tileType);

    void gridPointInput(double row, double col);

}
