package ai;

import model.*;
import model.geometry.Point;

import java.util.*;

/**
 * Class for representing a Breadth-First Search solver algorithm using the Vector Racer State system
 */
public class BreadthFirstSearch extends AbstractSolver {

    @Override
    public Deque<Point> solve(State initState){
        State initialState = new State(initState);
        // SETTING STATE GRAPH INTO AI MODE
        initialState.setAiSolverMode(true);
        this.nodeCount = 0;
        Deque<State> agenda = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        startTime = System.currentTimeMillis();
        agenda.addAll(initialState.getChildren());
        visited.add(encodeVisited(initialState.getStateNumber(), initialState.getCurrentPlayer()));
        int currentZone = 0;
        while(!agenda.isEmpty()){
            State currentState = agenda.poll();
            nodeCount++;

            if(currentState.getCurrentPlayer().getRacer().getCurrentZone()>currentZone){ // fixme
                System.out.println("AI moved from Zone "+currentZone+" to "+(++currentZone));
                agenda.clear();
            }

            // GOAL STATE RECOGNISER
            if(currentState.getCurrentPlayer().isFinished()){
                initialState.setAiSolverMode(false);
                return currentState.getCurrentPlayer().getRacer().getPointRoute();
            }

            visited.add(encodeVisited(currentState.getStateNumber(), currentState.getCurrentPlayer()));
            Set<State> children = currentState.getChildren();
            for(State child: children){
                int encodedChild = encodeVisited(child.getStateNumber(), child.getCurrentPlayer());
                if((child != null) && (!visited.contains(encodedChild))){
                    agenda.add(child);
                }
            }
        }
        initialState.setAiSolverMode(false);
        return fail();
    }
}
