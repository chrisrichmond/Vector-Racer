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

        agenda.addAll(initialState.getChildren());  // todo gets stuck looping in here

        visited.add(initialState);
        while(!agenda.isEmpty()){
            State currentState = agenda.poll();
            nodeCount++;

            //if(currentState.isGoal(player)){
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





//package ai;
//
//import model.*;
//import model.geometry.Point;
//
//import java.util.*;
//
//public class BreadthFirstSearch extends AbstractSolver {
//
//    public Deque<Move> solve(PlayerAPI player, State initialState, RacetrackAPI racetrack){
//        this.player = player;
//        this.nodeCount = 0;
//        Deque<Node> agenda = new ArrayDeque<>();
//        Set<Node> visited = new HashSet<>();
//        Node initialNode = new Node(initialState);
//
//        startTime = System.currentTimeMillis();
//
//        agenda.addAll(initialNode.getChildren());
//        visited.add(initialNode);
//        while(!agenda.isEmpty()){
//            Node currentNode = agenda.poll();
//            nodeCount++;
//
//            if(currentNode.isGoal(player)){
//                return calculateMoves(initialNode, currentNode);
//            }
//            visited.add(currentNode);
//            List<Node> children = currentNode.getChildren();
//            for(Node child: children){
//                if((child != null) && (!visited.contains(child))){
//                    agenda.add(child);
//                    visited.add(child);
//                }
//            }
//            System.out.println("AGENDA: ");
//            for(Node current: agenda){
//                State currentState = current.getState();
//                PlayerAPI currentPlayer = currentState.getCurrentPlayer();
//                Point currentPosition = currentPlayer.getRacer().getPosition();
//                System.out.println("State number "+currentState.getStateNumber()+" - "+currentPlayer.getName()+" on R"+currentPosition.getY()+" C"+currentPosition.getX());
//            }
//            try{
//                Thread.sleep(500);
//            }catch (InterruptedException e){
//                System.out.println("INTERRUPTED");
//            }
//        }
//        return fail();
//    }
//
//}
