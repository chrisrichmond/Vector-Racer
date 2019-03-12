package model;

import ai.AbstractSolver;
import ai.BreadthFirstSearch;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Deque;

public class AIPlayer extends Player {

    private AbstractSolver algorithm;
    private boolean solved;
    private Deque<Move> solution;

    public AIPlayer(String name, RacerAPI racer, Color color, String algorithmName) {
        super(name, racer, color, true);

        if(algorithmName.toLowerCase().equals("bfs")) {
            this.algorithm = new BreadthFirstSearch();
        }else{
            this.algorithm = new BreadthFirstSearch();
        }

        this.solved = false;
        this.solution = new ArrayDeque<>();
    }

    public boolean findSolution(State initialState){
        if(!solved){
            solution = algorithm.solve(this, initialState, initialState.getRacetrack());
            if(solution != null){
                solved = true;
            }
        }
        return solved;
    }

    public Move getMove(){
        return solution.pop();
    }
}
