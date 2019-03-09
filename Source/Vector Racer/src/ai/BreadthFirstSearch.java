package ai;

import model.Move;
import model.Player;
import model.RacetrackAPI;
import model.State;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class BreadthFirstSearch extends AbstractSolver {

    public Deque<Move> solve(Player player, State initialState, RacetrackAPI racetrack){
        this.player = player;
        this.nodeCount = 0;
        Deque<Node> agenda = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        Node initialNode = new Node(initialState);

        startTime = System.currentTimeMillis();

        agenda.addAll(initialNode.getChildren());
        visited.add(initialNode);
        while(!agenda.isEmpty()){
            Node currentNode = agenda.poll();
            nodeCount++;

            if(currentNode.isGoal()){
                return calculateMoves(initialNode, currentNode);
            }else{
                visited.add(currentNode);
                List<Node>
            }
        }
    }

}
