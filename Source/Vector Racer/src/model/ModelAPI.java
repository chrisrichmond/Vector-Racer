package model;

import changepropagation.Observable;

public interface ModelAPI extends Observable {

    public RacetrackAPI getRacetrack();

    public void setRacetrack(RacetrackAPI racetrack);




}
