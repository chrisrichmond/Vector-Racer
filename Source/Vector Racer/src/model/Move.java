package model;

import model.geometry.Point;
import model.geometry.Vect;

public class Move extends Vect {

    Player playerToMove;
    Point destination;

    public Move(Player playerToMove, Point destination) {
        super(playerToMove.getRacer().getPosition(), destination);
        this.playerToMove = playerToMove;
        this.destination = destination;
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
