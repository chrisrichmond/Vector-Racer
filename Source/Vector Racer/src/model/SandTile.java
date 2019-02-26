package model;

import utilities.VectorConstants;

public class SandTile extends Tile {

    public SandTile(int startX, int startY){
        super(startX, startY, true, 1.0f);
        setColor(VectorConstants.SAND_COLOR);
    }

}
