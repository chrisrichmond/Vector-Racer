package model;

import javafx.scene.paint.Color;
import model.geometry.Square;

import java.util.Objects;

/**
 * Abstract class which represents a tile in the game.
 */
public abstract class Tile extends Square implements Terrain {

    /**
     * Whether or not this Tile is traversable.
     */
    private boolean traversable;

    /**
     * The resistance value of this Tile.
     */
    private float resistance;

    /**
     * The Color associated with this Tile.
     */
    private Color color;

    /**
     * Copy constructor - creates a new instance of Tile.
     * @param original the Tile to deep copy
     */
    public Tile(Tile original){
        super(original.getStartX(), original.getStartY());
        this.traversable = original.isTraversable();
        this.resistance = original.getResistance();
        this.color = original.getColor();
    }

    /**
     * Creates a new instance of Tile.
     * @param startX the horizontal component marking the position of the Tile
     * @param startY the vertical component marking the position of the Tile
     * @param traversable whether or not this Tile is traversable
     * @param resistance the resistance value of this Tile
     */
    public Tile(int startX, int startY, boolean traversable, float resistance){
        super(startX, startY);
        this.traversable = traversable;
        this.resistance = resistance;
    }

    /**
     * Creates a new instance of Tile.
     * @param startX the horizontal component marking the position of the Tile
     * @param startY the vertical component marking the position of the Tile
     * @param traversable whether or not this Tile is traversable
     */
    public Tile(int startX, int startY, boolean traversable){
        this(startX, startY, traversable, 0.0f);
    }

    /**
     * Creates a new instance of Tile.
     * @param startX the horizontal component marking the position of the Tile
     * @param startY the vertical component marking the position of the Tile
     */
    public Tile(int startX, int startY){
        this(startX, startY, true);
    }

    @Override
    public boolean isTraversable() {
        return traversable;
    }

    @Override
    public float getResistance() {
        return resistance;
    }

    /**
     * Sets the Color associated with this Tile.
     * @param color the Color to set to the Tile
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * Returns the Color associated with this Tile.
     * @return the Color of the Tile
     */
    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(!(obj instanceof Tile))
            return false;
        if(obj == this) return true;
        Tile tile = (Tile) obj;
        return this.getStartX() == tile.getStartX()
                && this.getStartY() == tile.getStartY()
                && this.getCorners().equals(tile.getCorners())
                && this.traversable == tile.traversable
                && this.resistance == tile.resistance
                && this.color == tile.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartX(), getStartY(), getCorners(), traversable, resistance, color);
    }


}
