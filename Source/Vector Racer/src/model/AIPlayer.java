package model;

import ai.*;
import javafx.scene.paint.Color;
import model.geometry.Point;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Class representing an AI player in the game.
 */
public class AIPlayer extends Player {

    /**
     * The algorithm this AI will apply when attempting to find a solution
     */
    protected AbstractSolver algorithm;

    /**
     * Whether or not this AIPlayer has found a solution
     */
    protected boolean solved;

    /**
     * The Deque of Points representing a solution to a Point in a goal State
     */
    protected Deque<Point> solution;

    /**
     * Copy constructor - creates a new instance of AIPlayer.
     * @param original the AIPlayer to deep copy
     */
    public AIPlayer(AIPlayer original){
        super(new String(original.getName()),
                new Racer(original.getRacer()),
                original.getColor(),
                true);
        this.algorithm = original.getAlgorithm();
        this.solved = original.isSolved();
        this.solution = new LinkedList<>(original.getSolution());
    }

    /**
     * Creates a new instance of AIPlayer.
     * @param name the name of this AIPlayer
     * @param racer the Racer associated with this AIPlayer
     * @param color the Color associated with this AIPlayer
     * @param algorithmName the abbreviated name of the algorithm to use when this AIPlayer is solving
     */
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
            solution = algorithm.solve(initialState);
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
