package model;

import javafx.beans.property.ObjectProperty;

public interface ModelAPI {

    public void gameSetup();

    public void start();

    //public ObjectProperty<RacetrackAPI> racetrackProperty();

    public RacetrackAPI getRacetrack();

    public State getCurrentState();

    

}
