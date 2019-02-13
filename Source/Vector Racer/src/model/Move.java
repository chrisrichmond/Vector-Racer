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

}
