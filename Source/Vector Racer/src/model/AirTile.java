package model;

import utilities.VectorConstants;

/**
 * Class which represents an air tile in the game.
 */
public class AirTile extends Tile {

    /**
     * Creates a new instance of AirTile.
     * @param startX the horizontal component marking the position of the AirTile
     * @param startY the vertical component marking the position of the AirTile
     */
    public AirTile(int startX, int startY) {
        super(startX, startY, true, 0.0f);
        setColor(VectorConstants.AIR_COLOR);
    }
}
