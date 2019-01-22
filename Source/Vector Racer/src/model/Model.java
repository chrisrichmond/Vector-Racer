package model;

import changepropagation.Observer;

import java.util.ArrayList;
import java.util.List;

public class Model implements ModelAPI{

    private List<Observer> observers;
    private boolean changed;
    private List<State> history;
    private State currentState;

    public Model(){
        history = new ArrayList<>();
    }

    public void gameSetup(){
        // this method is called once the main menu has been passed (or possibly from the main menu controller itself rather than after it?) and is used to set various parameters for gameplay
        // may be easier to just call this method from controller and to pass it a parameter for every item to set up a unique game
        // i.e. list of players/player names racetrack, etc.

        // user selects number of racers
        // currentRacetrack = <default track here>    ---- todo user selects track

        // loop for number of players and add human players to racers arraylist
        // add remaining players as AI (do this further down the line)
    }

    public void start(){
        // TODO -- CALL THIS EXTERNALLY (MAIN CLASS?) AFTER MODEL OBJECT HAS BEEN CREATED AND ALL MVC INTERFACING CONNECTIONS HAVE BEEN MADE

        // start game loop
            // new round has begun
            // loop for each player ???? possibly redundant after redesign pertaining to the way States are implemented now?

//            for (Player currentPlayer: players) {
//                // loop until valid move
//                    // attempt to make a move
//            }

            // round ends

    }

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
            changed = false;
        }
    }

    @Override
    public RacetrackAPI getRacetrack() {
        return currentState.getRacetrack();
    }

}
