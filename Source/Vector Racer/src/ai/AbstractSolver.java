package ai;

import model.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public abstract class AbstractSolver {


    // TODO NEED TO IMPLEMENT MECHANISM FOR SKIPPING CURRENT PLAYER IN STATES WHERE CURRENT PLAYER IS NOT THE AI
    // OR DO I?
    // MIGHT BE BETTER OFF IN SUBCLASSES OF THIS



    protected PlayerAPI player;
    protected long startTime;
    protected long solveDuration;
    protected int nodeCount;

    public abstract Deque<Move> solve(PlayerAPI player, State initialState);

    /**
     * Produces a stack of Move objects to get from one Node to another
     * @param from Node to search from
     * @param to Node to search to
     * @return Deque of Moves to get to the goal
     */
    public Deque<Move> calculateMoves(Node from, Node to){
        int depth = 0;
        Deque<Move> moves = new ArrayDeque<>();

        while(!(from.equals(to))){                  // loop while the Node we're going to is not the Node we were originally coming from (as in the first Node)
            depth++;                                // increase depth count by 1
            Node next = to.getParent();             // use temporary Node variable to store the parent Node of the Node we were going to
            moves.push(next.calculateMoveTo(to));   // push the Move required to get from the parent Node to the Node we are currently going to, onto the top of the Stack
            to = next;                              // set the Node we're going to to the Parent
        }
        solveDuration = System.currentTimeMillis() - startTime;
        return moves;
    }

    public Deque<Move> fail(){
        solveDuration = System.currentTimeMillis() - startTime;
        System.out.println("AI couldn't find goal state solution after searching "+nodeCount+" nodes in "+solveDuration/1000+" seconds");
        return null;
    }

}
