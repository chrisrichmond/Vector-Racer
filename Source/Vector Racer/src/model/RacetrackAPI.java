package model;

public interface RacetrackAPI {

    /**
     * Gets the 2-dimensional grid of tiles representing the track layout
     * @return 2D Tile array of track layout
     */
    public Tile[][] getTiles();

    public boolean addTile(Tile newTile);

    public boolean removeTile(Tile tile);

    public boolean removeTile(int row, int col);

}
