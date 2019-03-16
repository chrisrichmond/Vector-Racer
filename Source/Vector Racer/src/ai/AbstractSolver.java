package ai;

import model.*;
import model.geometry.Point;

import java.util.*;

public abstract class AbstractSolver {


    // TODO NEED TO IMPLEMENT MECHANISM FOR SKIPPING CURRENT PLAYER IN STATES WHERE CURRENT PLAYER IS NOT THE AI
    // OR DO I?
    // MIGHT BE BETTER OFF IN SUBCLASSES OF THIS



    protected PlayerAPI player;
    protected long startTime;
    protected long solveDuration;
    protected int nodeCount;

    public abstract Deque<Point> solve(State initialState); // fixme changed signature to remove player parameter and this can be derived from current player of initial state

    /**
     * Produces a stack of Move objects to get from one State to another
     * @param from State to search from
     * @param to State to search to
     * @return Deque of Moves to get to the goal
     */
    public Deque<Move> calculateMoves(State from, State to){
        int depth = 0;
        Deque<Move> moves = new LinkedList<>();

        while(!(from.equals(to))){                  // loop while the State we're going to is not the State we were originally coming from (as in the first State)
            System.out.println("depth: "+depth++);  // increase depth count by 1
            State next = to.getParent();            // use temporary State variable to store the parent State of the State we were going to
//            moves.push(next.calculateMoveTo(to));   // push the Move required to get from the parent State to the State we are currently going to, onto the top of the Stack
           moves.push(to.getDelta());
            to = next;                              // set the State we're going to to the Parent
        }
        solveDuration = System.currentTimeMillis() - startTime;
        return moves;
    }

    public Deque<Point> fail(){
        solveDuration = System.currentTimeMillis() - startTime;
        System.out.println("AI couldn't find goal state solution after searching "+nodeCount+" nodes in "+solveDuration/1000+" seconds");
        return null;
    }

    public int encodeVisited(int stateNumber, PlayerAPI player){
        int xVelo = player.getRacer().getVelocity().getXVelo();
        int yVelo = player.getRacer().getVelocity().getYVelo();
        int xPos = player.getRacer().getPosition().getX();
        int yPos = player.getRacer().getPosition().getY();
        StateDetails stateDetails = new StateDetails(stateNumber, xPos, yPos, xVelo, yVelo);
        return stateDetails.hashCode();
    }

    private class StateDetails{ // fixme re-added velocity and statenumber hashing

//        private int stateNumber;
        private int xVelo;
        private int yVelo;
        private int xPos;
        private int yPos;

        StateDetails(int stateNumber, int xPos, int yPos, int xVelo, int yVelo){
//            this.stateNumber = stateNumber;
            this.xVelo = xVelo;
            this.yVelo = yVelo;
            this.xPos = xPos;
            this.yPos = yPos;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof StateDetails)) return false;
            StateDetails that = (StateDetails) o;
//    fixme        return stateNumber == that.stateNumber &&
            return        xVelo == that.xVelo &&
                    yVelo == that.yVelo &&
                    xPos == that.xPos &&
                    yPos == that.yPos;
        }

        @Override
        public int hashCode() {
            return Objects.hash(xPos, yPos, xVelo, yVelo);
        }
    }

}
