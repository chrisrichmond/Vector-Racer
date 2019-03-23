package model;

import utilities.VectorConstants;

/**
 * Class which represents an ice tile in the game.
 */
public class IceTile extends Tile {

    /**
     * Creates a new instance of IceTile.
     * @param startX the horizontal component marking the position of the IceTile
     * @param startY the vertical component marking the position of the IceTile
     */
    public IceTile(int startX, int startY){
        super(startX, startY, true, -1.0f);
        setColor(VectorConstants.ICE_COLOR);
    }
}
