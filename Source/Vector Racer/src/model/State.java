package model;

import model.geometry.Vect;

import java.util.List;

public class State {

    private List<State> nextStates;

    public State(Vect move){
        // nextStates = do logic to check if move is legal
    }

    public List<State> getNextStates(){
        return nextStates;
    }

}
