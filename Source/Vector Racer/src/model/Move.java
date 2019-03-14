package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.Objects;

public class Move extends Vect {

    private PlayerAPI playerToMove;
    private Point destination;

    public Move(Move original){
        super(original.playerToMove.getRacer().getPosition(), original.getDestination());
        this.playerToMove = new Player(original.getPlayerToMove());
        this.destination = new Point(original.getDestination());
    }

    public Move(PlayerAPI playerToMove, Point destination) {
        super(playerToMove.getRacer().getPosition(), destination);
        this.playerToMove = (Player) playerToMove;
        this.destination = destination;
    }

    public Point getDestination(){
        return destination;
    }

    public PlayerAPI getPlayerToMove(){
        return playerToMove;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerToMove, destination);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(!(obj instanceof Move))
            return false;
        if(obj == this)
            return true;
        return ( (this.playerToMove == ((Move) obj).playerToMove) && (this.destination.equals(((Move) obj).destination)) );
    }

}
