package model;

import changepropagation.Observable;

public interface ModelAPI extends Observable {

    public void gameSetup();

    public void start();

    public RacetrackAPI getRacetrack();

    public void setRacetrack(RacetrackAPI racetrack);

}
