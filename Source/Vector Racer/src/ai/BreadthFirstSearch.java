package ai;

import model.*;
import model.geometry.Point;

import java.util.*;

public class BreadthFirstSearch extends AbstractSolver {

    public Deque<Move> solve(PlayerAPI player, State initialState){
        // SETTING STATE GRAPH INTO AI MODE
        initialState.setAiSolverMode(true);

        this.player = player;
        this.nodeCount = 0;
        Deque<State> agenda = new ArrayDeque<>();
        Set<State> visited = new HashSet<>();

        startTime = System.currentTimeMillis();

        System.out.println("IN SOLVER");

        agenda.addAll(initialState.getChildren());
        System.out.println(agenda.size());
        visited.add(initialState);
        while(!agenda.isEmpty()){
            State currentState = agenda.poll();
            nodeCount++;

            // GOAL STATE RECOGNISER
            if(player.isFinished()){
                initialState.setAiSolverMode(false);
                System.out.println("PLAYER FINISHED");
                return calculateMoves(initialState, currentState);
            }

            visited.add(currentState);
            List<State> children = currentState.getChildren();
            for(State child: children){
                if((child != null) && (!visited.contains(child))){
                    agenda.add(child);
                    visited.add(child);
                }
            }
            System.out.println("AGENDA: ");
            for(State current: agenda){
                PlayerAPI currentPlayer = current.getCurrentPlayer();
                Point currentPosition = currentPlayer.getRacer().getPosition();
                System.out.println("Depth (state num) "+currentState.getStateNumber()+" - "+currentPlayer.getName()+" on R"+currentPosition.getY()+" C"+currentPosition.getX());
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

