package model;

import utilities.Observable;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Interface representing main model component of the Vector Racer system, it is the game data and logic.
 * Implementing classes should handle all functionality and data structures for running the game.
 */
public interface ModelAPI extends Observable {

    /**
     * Used to load a specified file into this ModelAPI and define key data.
     * Must be called before any other mutator in this class (and certain accessors will be undefined until called.
     * @param filename the file to load from
     * @param player1name the name of the first player
     * @param player2name the name of the second player
     * @param player2ai whether or not the second player is an AI
     * @throws FileNotFoundException upon an un invalid filepath being passed
     */
    void setup(File filename, String player1name, String player2name, boolean player2ai) throws FileNotFoundException;

    /**
     * Should be called after setup() and before any other mutators, in order to start the game loop.
     */
    void start();

    /**
     * Returns the RacetrackAPI associated with this ModelAPI.
     * @return the RacetrackAPI associated with this ModelAPI
     */
    RacetrackAPI getRacetrack();

    /**
     * Returns the current State associated with this ModelAPI.
     * @return the current State
     */
    State getCurrentState();

    /**
     * Loads a file from a specified filepath into the model.
     * @param filename the filepath
     * @throws FileNotFoundException upon an invalid filepath being passed
     */
    void loadFile(File filename) throws FileNotFoundException;

    /**
     * Creates a new racetrack in the model with the specified dimensions and spawn location for players' vehicles.
     * @param rows the number of rows high that the RacetrackAPI should be
     * @param cols the number of columns wide that the RacetrackAPI should be
     * @param startPosRow the row on which players' vehicles should spawn
     * @param startPosCol the column on which players' vehicles should spawn
     * @param finalZone the last zone that vehicles should be in before they can finish the race by passing into a goal
     */
    void createEmptyRacetrack(int rows, int cols, int startPosRow, int startPosCol, int finalZone);

    /**
     * Attempts to add an AirTile to this ModelAPI's RacetrackAPI.
     * @param row the row on which to add this AirTile
     * @param col the column on which to add this AirTile
     */
    void addAirTile(int row, int col);

    /**
     * Attempts to add an SandTile to this ModelAPI's RacetrackAPI.
     * @param row the row on which to add this SandTile
     * @param col the column on which to add this SandTile
     */
    void addSandTile(int row, int col);

    /**
     * Attempts to add an IceTile to this ModelAPI's RacetrackAPI.
     * @param row the row on which to add this IceTile
     * @param col the column on which to add this IceTile
     */
    void addIceTile(int row, int col);

    /**
     * Attempts to add an WallTile to this ModelAPI's RacetrackAPI.
     * @param row the row on which to add this WallTile
     * @param col the column on which to add this WallTile
     */
    void addWallTile(int row, int col);

    /**
     * Attempts to add an CheckpointTile to this ModelAPI's RacetrackAPI.
     * @param row the row on which to add this CheckpointTile
     * @param col the column on which to add this CheckpointTile
     * @param zoneNumber the zone number which should be represented by this CheckpointTile
     */
    void addCheckpointTile(int row, int col, int zoneNumber);

    /**
     * Attempts to fill all empty spaces within this ModelAPI's RacetrackAPI with all of a specified Tile type.
     * @param tileType the type of Tile to fill with
     */
    void fillRemainderWith(String tileType);

    /**
     * Used for passing in grid input to the ModelAPI and handling what should happen upon input at the specified located being received.
     * @param row the grid row input
     * @param col the grid column input
     */
    void gridPointInput(double row, double col);

    /**
     * The position at which players' vehicles should spawn on this ModelAPI's RacetrackAPI.
     * @param row the row on which players' vehicles should spawn
     * @param col the column on which players' vehicles should spawn
     */
    void setStartPosition(int row, int col);

    /**
     * Returns the winning PlayerAPI of the game if one has been set.
     * @return the winning PlayerAPI of the game, null if not set
     */
    PlayerAPI getWinner();

    /**
     * Returns whether or not the second PlayerAPI was initialised as an AI player or not.
     * @return true if AI, false if not
     */
    boolean isPlayer2Ai();

}
