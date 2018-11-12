package model;

import changepropagation.Observer;

import java.util.ArrayList;
import java.util.List;

public class Model implements ModelAPI{

    private List<Observer> observers;
    private boolean changed;
    private RacetrackAPI currentRacetrack;
    private List<Player> players;
    private List<State> states;
    private State currentState;



    public Model(){
        players = new ArrayList<>();
        states = new ArrayList<>();


        gameSetup();
    }

    public void gameSetup(){
        // user selects number of racers
        // currentRacetrack = <default track here>    ---- todo user selects track

        // loop for number of players and add human players to racers arraylist
        // add remaining players as AI (do this further down the line)
    }

    public void start(){
        // TODO -- CALL THIS EXTERNALLY (MAIN CLASS?) AFTER MODEL OBJECT HAS BEEN CREATED AND ALL MVC INTERFACING CONNECTIONS HAVE BEEN MADE

        // start game loop
            // new round has begun
            // loop for each player
            for (Player currentPlayer: players) {
                // loop until valid move
                    // attempt to make a move
            }
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
        return currentRacetrack;
    }

    @Override
    public void setRacetrack(RacetrackAPI racetrack) {
        currentRacetrack = racetrack;
    }
}
