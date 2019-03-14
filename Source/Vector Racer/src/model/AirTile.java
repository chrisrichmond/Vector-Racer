package model;

import utilities.VectorConstants;

public class AirTile extends Tile {



    public AirTile(int startX, int startY) {
        super(startX, startY, true, 0.0f);
        setColor(VectorConstants.AIR_COLOR);
    }

    
}
