package model;

import utilities.VectorConstants;

public class Racetrack {

    private Tile[][] tiles;
    private int rowSize;
    private int colSize;
    private boolean fullyFilled; // ensures all tile positions have been filled

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

    public boolean addTile(Tile newTile){       // todo check all this methods logic again if any problems are encountered
        boolean success = true;

        positionCheck:
        for(int row = 0; row < rowSize; row++){
            for(int col = 0; col < colSize; col++){
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

    public Tile[][] getTiles() {
        return tiles;
    }
}
