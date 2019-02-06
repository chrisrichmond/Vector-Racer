package model;

import java.util.List;

public interface RacetrackAPI {

    /**
     * Gets the 2-dimensional grid of tiles representing the track layout
     * @return 2D Tile array of track layout
     */
    List<Tile> getTiles();

    /**
     * Adds a new tile to the Racetrack's list as long as its position is not already occupied
     * @param newTile the new Tile object to be added to the list
     * @return true if successful, false if not
     */
    boolean addTile(Tile newTile);

    /**
     * Removes a tile from the Racetrack's list if it is currently contained within it
     * @param tile the Tile object to be removed from the list
     * @return true if successful, false if not
     */
    boolean removeTile(Tile tile);

    /**
     * Removes a tile from the Racetrack's list if one exists at the given row and column position
     * @param row the row that the Tile to be removed should be found at
     * @param col the column that the Tile to be removed should be found at
     * @return true if successful, false if not
     */
    boolean removeTile(int row, int col);

    /**
     * Clears all Racetrack components lists, i.e. removes all Tiles
     */
    void clear();

    boolean addAirTile(Tile newTile);

    boolean addSandTile(Tile newTile);

    boolean addIceTile(Tile newTile);

}
