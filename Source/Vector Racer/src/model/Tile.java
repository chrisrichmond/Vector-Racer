package model;

import javafx.scene.paint.Color;
import model.geometry.Square;

import java.util.Objects;

public abstract class Tile extends Square implements Terrain {

    private boolean traversable;
    private float resistance;
    private Color color;

    public Tile(int startX, int startY, boolean traversable, float resistance){
        super(startX, startY);
        this.traversable = traversable;
        this.resistance = resistance;
    }

    public Tile(int startX, int startY, boolean traversable){
        this(startX, startY, traversable, 0.0f);
    }

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

    public void setColor(Color color){
        this.color = color;
    }

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
