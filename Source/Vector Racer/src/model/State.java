package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.*;

public class State {

    private PlayerAPI currentPlayer; // the player whose turn it is in this State
    private Queue<PlayerAPI> players; // all players currently existing in this State
    private RacetrackAPI racetrack; // the racetrack existing in this State
    private int stateNumber;
    private boolean gameOver;
    private State parent = null;

    // copy constructor
    public State(State original){
        this.currentPlayer = original.currentPlayer;
        this.players = original.players;
        this.racetrack = original.racetrack;
        this.stateNumber = original.stateNumber;
        this.gameOver = original.gameOver;
        this.parent = this.getParent();
    }

    public State(Queue<PlayerAPI> players, RacetrackAPI racetrack, int stateNumber){
        this.currentPlayer = players.peek();
        this.players = players;
        this.racetrack = racetrack;
        this.stateNumber = stateNumber;
        this.gameOver = false;

        int skipCount = 0;
        while(currentPlayer.isFinished()){
            if(skipCount >= players.size()){
                // ALL PLAYERS HAVE FINISHED
                gameOver = true;
                break;
            }else{
                skipCurrentPlayer();
                skipCount++;
            }
        }

        for(PlayerAPI currentPlayer: players){
            if(racetrack.isTouchingWall(currentPlayer.getRacer())){
                if(!currentPlayer.isFinished()) {
                    currentPlayer.getRacer().getPointRoute().pop();
                    currentPlayer.getRacer().killVelocity();
                }
            }
        }

        if(currentPlayer.isAI()){
            this.makeMove(((AIPlayer)currentPlayer).getMove());
        }
    }

    public State(Queue<PlayerAPI> players, RacetrackAPI racetrack, int stateNumber, State parent){
        this(players, racetrack, stateNumber);
        this.parent = parent;
    }

    /**
     * Called when the current player has already finished the race and so
     * @return
     */
    public void skipCurrentPlayer(){
        players.poll();
        players.add(currentPlayer);
    }

    public State makeMove(Move move){
        if(isMoveLegal(move)){
            // return new State with currentPlayer changed to the next in the list
            currentPlayer.getRacer().moveWhilstApplyingEffects(racetrack, move.destination);
            System.out.println("new velo = row:"+(currentPlayer.getRacer().getVelocity().getYVelo()+" col:"+currentPlayer.getRacer().getVelocity().getXVelo()));
            players.poll();
            players.add(currentPlayer);
            return new State(players, racetrack, stateNumber+1);
        }else{
            System.out.println("Illegal move!");
            return this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return stateNumber == state.stateNumber &&
                isGameOver() == state.isGameOver() &&
                getCurrentPlayer().equals(state.getCurrentPlayer()) &&
                getPlayers().equals(state.getPlayers()) &&
                getRacetrack().equals(state.getRacetrack()) &&
                Objects.equals(getParent(), state.getParent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrentPlayer(), getPlayers(), getRacetrack(), stateNumber, isGameOver(), getParent());
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

    public Queue<PlayerAPI> getPlayers(){
        return players;
    }

    public PlayerAPI getCurrentPlayer(){
        return currentPlayer;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public State getParent(){
        return parent;
    }

    // TODO NEED TO CHECK THIS ALL DUE TO BEING UNSURE IF COPYING KEEPS REFERENCE TO ORIGINAL OBJECT ATTRIBUTES
    public HashMap<Move, State> getChildren(){
        State copiedCurrentState = new State(this);
        HashMap<Move, State> children = new HashMap();
        for(Point currentPossibleNextPoint: this.currentPlayer.getPossibleNextPoints()){
            Move move = new Move(this.currentPlayer, currentPossibleNextPoint);
            children.put(move, copiedCurrentState.makeMove(move));
        }
        return children;
    }
}
