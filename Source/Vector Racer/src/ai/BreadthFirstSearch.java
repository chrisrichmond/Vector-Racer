package ai;

import model.*;
import model.geometry.Point;

import java.util.*;

public class BreadthFirstSearch extends AbstractSolver {

    public Deque<Move> solve(PlayerAPI playerToSolveFor, State initState){
//        Player player = new Player(playerToSolveFor);
//        State initialState = new State(initState);
        Player player = (Player) playerToSolveFor;
        State initialState = initState;

        // SETTING STATE GRAPH INTO AI MODE
        initialState.setAiSolverMode(true);

        this.player = player;
        this.nodeCount = 0;
        Deque<State> agenda = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        startTime = System.currentTimeMillis();

        System.out.println("IN SOLVER");

        agenda.addAll(initialState.getChildren());
        System.out.println(agenda.size());
        visited.add(encodeVisited(player));
        while(!agenda.isEmpty()){
            State currentState = agenda.poll();
            nodeCount++;

            System.out.println("================================");
            System.out.println("CURRENTSTATE:   "+currentState.hashCode()+" ("+currentState.getStateNumber()+")");
            System.out.print("AGENDA:   ");
            for(State state: agenda){
                System.out.print("["+state.hashCode()+" "+state.getCurrentPlayer().getName()+ " R"+state.getCurrentPlayer().getRacer().getPosition().getY()+" C"+state.getCurrentPlayer().getRacer().getPosition().getX()+"]   ");
            }
            System.out.println();



            // GOAL STATE RECOGNISER
            if(player.isFinished()){
                initialState.setAiSolverMode(false);
                System.out.println("PLAYER FINISHED");
                return calculateMoves(initialState, currentState);
            }

            visited.add(encodeVisited(currentState.getCurrentPlayer()));
            List<State> children = currentState.getChildren();
            for(State child: children){
                int encodedChild = encodeVisited(child.getCurrentPlayer());
                int currentRow = player.getRacer().getPosition().getY();
                int currentCol = player.getRacer().getPosition().getX();
                if((child != null) && (!visited.contains(encodedChild))){
//                    if(child.getRacetrack().)
                    agenda.add(child);

                }
            }
//            System.out.println("AGENDA: ");
//            for(State current: agenda){
//                PlayerAPI currentPlayer = current.getCurrentPlayer();
//                Point currentPosition = currentPlayer.getRacer().getPosition();
//                //System.out.println("Depth (state num) "+currentState.getStateNumber()+" - "+currentPlayer.getName()+" on R"+currentPosition.getY()+" C"+currentPosition.getX());
////            }
////            try{
////                Thread.sleep(1000);
////            }catch (InterruptedException e){
////                System.out.println("INTERRUPTED");
//            }
        }
        initialState.setAiSolverMode(false);
        return fail();
    }

}
