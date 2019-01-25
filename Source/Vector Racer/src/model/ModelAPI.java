package model;

import changepropagation.Observable;
import javafx.beans.property.ObjectProperty;

public interface ModelAPI extends Observable {

    public void gameSetup();

    public void start();

    //public ObjectProperty<RacetrackAPI> racetrackProperty();

    public RacetrackAPI getRacetrack();

}
