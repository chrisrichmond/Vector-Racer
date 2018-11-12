package model;

public class Racetrack {

    private Tile[][] tiles;
    private int rowSize;
    private int colSize;
    private boolean fullyFilled; // ensures all tile positions have been filled

    public Racetrack(String size) {

        switch(size){
            case("small"):
                tiles = new Tile[60][40];   //todo probably fiddle with these values til something adequate is found
                break;
            case("medium"):
                tiles = new Tile[80][60];
                break;
            case("large"):
                tiles = new Tile[100][80];
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
