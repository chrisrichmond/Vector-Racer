package model;

import utilities.VectorConstants;

import java.util.List;

public class Racetrack implements RacetrackAPI{

    private List<Tile> tiles;
    private List<Tile> airTiles;
    private List<SandTile> sandTiles;
    private List<IceTile> iceTiles;
    private int rows;
    private int cols;
    private boolean fullyFilled; // ensures all tile positions have been filled

    /*
    public Racetrack(String size) {

        switch(size){
            case("small"):
                tiles = new Tile[VectorConstants.SMALL_ROWS][VectorConstants.SMALL_COLS];
                break;
            case("medium"):
                tiles = new Tile[VectorConstants.MEDIUM_ROWS][VectorConstants.MEDIUM_COLS];
                break;
            case("large"):
                tiles = new Tile[VectorConstants.LARGE_ROWS][VectorConstants.LARGE_COLS];
                break;
                default:

        }
    }
    */

    public Racetrack(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public boolean addTile(Tile newTile){
        if( (newTile.getStartX()<0) || (newTile.getStartX()>=cols) || (newTile.getStartY()<0) || (newTile.getStartY()>=rows)){
            System.out.println("Tile to be added was outside Racetrack dimensions");
            return false;
        }

        boolean success = true;

        positionCheck:
        for (Tile currentTile: tiles) {
            if( (newTile.getStartX() == currentTile.getStartX()) && (newTile.getStartY() == currentTile.getStartY()) ){
                System.out.println("Tile already exists");
                success = false;
                break positionCheck;
            }
        }

        if(success){
            tiles.add(newTile);
        }
        return success;
    }

    @Override
    public boolean removeTile(Tile tile) {
        boolean success = false;

        if(tiles.contains(tile)){
            tiles.remove(tile);
            success = true;
        }
        if(airTiles.contains(tile)){
            airTiles.remove(tile);
        }
        if(sandTiles.contains(tile)){
            sandTiles.remove(tile);
        }
        if(iceTiles.contains(tile)){
            iceTiles.remove(tile);
        }
        if(!success){
            System.out.println("Couldn't find specified tile");
        }
        return success;
    }

    @Override
    public boolean removeTile(int row, int col) {
        return false;
    }

    @Override
    public void clear() {
        tiles.clear();
        airTiles.clear();
        sandTiles.clear();
        iceTiles.clear();
    }

    @Override
    public boolean addAirTile(Tile newTile) {
        if(addTile(newTile)){
            airTiles.add(newTile);
            return true;
        }
        return false;
    }

    @Override
    public boolean addSandTile(SandTile newTile) {
        if(addTile(newTile)){
            sandTiles.add(newTile);
            return true;
        }
        return false;
    }

    @Override
    public boolean addIceTile(IceTile newTile) {
        if(addTile(newTile)){
            iceTiles.add(newTile);
            return true;
        }
        return false;
    }

    @Override
    public List<Tile> getTiles() {
        return tiles;
    }
}
