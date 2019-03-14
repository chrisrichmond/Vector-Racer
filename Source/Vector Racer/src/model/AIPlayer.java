package model;

import ai.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;

public class AIPlayer extends Player {

    protected AbstractSolver algorithm;
    protected boolean solved;
    protected Deque<Move> solution;

    public AIPlayer(AIPlayer original){
        super(new String(original.getName()),
                new Racer((Racer)original.getRacer()),
                original.getColor(),
                true);
        this.algorithm = original.getAlgorithm();
        this.solved = original.isSolved();
        this.solution = new ArrayDeque<>(original.getSolution());
    }

    public AIPlayer(String name, RacerAPI racer, Color color, String algorithmName) {
        super(name, racer, color, true);

        if(algorithmName.toLowerCase().equals("bfs")) {
            this.algorithm = new BreadthFirstSearch();
        }else if(algorithmName.toLowerCase().equals("dfs")) {
            this.algorithm = new DepthFirstSearch();
        }else if(algorithmName.toLowerCase().equals("bfswz")) {
            this.algorithm = new BreadthFirstSearchWithZoning();
        }else if(algorithmName.toLowerCase().equals("dfswz")) {
            this.algorithm = new DepthFirstSearchWithZoning();
        }else{
            System.out.println("SEARCH ALGORITHM DEFAULTING TO BFS");
            this.algorithm = new BreadthFirstSearch();
        }

        this.solved = false;
        this.solution = new ArrayDeque<>();
    }

    public Deque<Move> getSolution(){
        return solution;
    }

    public AbstractSolver getAlgorithm(){
        return algorithm;
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

//    public String toString() {
//        StringBuilder result = new StringBuilder();
//        String newLine = System.getProperty("line.separator");
//
//        result.append( this.getClass().getName() );
//        result.append( " Object {" );
//        result.append(newLine);
//
//        //determine fields declared in this class only (no fields of superclass)
//        Field[] fields = this.getClass().getDeclaredFields();
//
//        //print field names paired with their values
//        for ( Field field : fields  ) {
//            result.append("  ");
//            try {
//                result.append( field.getName() );
//                result.append(": ");
//                //requires access to private field:
//                result.append( field.get(this) );
//            } catch ( IllegalAccessException ex ) {
//                System.out.println(ex);
//            }
//            result.append(newLine);
//        }
//        result.append("}");
//
//        return result.toString();
//    }

}
