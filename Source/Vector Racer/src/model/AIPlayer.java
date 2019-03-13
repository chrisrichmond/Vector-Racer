package model;

import ai.*;
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
        }else if(algorithmName.toLowerCase().equals("dfs")) {
//            this.algorithm = new DepthFirstSearch();
        }else if(algorithmName.toLowerCase().equals("bfswz")) {
//            this.algorithm = new BreadthFirstSearchWithZoning();
        }else if(algorithmName.toLowerCase().equals("dfswz")) {
//            this.algorithm = new DepthFirstSearchWithZoning();
        }else{
            System.out.println("SEARCH ALGORITHM DEFAULTING TO BFS");
            this.algorithm = new BreadthFirstSearch();
        }

        this.solved = false;
        this.solution = new ArrayDeque<>();
    }

    public boolean findSolution(State initialState){
        if(!solved){
            solution = algorithm.solve(this, initialState);
            if(solution != null){
                solved = true;
            }
        }
        return solved;
    }

    public Move getMove() {
        if (solved) {
            return solution.pop();
        }else{
            System.out.println("Cannot get AI move as no solution exists yet!");
            return null;
        }
    }

    public boolean isSolved(){
        return solved;
    }
}
