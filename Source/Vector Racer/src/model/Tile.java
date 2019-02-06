package model;

import javafx.scene.paint.Color;
import model.geometry.Square;

public abstract class Tile extends Square implements Terrain {

    private boolean traversable;
    private float resistance;
    private float slide;
    private float chaos;
    private Color color;

    public Tile(int startX, int startY, boolean traversable, float resistance, float slide, float chaos){
        super(startX, startY);
        this.traversable = traversable;
        this.resistance = resistance;
        this.slide = slide;
        this.chaos = chaos;
    }

    public Tile(int startX, int startY, boolean traversable){
        this(startX, startY, traversable, 0.0f, 0.0f, 0.0f);
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

    @Override
    public float getSlide() {
        return slide;
    }

    @Override
    public float getChaos() {
        return chaos;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
