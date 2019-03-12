package ai;

import model.Move;
import model.PlayerAPI;
import model.RacetrackAPI;
import model.State;

import java.util.*;

public class BreadthFirstSearchWithZoning extends AbstractSolver {

    @Override
    public Deque<Move> solve(PlayerAPI player, State initialState, RacetrackAPI racetrack){
        this.player = player;
        this.nodeCount = 0;
        Deque<Node> agenda = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        Node initialNode = new Node(initialState);

        startTime = System.currentTimeMillis();

        agenda.addAll(initialNode.getChildren());
        visited.add(initialNode);
        int currentZone = 0;
        while(!agenda.isEmpty()){
            Node currentNode = agenda.poll();
            nodeCount++;

            if(currentNode.isGoal(player)){
                return calculateMoves(initialNode, currentNode);
            }

            if(currentNode.getState().getCurrentPlayer().getRacer().getCurrentZone()>currentZone){
                agenda.clear();
                currentZone++;
            }

            visited.add(currentNode);
            List<Node> children = currentNode.getChildren();
            for(Node child: children){
                if((child != null) && (!visited.contains(child))){
                    agenda.add(child);
                    visited.add(child);
                }
            }
        }
        return fail();
    }

}
