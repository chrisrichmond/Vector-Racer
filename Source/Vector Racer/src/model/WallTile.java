package model;

import utilities.VectorConstants;

public class WallTile extends Tile{

    public WallTile(int startX, int startY) {
        super(startX, startY, false);
        setColor(VectorConstants.WALL_COLOR);
    }
}
