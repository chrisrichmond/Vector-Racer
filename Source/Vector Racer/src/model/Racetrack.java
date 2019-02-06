package model;

import utilities.VectorConstants;

import java.util.List;

public class Racetrack implements RacetrackAPI{

    private List<Tile> tiles;
    private List<Tile> airTiles;
    private List<Tile> sandTiles;
    private List<Tile> iceTiles;
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
        boolean success = true;

        positionCheck:
        for (Tile currentTile: tiles) {
            if( (newTile.getStartX() == currentTile.getStartX()) && (newTile.getStartY() == currentTile.getStartY()) ){
                success = false;
                break positionCheck;
            }
        }

        if(success){
            tiles.add(newTile);
        }
    }

    /*
    @Override
    public boolean addTile(Tile newTile){       // todo check all this methods logic again if any problems are encountered
        boolean success = true;

        positionCheck:
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if((tiles[row][col].getStartX() == newTile.getStartX())&&(tiles[row][col].getStartY() == newTile.getStartY())){
                    success = false;
                    break positionCheck;
                }
            }
        }

        if(success){
            tiles[newTile.getStartY()][newTile.getStartX()] = newTile;
        }

        return success;
    }
    */

    @Override
    public boolean removeTile(Tile tile) {
        return false;
    }

    @Override
    public boolean removeTile(int row, int col) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public List<Tile> getTiles() {
        return tiles;
    }
}
