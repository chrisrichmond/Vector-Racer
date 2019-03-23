package model;

import ai.*;
import javafx.scene.paint.Color;
import model.geometry.Point;
import java.util.Deque;
import java.util.LinkedList;

public class AIPlayer extends Player {

    protected AbstractSolver algorithm;
    protected boolean solved;
    protected Deque<Point> solution;

    public AIPlayer(AIPlayer original){
        super(new String(original.getName()),
                new Racer(original.getRacer()),
                original.getColor(),
                true);
        this.algorithm = original.getAlgorithm();
        this.solved = original.isSolved();
        this.solution = new LinkedList<>(original.getSolution());
    }

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
        this.solution = new LinkedList<>();
    }

    public Deque<Point> getSolution(){
        return solution;
    }

    public AbstractSolver getAlgorithm(){
        return algorithm;
    }

    public boolean findSolution(State initialState){
        if(!solved){
            solution = algorithm.solve(initialState); // fixme changed to remove passing of this player
            if(solution != null){
                solved = true;
            }
        }
        System.out.println("S O L V E D");
        return solved;
    }

    public Move getMove() {
        if (solved) {
            return new Move(this, solution.removeLast());
        } else {
            System.out.println("Cannot get AI move as no solution exists yet!");
            return null;
        }
    }

    public boolean isSolved(){
        return solved;
    }

}
