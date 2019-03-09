package ai;

import model.Move;
import model.Player;

import java.util.Deque;
import java.util.List;

public abstract class AbstractSolver {


    // TODO NEED TO IMPLEMENT MECHANISM FOR SKIPPING CURRENT PLAYER IN STATES WHERE CURRENT PLAYER IS NOT THE AI
    // MIGHT BE BETTER OFF IN SUBCLASSES OF THIS



    protected Player player;
    protected long startTime;
    protected long solveDuration;
    protected int nodeCount;

    public Deque<Move> calculateMoves(Node from, Node to){
        int depth = 0;
        List<Move>
    }

}
