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
    private Move delta = null;
    private boolean aiSolverMode = false;

    public State(Queue<PlayerAPI> players, RacetrackAPI racetrack, int stateNumber, State parent, Move delta, boolean aiSolverMode){
        this.currentPlayer = players.peek();
        this.players = players;
        this.racetrack = racetrack;
        this.stateNumber = stateNumber;
        this.gameOver = false;
        this.parent = parent;
        this.delta = delta;
        this.aiSolverMode = aiSolverMode;

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

        /*
         At this point the game is now waiting for a player to take their turn.
         If the state is in AI solver mode then it will first ensure that the current player is the AI
         then will
          */
//        if(aiSolverMode){
//            for(PlayerAPI p: players){
//                System.out.println("p.isAI(): "+p.isAI());
//            }
//
//            try{
//                Thread.sleep(2000);
//            }catch (Exception e){
//
//            }
//            while(!currentPlayer.isAI()){
//                System.out.println("currentPlayer before is: "+currentPlayer);
//                skipCurrentPlayer();
//                System.out.println("currentPlayer after is: "+currentPlayer);
//                System.out.println("SOMEHOW STUCK IN HERE?");
//            }
//        }

        /*
        // everything in here is probably very wrong, don't uncomment unless absolutely sure

        if(aiSolverMode){
            while(!currentPlayer.isAI()){
                skipCurrentPlayer();
            }
            Random random = new Random();
            List<Point> points = currentPlayer.getRacer().getPossibleNextPoints(racetrack);
            this.makeMove(new Move(currentPlayer, points.get(random.nextInt(points.size()))));
        }else if(currentPlayer.isAI()){
            if(((AIPlayer)currentPlayer).isSolved()) {  // this must be true if aiSolverMode is false anyway
                this.makeMove(((AIPlayer) currentPlayer).getMove());
            }
        }
        */

//        if(currentPlayer.isAI()){
//            if(((AIPlayer)currentPlayer).isSolved()) {
//                this.makeMove(((AIPlayer) currentPlayer).getMove());
//            }
//            else{
//                Random random = new Random();
//                List<Point> points = currentPlayer.getRacer().getPossibleNextPoints();
//                this.makeMove(new Move(currentPlayer, points.get(random.nextInt(points.size()))));
//            }
//        }
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
            currentPlayer.getRacer().moveWhilstApplyingEffects(racetrack, move.destination);
//            System.out.println("new velo = row:"+(currentPlayer.getRacer().getVelocity().getYVelo()+" col:"+currentPlayer.getRacer().getVelocity().getXVelo()));

            System.out.println("Legal move, moving "+currentPlayer.getName()+" to R"+currentPlayer.getRacer().getPosition().getY()+" C"+currentPlayer.getRacer().getPosition().getX());
            // only switch players if not in AI solver mode
            if(!aiSolverMode){
                players.poll();                 // todo these could cause issues, do we want to leave the old state with altered attributes, yes/no?
                players.add(currentPlayer);
            }

            return new State(players, racetrack, stateNumber+1, this, move, aiSolverMode);
        }else{
            System.out.println(stateNumber);
            System.out.println("Illegal move! "+move.playerToMove.getName() + " R"+move.destination.getY()+ " C"+move.destination.getX());
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
                getCurrentPlayer().equals(state.getCurrentPlayer()) &&
                getPlayers().equals(state.getPlayers()) &&
                getRacetrack().equals(state.getRacetrack()) &&
                Objects.equals(getParent(), state.getParent()) &&
                Objects.equals(delta, state.delta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrentPlayer(), getPlayers(), getRacetrack(), getStateNumber(), isGameOver(), getParent(), delta);
    }

    public boolean isMoveLegal(Move move){
        // compare position/vector of Player in next state against a list of the 9 next legal positions based on the current position of the Player in the current State

        boolean legal = true;

        if(!(move.playerToMove.getRacer().getPossibleNextPoints(racetrack).contains(move.destination))){
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
        List<Point> possibleNextPoints = currentPlayer.getRacer().getPossibleNextPoints(racetrack);

//        System.out.println("possibleNextPoints.size(): "+possibleNextPoints.size());

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

    public List<State> getChildren(){
        return getNextLegalStates();
    }

    public Move getDelta(){
        return delta;
    }

    public int getStateNumber() {
        return stateNumber;
    }
}
