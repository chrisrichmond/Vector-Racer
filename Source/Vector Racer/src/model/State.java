package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class State {

    private Player currentPlayer; // the player whose turn it is in this State
    private Queue<Player> players; // all players currently existing in this State
    private RacetrackAPI racetrack; // the racetrack existing in this State
    private int stateNumber;

    public State(Queue<Player> players, RacetrackAPI racetrack, int stateNumber){
        this.currentPlayer = players.peek();
        this.players = players;
        this.racetrack = racetrack;
        this.stateNumber = stateNumber;
    }

    public State makeMove(Move move){
        if(isMoveLegal(move)){
            // return new State with currentPlayer changed to the next in the list

            currentPlayer.racer.setPosition(move.destination);
//            currentPlayer.racer.moveWhilstApplyingEffects(racetrack, move.destination);
            players.poll();
            players.add(currentPlayer);
            return new State(players, racetrack, stateNumber+1);
        }else{
            System.out.println("Illegal move!");
            return this;
        }

    }

    public boolean isMoveLegal(Move move){
        // compare position/vector of Player in next state against a list of the 9 next legal positions based on the current position of the Player in the current State

        boolean legal = true;

        if(!(move.playerToMove.getPossibleNextPoints().contains(move.destination))){
            // the offered destination is not one of the Player to be moved's next valid positions
            System.out.println("the offered destination is not one of the Player to be moved's next valid positions");
            legal = false;
        }else if( (move.destination.getX() < 0) || (move.destination.getX() >= racetrack.getCols()) || (move.destination.getY() < 0) || (move.destination.getY() >= racetrack.getRows()) ){
            // the offered destination is outwith the bounds of the racetrack
            System.out.println("the offered destination is outwith the bounds of the racetrack");
            legal = false;
        }

        return legal;

//        Queue<Player> tempPlayers = new LinkedList<>(players);
//        tempPlayers.poll();
//        tempPlayers.add(currentPlayer);
//        State nextStateAttempt = new State(tempPlayers, racetrack, stateNumber+1);
//
//        for(State state: getNextLegalStates()){
//            if(nextStateAttempt.equals(state)){
//                return true;
//            }
//        }
//
//        return false;
    }

    private List<State> getNextLegalStates(){
        List<State> nextLegalStates = new ArrayList<>();
        List<Point> possibleNextPoints = currentPlayer.getPossibleNextPoints();

        for(Point possiblePoint: possibleNextPoints){
            Move possibleMove = new Move(currentPlayer, possiblePoint);
            nextLegalStates.add(this.makeMove(possibleMove));   // todo could lead to duplicate states being added, which doesn't present an immediate issue but could be inefficient so maybe consider some kind of redesign to remove dupes
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

    public Queue<Player> getPlayers(){
        return players;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }
}
