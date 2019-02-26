package model;

import utilities.VectorConstants;

public class IceTile extends Tile {

    public IceTile(int startX, int startY){
        super(startX, startY, true, -1.0f);
        setColor(VectorConstants.ICE_COLOR);
    }
}
