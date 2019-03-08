package ai;

import model.Move;
import model.State;

import java.util.Deque;
import java.util.List;

public abstract class AbstractSolver {

    protected long startTime;
    protected long solveDuration;
    protected int nodeCount;

    public Deque<Move> calculateMoves(State from, State to){
        int depth = 0;
        List<Move>
    }

}
