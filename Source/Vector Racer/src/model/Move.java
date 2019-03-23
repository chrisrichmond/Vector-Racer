package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.Objects;

/**
 * Represents a move in the game.
 * Consists of a player to move and a destination to move to.
 */
public class Move extends Vect {

    /**
     * The PlayerAPI to move
     */
    private PlayerAPI playerToMove;

    /**
     * The destination Point
     */
    private Point destination;

    /**
     * Copy constructor - creates a new instance of Move.
     * @param original
     */
    public Move(Move original){
        super(original.playerToMove.getRacer().getPosition(), original.getDestination());
        this.playerToMove = new Player(original.getPlayerToMove());
        this.destination = new Point(original.getDestination());
    }

    /**
     * Creates a new instance of Move.
     * @param playerToMove the PlayerAPI to move
     * @param destination the destination Point
     */
    public Move(PlayerAPI playerToMove, Point destination) {
        super(playerToMove.getRacer().getPosition(), destination);
        this.playerToMove = (Player) playerToMove;
        this.destination = destination;
    }

    /**
     * Returns the destination Point of this Move
     * @return the destination Point
     */
    public Point getDestination(){
        return destination;
    }

    /**
     * Returns the PlayerAPI to move in this Move
     * @return the PlayerAPI to move
     */
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
