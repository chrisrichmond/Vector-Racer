package model;

import model.geometry.Point;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Class which represents a game state in which the current game players, racetrack, and AI mode are held.
 * State can hold reference to either zero or one parent States, and can also hold reference to zero to many child States.
 * State space can be constructed and explored using parent/child relationships.
 */
public class State {

    /**
     * The Queue representing all the Players in this State, front of the Queue is the current Player.
     */
    private Queue<PlayerAPI> players;

    /**
     * The RacetrackAPI associated with this State.
     */
    private RacetrackAPI racetrack;

    /**
     * The number of this State.
     */
    private int stateNumber;

    /**
     * Whether the game is over or not in this State.
     */
    private boolean gameOver;

    /**
     * The parent State of this State, null by default unless assigned.
     */
    private State parent = null;

    /**
     * The Move that was made in order to generate this State, null by default unless assigned.
     */
    private Move delta = null;

    /**
     * Whether or not this State is currently participating in an AI solver State graph
     */
    private boolean aiSolverMode = false;

    /**
     * Copy constructor - creates a new instance of State.
     * @param original the State to deep copy
     */
    public State(State original){
        this.players = new LinkedList<>();
        for(PlayerAPI p: original.getPlayers()){
            if(p.isAI()) {
                this.players.add(new AIPlayer((AIPlayer) p));
            }else{
                this.players.add(new Player(p));
            }
        }
        this.racetrack = original.getRacetrack();
        this.stateNumber = original.getStateNumber();
        this.gameOver = original.isGameOver();
        this.parent = original.parent;
        if(original.getDelta() != null)
            this.delta = new Move(original.getDelta());
        this.aiSolverMode = original.isAiSolverMode();
    }

    /**
     * Creates a new instance of State, checks if the game is over, and moves any players in non-traversable Terrain back to a previous Point
     * @param players the Queue of Players in this State with the current Player at the front
     * @param racetrack the RacetrackAPI associated with this State
     * @param stateNumber the number of this State
     * @param parent the parent State of this State, may be null
     * @param delta the Move that was made to enter this State, may be null
     * @param aiSolverMode whether or not this State is in an AI solver State graph
     */
    public State(Queue<PlayerAPI> players, RacetrackAPI racetrack, int stateNumber, State parent, Move delta, boolean aiSolverMode){
        this.players = players;
        this.racetrack = racetrack;
        this.stateNumber = stateNumber;
        this.gameOver = false;
        this.parent = parent;
        this.delta = delta;
        this.aiSolverMode = aiSolverMode;

        int skipCount = 0;
        if(!isAiSolverMode()) {
            while (getCurrentPlayer().isFinished()) {
                if (skipCount >= players.size()) {
                    // ALL PLAYERS HAVE FINISHED
                    gameOver = true;
                    break;
                } else {
                    skipCurrentPlayer();
                    skipCount++;
                }
            }
        }

        for(PlayerAPI player: players){
            if(racetrack.isTouchingWall(player.getRacer())){
                if(!player.isFinished()) {
//                    System.out.println("IN WALL, POPPING");
                    player.getRacer().getPointRoute().pop();
                    player.getRacer().killVelocity();
                }
            }
        }
    }

    /**
     * Returns the current Player in this State.
     * @return the Player at the front of the players Queue
     */
    public PlayerAPI getCurrentPlayer(){
        return players.peek();
    }

    /**
     * Sets whether this State is flagged as solving for an AI algorithm or not.
     * @param aiSolverMode true to set mode, false to set off
     */
    public void setAiSolverMode(boolean aiSolverMode){
        this.aiSolverMode = aiSolverMode;
    }

    /**
     * Returns whether or not this State is flagged as solving for an AI algorithm or not.
     * @return true if in solver mode, false if not
     */
    public boolean isAiSolverMode(){
        return aiSolverMode;
    }

    /**
     * Called when the current player has already finished the race and so the turn is passed to the following player.
     */
    public void skipCurrentPlayer(){
        PlayerAPI previousPlayer = players.poll();
        players.add(previousPlayer);
    }

    /**
     * Uses this State in conjunction with a specified Move to attempt to advance to another State if the Move is legal.
     * Will attempt to move the current player.
     * @param move details of the Player to move and the destination Point to move to
     * @return a new State in which it is the following player in the Queue's turn and players' attributes may have changed
     */
    public State makeMove(Move move){
        if(isMoveLegal(move)){

            Queue<PlayerAPI> playersClone = new LinkedList<>();
            for (PlayerAPI p : players) {
                if (p.isAI()) {
                    playersClone.add(new AIPlayer((AIPlayer) p));
                } else {
                    playersClone.add(new Player(p));
                }
            }

            playersClone.peek().getRacer().moveWhilstApplyingEffects(racetrack, move.getDestination());

            // only switch players if not in AI solver mode
            if(!aiSolverMode){
                PlayerAPI currentClonePlayer = playersClone.poll();
                playersClone.add(currentClonePlayer);
            }

            if(playersClone.peek().isFinished()){
                System.out.println("PLAYER IS FINISHED IN MAKEMOVE");
            }

            return new State(playersClone, racetrack, stateNumber+1, this, move, aiSolverMode);
        }else{
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return getStateNumber() == state.getStateNumber() &&
                isGameOver() == state.isGameOver() &&
                isAiSolverMode() == state.isAiSolverMode() &&
                getCurrentPlayer().equals(state.getCurrentPlayer()) &&
                getPlayers().equals(state.getPlayers()) &&
                getRacetrack().equals(state.getRacetrack()) &&
                Objects.equals(getParent(), state.getParent()) &&
                Objects.equals(getDelta(), state.getDelta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrentPlayer(), getPlayers(), getRacetrack(), getStateNumber(), isGameOver(), getParent(), getDelta(), isAiSolverMode());
    }

    /**
     * Returns whether or not a move is legal or not in this State.
     * @param move the Move to test for legality
     * @return true if legal, false if not
     */
    public boolean isMoveLegal(Move move){

        boolean legal = true;
        if(!(move.getPlayerToMove().getRacer().getPossibleNextPoints(racetrack, move.getPlayerToMove().isAI()).contains(move.getDestination()))){
            legal = false;
        }else if( (move.getDestination().getX() < 0) || (move.getDestination().getX() >= racetrack.getCols()) || (move.getDestination().getY() < 0) || (move.getDestination().getY() >= racetrack.getRows()) ){
            legal = false;
        }

        return legal;
    }

    /**
     * Returns a Set of legal child States from this State.
     * @return the Set of children
     */
    private Set<State> getNextLegalStates(){
        Set<State> nextLegalStates = new HashSet<>();
        List<Point> possibleNextPoints = getCurrentPlayer().getRacer().getPossibleNextPoints(racetrack, getCurrentPlayer().isAI());

        for(Point possiblePoint: possibleNextPoints){
            Move possibleMove = new Move(getCurrentPlayer(), possiblePoint);
            nextLegalStates.add(this.makeMove(possibleMove));
        }

        return nextLegalStates;
    }

    /**
     * Returns the RacetrackAPI associated with this State.
     * @return the RacetrackAPI
     */
    public RacetrackAPI getRacetrack() {
        return racetrack;
    }

    /**
     * Returns the Queue of PlayerAPIs associated with this State.
     * @return the Queue of PlayerAPIs
     */
    public Queue<PlayerAPI> getPlayers(){
        return players;
    }

    /**
     * Returns whether or not the game is over in this State.
     * @return true if game is over, false if not
     */
    public boolean isGameOver(){
        return gameOver;
    }

    /**
     * Returns the parent State of this State
     * @return the parent of this State, null if State has no parent
     */
    public State getParent(){
        return parent;
    }

    /**
     * Performs a check on child States of this State and if it is not a child then returns null,
     * If is a child then return the Move that was made in order to get to it from this State
     * @param to the State to get to, must be a child of this State
     * @return the delta of the parameter State or null if condition is not met
     */
    public Move calculateMoveTo(State to){
        if(!getNextLegalStates().contains(to)){
            System.out.println("State to calculate Move to is not a child of this State");
            return null;
        }
        return to.getDelta();
    }

    /**
     * Convenience and debug method for getNextLegalStates()
     * @return the children of this State
     */
    public Set<State> getChildren() {

        Set<State> children = getNextLegalStates();

        for(State currentChild: children){
            if(currentChild.getCurrentPlayer().isFinished()){
//                System.out.println(currentChild.getCurrentPlayer()+" is finished in this child: "+currentChild);
            }
        }

        return children;
    }

    /**
     * Returns the Move that was made from this State's parent to produce this State, or null if no parent State
     * @return the delta of this State
     */
    public Move getDelta(){
        return delta;
    }

    /**
     * Returns the number of this State
     * @return this State's number
     */
    public int getStateNumber() {
        return stateNumber;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}
