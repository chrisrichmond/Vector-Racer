package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.lang.reflect.Field;
import java.util.*;

public class State {

    private PlayerAPI currentPlayer; // the player whose turn it is in this State
    private Queue<PlayerAPI> players; // all players currently existing in this State
    private RacetrackAPI racetrack; // the racetrack existing in this State
    private int stateNumber;
    private boolean gameOver;
    private State parent = null;
    private Move delta = null;
    private boolean aiSolverMode = false;

    public State(State original){
        this.players = new ArrayDeque<>();
        for(PlayerAPI p: original.getPlayers()){
            if(p.isAI()) {
                this.players.add(new AIPlayer((AIPlayer) p));
            }else{
                this.players.add(new Player(p));
            }
        }
        this.currentPlayer = this.players.peek();
        this.racetrack = new Racetrack(original.getRacetrack());
        this.stateNumber = original.getStateNumber();
        this.gameOver = original.isGameOver();
        if(original.getParent() != null)
            this.parent = new State(original.getParent());
        if(original.getDelta() != null)
            this.delta = new Move(original.getDelta());
        this.aiSolverMode = original.isAiSolverMode();
    }

    public State(Queue<PlayerAPI> players, RacetrackAPI racetrack, int stateNumber, State parent, Move delta, boolean aiSolverMode){
        this.currentPlayer = new Player(players.peek());
        this.players = players;
        this.racetrack = racetrack;
        this.stateNumber = stateNumber;
        this.gameOver = false;
        this.parent = parent;
        this.delta = delta;
        this.aiSolverMode = aiSolverMode;

//        this.currentPlayer = new Player(players.peek());
//        this.players = new ArrayDeque<>(players);
//        this.racetrack = new Racetrack(racetrack);
//        this.stateNumber = stateNumber;
//        this.parent = parent;
//        if(delta != null)
//            this.delta = new Move(delta);
//        this.aiSolverMode = aiSolverMode;

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

//        System.out.println("**************************");
//        System.out.println(currentPlayer.getName()+" R"+currentPlayer.getRacer().getPosition().getY()+" C"+currentPlayer.getRacer().getPosition().getX());
        for(PlayerAPI player: players){
            if(racetrack.isTouchingWall(player.getRacer())){
                if(!player.isFinished()) {
                    System.out.println("IN WALL, POPPING");
                    player.getRacer().getPointRoute().pop();
                    player.getRacer().killVelocity();
                }
            }
        }
//        System.out.println(currentPlayer.getName()+" R"+currentPlayer.getRacer().getPosition().getY()+" C"+currentPlayer.getRacer().getPosition().getX());

//        System.out.println("***************************");
    }

    public void setAiSolverMode(boolean aiSolverMode){
        this.aiSolverMode = aiSolverMode;
    }

    public boolean isAiSolverMode(){
        return aiSolverMode;
    }

    /**
     * Called when the current player has already finished the race and so the turn is passed to the following player
     */
    public void skipCurrentPlayer(){
        PlayerAPI previousPlayer = players.poll();
        currentPlayer = players.peek();
        players.add(previousPlayer);
    }

    public State makeMove(Move move){
        if(isMoveLegal(move)){
            // return new State with currentPlayer changed to the next in the list
            Player currentPlayerClone = new Player(currentPlayer);
            currentPlayerClone.getRacer().moveWhilstApplyingEffects(racetrack, move.getDestination());

            System.out.println("Legal move, moving "+currentPlayer.getName()+" to R"+currentPlayer.getRacer().getPosition().getY()+" C"+currentPlayer.getRacer().getPosition().getX());
            // only switch players if not in AI solver mode
            if(!aiSolverMode){
                players.poll();                 // todo these could cause issues, do we want to leave the old state with altered attributes, yes/no?
                players.add(currentPlayer);
            }

            return new State(players, racetrack, stateNumber+1, this, move, aiSolverMode);
        }else{
            System.out.println(stateNumber);
//            System.out.println("Illegal move! "+move.getPlayerToMove().getName() + " R"+move.getDestination().getY()+ " C"+move.getDestination().getX());
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

    public boolean isMoveLegal(Move move){
        // compare position/vector of Player in next state against a list of the 9 next legal positions based on the current position of the Player in the current State

        boolean legal = true;

        if(!(move.getPlayerToMove().getRacer().getPossibleNextPoints(racetrack).contains(move.getDestination()))){
            // the offered destination is not one of the Player to be moved's next valid positions
            System.out.println("the offered destination is not one of the Player to be moved's next valid positions");
            legal = false;
        }else if( (move.getDestination().getX() < 0) || (move.getDestination().getX() >= racetrack.getCols()) || (move.getDestination().getY() < 0) || (move.getDestination().getY() >= racetrack.getRows()) ){
            // the offered destination is outwith the bounds of the racetrack
            System.out.println("the offered destination is outwith the bounds of the racetrack");
            legal = false;
        }

        return legal;
    }

    private Set<State> getNextLegalStates(){
        Set<State> nextLegalStates = new HashSet<>();
        List<Point> possibleNextPoints = currentPlayer.getRacer().getPossibleNextPoints(racetrack);

        System.out.println("possibleNextPoints.size(): "+possibleNextPoints.size());

        for(Point possiblePoint: possibleNextPoints){
            Move possibleMove = new Move(currentPlayer, possiblePoint);
//            System.out.println("BEFORE MAKING MOVE");
            nextLegalStates.add(this.makeMove(possibleMove));   // todo could lead to duplicate states being added, which doesn't present an immediate issue but could be inefficient so maybe consider some kind of redesign to remove dupes
//            System.out.println("IN NEXTLEGALSTATES FOR LOOP AFTER ADDING NEW STATE FROM MAKEMOVE()");
        }

        return nextLegalStates;
    }

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
//    public HashMap<Move, State> getChildren(){
//        State copiedCurrentState = new State(this);
//        HashMap<Move, State> children = new HashMap();
//        for(Point currentPossibleNextPoint: this.currentPlayer.getRacer().getPossibleNextPoints()){
//            Move move = new Move(this.currentPlayer, currentPossibleNextPoint);
//            children.put(move, copiedCurrentState.makeMove(move));
//        }
//        return children;
//    }

    /**
     *
     * @param to the State to get to, must be a child of this State
     * @return
     */
    public Move calculateMoveTo(State to){
        if(!getNextLegalStates().contains(to)){
            System.out.println("State to calculate Move to is not a child of this State");
            return null;
        }
        return to.getDelta();
    }

    public Set<State> getChildren() {
//        List<State> children = new ArrayList<>();
        for (State currentNextLegalState : getNextLegalStates()){
            System.out.print("[child state hash "+currentNextLegalState.hashCode()+" "+currentNextLegalState.getCurrentPlayer().getName()+ " R"+currentNextLegalState.getCurrentPlayer().getRacer().getPosition().getY()+" C"+currentNextLegalState.getCurrentPlayer().getRacer().getPosition().getX()+"]   ");
            System.out.println("hash for currentPlayer in currentNextLegalState: "+currentNextLegalState.getCurrentPlayer().hashCode());
            //            children.add(new State(currentNextLegalState));
        }
//        return children;

        return getNextLegalStates();
    }

    public Move getDelta(){
        return delta;
    }

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
