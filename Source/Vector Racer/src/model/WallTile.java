package model;

import utilities.VectorConstants;

/**
 * Class which represents a wall tile in the game.
 */
public class WallTile extends Tile{

    /**
     * Creates a new instance of WallTile.
     * @param startX the horizontal component marking the position of the WallTile
     * @param startY the vertical component marking the position of the WallTile
     */
    public WallTile(int startX, int startY) {
        super(startX, startY, false);
        setColor(VectorConstants.WALL_COLOR);
    }
}
