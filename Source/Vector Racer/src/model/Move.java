package model;

import model.geometry.Point;
import model.geometry.Vect;

public class Move extends Vect {

    private Player playerToMove;

    public Move(Player playerToMove, Point end) {
        super(playerToMove.getRacer().getPosition(), end);
        this.playerToMove = playerToMove;
    }

}
