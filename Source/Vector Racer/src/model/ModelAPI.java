package model;


import model.geometry.Point;
import utilities.Observable;

import java.io.File;
import java.io.FileNotFoundException;

public interface ModelAPI extends Observable {

    void setup(File filename, String player1name, String player2name, boolean player2ai) throws FileNotFoundException;

    void start();

    //public ObjectProperty<RacetrackAPI> racetrackProperty();

    RacetrackAPI getRacetrack();

    State getCurrentState();

    void loadFile(File filename) throws FileNotFoundException;

    void createEmptyRacetrack(int rows, int cols, int startPosRow, int startPosCol, int finalZone);

    void addAirTile(int row, int col);

    void addSandTile(int row, int col);

    void addIceTile(int row, int col);

    void addWallTile(int row, int col);

    void addCheckpointTile(int row, int col, int zoneNumber);

    void fillRemainderWith(String tileType);

    void gridPointInput(double row, double col);

    void setStartPosition(int row, int col);

    PlayerAPI getWinner();

}
