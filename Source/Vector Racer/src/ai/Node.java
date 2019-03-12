package ai;

import model.Move;
import model.Player;
import model.State;

import java.util.*;

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

    public boolean isGoal(PlayerAPI player){
        // TODO NEED TO CHECK THAT THE CURRENT PLAYER IS ALWAYS THE AI PLAYER
        return (player.isFinished());
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

    /**
     *
     * @param to the Node to get to, must be a child of this Node
     * @return
     */
    public Move calculateMoveTo(Node to){
        if(!isChild(to)){
            System.out.println("Node to calculate Move to is not a child of this Node");
            return null;
        }
        return to.getDelta();
    }

    public boolean isChild(Node node){
        for(Node currentChild: getChildren()){
            if(currentChild.equals(node)){
                return true;
            }
        }
        return false;
    }

    public Node getParent(){
        return parent;
    }

    public Move getDelta(){
        return delta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return getState().equals(node.getState()) &&
                Objects.equals(parent, node.parent) &&
                Objects.equals(delta, node.delta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), parent, delta);
    }
}
