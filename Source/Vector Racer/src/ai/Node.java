package ai;

import model.Move;
import model.State;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Node {

    private State state;
    private Node parent;
    private Move delta;

    public Node(State state){
        this(state, null, null);
    }

    public Node(State state, Node parent, Move delta){
        this.state = state;
        this.parent = parent;
        this.delta = delta;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isGoal(){
        // TODO NEED TO CHECK THAT THE CURRENT PLAYER IS ALWAYS THE AI PLAYER
        return (state.getCurrentPlayer().isFinished());
    }

    public List<Node> getChildren(){
        List<Node> nextNodes = new ArrayList<>();

        Iterator iterator = state.getChildren().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry pair = (Map.Entry)iterator.next();
            nextNodes.add(new Node((State)(pair.getValue()), this, (Move)(pair.getKey())));
            iterator.remove();
        }

        return nextNodes;
    }
}
