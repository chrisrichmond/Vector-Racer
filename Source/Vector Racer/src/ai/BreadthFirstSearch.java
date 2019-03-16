package ai;

import model.*;
import model.geometry.Point;

import java.util.*;

public class BreadthFirstSearch extends AbstractSolver {

    public Deque<Point> solve(PlayerAPI player, State initState){
//        Player player = new Player(playerToSolveFor);
        State initialState = new State(initState);
        // SETTING STATE GRAPH INTO AI MODE
        initialState.setAiSolverMode(true);
//        System.out.println("************************************************");
//        System.out.println(initState.toString());
//        System.out.println(initialState.toString());
//        System.out.println("************************************************");

        this.player = player;
        this.nodeCount = 0;
        Deque<State> agenda = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        startTime = System.currentTimeMillis();

        System.out.println("IN SOLVER");

        agenda.addAll(initialState.getChildren());
//        System.out.println(agenda.size());
        visited.add(encodeVisited(initialState.getStateNumber(), player));
        int currentZone = 0;
        while(!agenda.isEmpty()){
            State currentState = agenda.poll();
            nodeCount++;

//            System.out.println("currentState being explored in agenda ");
//            System.out.println("HASHCODE: "+ currentState.hashCode());
//            System.out.println("CURRENT PLAYER: "+currentState.getCurrentPlayer() + " " + currentState.getCurrentPlayer().hashCode());
//            System.out.println("ROW: "+currentState.getCurrentPlayer().getRacer().getPosition().getY()+" COL: "+currentState.getCurrentPlayer().getRacer().getPosition().getX()+" ZONE: "+currentState.getCurrentPlayer().getRacer().getCurrentZone());
//            System.out.println("PARENT STATE: " +currentState.getParent());

            if(currentState.getCurrentPlayer().getRacer().getCurrentZone()>currentZone){
                System.out.println("incrementing currentZone from "+currentZone+" to "+(++currentZone));
                agenda.clear();
            }

            // GOAL STATE RECOGNISER
//            System.out.println("exploring currentState: "+currentState);
//            System.out.println("BEFORE GOAL STATE RECOGNISER: "+currentState.getCurrentPlayer().getRacer());
//            System.out.println("finished?: "+currentState.getCurrentPlayer().isFinished());
            if(currentState.getCurrentPlayer().isFinished()){
                initialState.setAiSolverMode(false);    // todo do I actually need this? this is the cloned state so it wont be used anyway
                System.out.println("PLAYER FINISHED (in BFS)");
//                return calculateMoves(initialState, currentState); // fixme CAN JUST CLONE SUCCESSFUL RACER'S POINT ROUTE SURELY???
                return currentState.getCurrentPlayer().getRacer().getPointRoute();
            }

            visited.add(encodeVisited(currentState.getStateNumber(), currentState.getCurrentPlayer()));
            Set<State> children = currentState.getChildren();
            for(State child: children){
//                System.out.println("CURRENT CHILD: "+child.hashCode()+" currentPlayer"+child.getCurrentPlayer().hashCode()+" R"+child.getCurrentPlayer().getRacer().getPosition().getY()+" C"+child.getCurrentPlayer().getRacer().getPosition().getY());
                int encodedChild = encodeVisited(child.getStateNumber(), child.getCurrentPlayer());
//                int currentRow = player.getRacer().getPosition().getY();
//                int currentCol = player.getRacer().getPosition().getX();
                if((child != null) && (!visited.contains(encodedChild))){
                    agenda.add(child);
                }
            }

//            try{
//                Thread.sleep(1000);
//            }catch (InterruptedException e){
//                System.out.println("INTERRUPTED");
//            }
        }
        initialState.setAiSolverMode(false);
        return fail();
    }

}
