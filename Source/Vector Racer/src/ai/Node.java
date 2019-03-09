package ai;

import model.Move;
import model.State;

import java.util.List;

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
        if(state.)
    }

    public List<Node> getChildren(){
        state.getCurrentPlayer().getPossibleNextPoints();
    }
}
