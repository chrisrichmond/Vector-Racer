package model;

import model.geometry.Square;

public abstract class Tile extends Square implements Terrain {

    @Override
    public boolean isTraversable() {
        return false;
    }

    @Override
    public float getResistance() {
        return 0;
    }

    @Override
    public float getSlide() {
        return 0;
    }

    @Override
    public float getChaos() {
        return 0;
    }


}
