package model;

import changepropagation.Observer;

import java.util.List;

public class Model implements ModelAPI{

    private List<Observer> observers;
    private boolean changed;

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
        if(changed){
            for (Observer o: observers) {
                o.notify();
            }
        }
        changed = false;
    }

    @Override
    public RacetrackAPI getRacetrack() {
        return null;
    }

    @Override
    public void setRacetrack(RacetrackAPI racetrack) {

    }
}
