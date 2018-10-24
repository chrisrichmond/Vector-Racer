package model;

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

    /**
     * The slide factor of this form of Terrain, makes it difficult for racers to change their current velocity, ranging from 0.00 to 1.00
     * @return float representing the slide
     */
    public float getSlide();

    /**
     * The chaos factor of this form of Terrain, takes control away from the player and applies a random velocity to their current turn, ranging from 0.00 to 1.00
     * @return float representing the chaos
     */
    public float getChaos();


}
