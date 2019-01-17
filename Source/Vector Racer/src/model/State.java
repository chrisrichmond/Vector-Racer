package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class State {

    private Player currentPlayer; // the player whose turn it is in this State
    private Queue<Player> players; // all players currently existing in this State
    private RacetrackAPI racetrack; // the racetrack existing in this State
    private List<State> nextStates;

    public State(Queue<Player> players, RacetrackAPI racetrack){
        this.currentPlayer = players.peek();
        this.players = players;
        this.racetrack = racetrack;

    }

    public State makeMove(Move move){

        if(isMoveLegal(move)){
            // return new State with currentPlayer changed to the next in the list
            players.poll();
            players.add(currentPlayer);
            return new State(players, racetrack);
        }else{
            System.out.println("Illegal move!");
            return this;
        }

    }

    public boolean isMoveLegal(Move move){

        // compare position/vector of Player in next state against a list of the 9 next legal positions based on the current position of the Player in the current State

    }

    private List<State> getNextLegalStates(){
        List<State> nextLegalStates = new ArrayList<>();
        List<Point> possibleNextPoints = currentPlayer.getPossibleNextPoints();

        for(Point possiblePoint: possibleNextPoints){
            Move possibleMove = new Move(currentPlayer, possiblePoint);
            nextLegalStates.add(this.makeMove(possibleMove));
        }

        return nextLegalStates;
    }

    /*
    public List<State> getNextStates(){
        return nextStates;
    }
*/

    public RacetrackAPI getRacetrack() {
        return racetrack;
    }
}
