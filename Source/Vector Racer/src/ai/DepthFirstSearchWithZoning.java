//package ai;
//
//import model.Move;
//import model.PlayerAPI;
//import model.RacetrackAPI;
//import model.State;
//
//import java.util.*;
//
//public class DepthFirstSearchWithZoning extends AbstractSolver {
//
//    @Override
//    public Deque<Move> solve(PlayerAPI player, State initialState) {
//        // SETTING STATE GRAPH INTO AI MODE
//        initialState.setAiSolverMode(true);
//
//        this.player = player;
//        this.nodeCount = 0;
//        Deque<State> agenda = new ArrayDeque<>();
//        Set<State> visited = new HashSet<>();
//
//        startTime = System.currentTimeMillis();
//
//        agenda.addAll(initialState.getChildren());
//        visited.add(initialState);
//        int currentZone = 0;
//        while (!agenda.isEmpty()){
//            State currentState = agenda.pop();
//            nodeCount++;
//
//            if(player.isFinished()){
//                return calculateMoves(initialState, currentState);
//            }
//
//            if(player.getRacer().getCurrentZone()>currentZone){
//                agenda.clear();
//                currentZone++;
//            }
//
//            visited.add(currentState);
//            Set<State> children = currentState.getChildren();
//            for(State child: children){
//                if((child != null) && (!visited.contains(child))){
//                    agenda.push(child);
//                    visited.add(child);
//                }
//            }
//        }
//
//        return fail();
//    }
//}
