//package ai;
//
//import model.Move;
//import model.PlayerAPI;
//import model.State;
//
//import java.util.*;
//
//public class IterativeDeepeningSearch extends AbstractSolver {
//
//
//
//
//    // todo this isn't finished yet
//
//    @Override
//    public Deque<Move> solve(PlayerAPI player, State initialState) {
//        // SETTING STATE GRAPH INTO AI MODE
//        initialState.setAiSolverMode(true);
//
//        this.player = player;
//        this.nodeCount = 0;
//        int depthLimit = 5;
//        int maxDepthLimit = 15;
//        Deque<State> agenda = new ArrayDeque<>();
//        Set<State> visited = new HashSet<>();
//
//        startTime = System.currentTimeMillis();
//
//        agenda.addAll(initialState.getChildren());
//        visited.add(initialState);
//
//        int depth = 0;
//        while(depthLimit<maxDepthLimit){
//            while(!agenda.isEmpty()) {
//                State currentState = agenda.pop();
//                nodeCount++;
//
//                if (player.isFinished()) {
//                    System.out.println("PLAYER FINISHED");
//                    return calculateMoves(initialState, currentState);
//                }
//
//                visited.add(currentState);
//
//                if (depth < depthLimit) {
//                    Set<State> children = currentState.getChildren();
//                    for (State child : children) {
//                        if ((child != null) && (!visited.contains(child))) {
//                            agenda.push(child);
//                            visited.add(child);
//                        }
//                    }
//                    depth++;
//                }
//            }
//            depthLimit++;
//        }
//
//        return fail();
//    }
//}
