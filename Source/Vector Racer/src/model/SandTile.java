package model;

import utilities.VectorConstants;

/**
 * Class which represents a sand tile in the game.
 */
public class SandTile extends Tile {

    /**
     * Creates a new instance of SandTile.
     * @param startX the horizontal component marking the position of the SandTile
     * @param startY the vertical component marking the position of the SandTile
     */
    public SandTile(int startX, int startY){
        super(startX, startY, true, 1.0f);
        setColor(VectorConstants.SAND_COLOR);
    }
}
