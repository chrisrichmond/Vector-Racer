package model;

import model.geometry.Square;

public interface Terrain {

    /**
     * Whether or not this form of Terrain is able to be moved through
     * @return true if this object is traversable, false if not
     */
    public boolean isTraversable();

    /**
     * The resistance factor of this form of Terrain, slows racers down, ranging from 0.00 to 1.00
     * @return float representing the resistance
     */
    public float getResistance();


}
