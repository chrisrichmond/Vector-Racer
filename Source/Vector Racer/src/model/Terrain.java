package model;

/**
 * Interface which represents terrain characteristics in the game.
 */
public interface Terrain {

    /**
     * Whether or not this form of Terrain is able to be moved through
     * @return true if this object is traversable, false if not
     */
    boolean isTraversable();

    /**
     * The resistance factor of this form of Terrain, slows racers down, ranging from 0.00 to 1.00
     * @return float representing the resistance
     */
    float getResistance();

}
