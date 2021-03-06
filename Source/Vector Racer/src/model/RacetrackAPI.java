package model;

import model.geometry.Point;

import java.util.List;
import java.util.Set;

/**
 * Interface representing a racetrack in the game.
 */
public interface RacetrackAPI {

    /**
     * Gets the 2-dimensional grid of tiles representing the track layout.
     * @return 2D Tile array of track layout
     */
    List<Tile> getTiles();

    Tile getTile(int row, int col);

    /**
     * Adds a new tile to the Racetrack's list as long as its position is not already occupied.
     * @param newTile the new Tile object to be added to the list
     * @return true if successful, false if not
     */
    boolean addTile(Tile newTile);

    /**
     * Removes a tile from the Racetrack's list if it is currently contained within it.
     * @param tile the Tile object to be removed from the list
     * @return true if successful, false if not
     */
    boolean removeTile(Tile tile);

    /**
     * Removes a tile from the Racetrack's list if one exists at the given row and column position.
     * @param row the row that the Tile to be removed should be found at
     * @param col the column that the Tile to be removed should be found at
     * @return true if successful, false if not
     */
    boolean removeTile(int row, int col);

    /**
     * Clears all Racetrack components lists, i.e. removes all Tiles.
     */
    void clear();

    /**
     * Adds a new air tile to the racetrack data structure if a tile doesn't already exist in this position.
     * @param newTile
     * @return true if the tile was added, false if not
     */
    boolean addAirTile(AirTile newTile);

    /**
     * Adds a new sand tile to the racetrack data structure if a tile doesn't already exist in this position.
     * @param newTile
     * @return true if the tile was added, false if not
     */
    boolean addSandTile(SandTile newTile);

    /**
     * Adds a new ice tile to the racetrack data structure if a tile doesn't already exist in this position.
     * @param newTile
     * @return true if the tile was added, false if not
     */
    boolean addIceTile(IceTile newTile);

    /**
     * Adds a new wall tile to the racetrack data structure if a tile doesn't already exist in this position.
     * @param newTile
     * @return true if the tile was added, false if not
     */
    boolean addWallTile(WallTile newTile);

    /**
     * Adds a new checkpoint tile to the racetrack data structure if a tile doesn't already exist in this position.
     * @param newTile
     * @return true if the tile was added, false if not
     */
    boolean addCheckpointTile(CheckpointTile newTile);

    /**
     * Gets all the air tiles held by this Racetrack's data structure.
     * @return a list of air tiles
     */
    List<AirTile> getAirTiles();

    /**
     * Gets all the sand tiles held by this Racetrack's data structure.
     * @return a list of sand tiles
     */
    List<SandTile> getSandTiles();

    /**
     * Gets all the ice tiles held by this Racetrack's data structure.
     * @return a list of ice tiles
     */
    List<IceTile> getIceTiles();

    /**
     * Gets all the wall tiles held by this Racetrack's data structure.
     * @return a list of wall tiles
     */
    List<WallTile> getWallTiles();

    /**
     * Gets all the checkpoint tiles held by this Racetrack's data structure.
     * @return
     */
    List<CheckpointTile> getCheckpointTiles();

    /**
     * Gets the number of rows this Racetrack contains.
     * @return the integer rows attribute of this Racetrack
     */
    int getRows();

    /**
     * Gets the number of columns this Racetrack contains.
     * @return the integer cols attribute of this Racetrack
     */
    int getCols();

    /**
     * Gets the position that Racers will spawn at on this Racetrack.
     * @return the start Point in cartesian space
     */
    Point getStartPosition();

    /**
     * Sets the position that Racers will spawn at on this Racetrack.
     * @param startPostition the start Point in cartesian space
     */
    void setStartPosition(Point startPostition);

    /**
     * Returns whether or not the specifed RacerAPI is touching a vertex belonging to a WallTile.
     * @param racer
     * @return true if touching, false if not
     */
    boolean isTouchingWall(RacerAPI racer);

    /**
     * Evaluates the Set of Terrain which the line intersects.
     * @param start the Point to start the line from
     * @param end the Point to end the line at
     * @return the Set of all Terrain intersected between these point, null if no Terrain crossed
     */
    Set<Terrain> getTerrainBetween(Point start, Point end);

    /**
     * Returns the final zone of this RacetrackAPI.
     * @return the final zone
     */
    int getFinalZone();

    /**
     * Returns whether or not a Point on this RacetrackAPI is a member of any non-traversable Terrain.
     * @param vertex the corner Point to test
     * @return true if traversable, false if not
     */
    boolean isVertexTraversable(Point vertex);

    /**
     * Returns the name of this RacetrackAPI.
     * @return the name of this RacetrackAPI
     */
    String getName();

    /**
     * Sets the name of this RacetrackAPI.
     * @param name the name to assign to this RacetrackAPI
     */
    void setName(String name);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
