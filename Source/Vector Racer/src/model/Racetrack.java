package model;

import utilities.VectorConstants;

import java.util.ArrayList;
import java.util.List;

public class Racetrack implements RacetrackAPI{

    private List<Tile> tiles;
    private List<Tile> airTiles;
    private List<SandTile> sandTiles;
    private List<IceTile> iceTiles;
    private List<WallTile> wallTiles;
    private int rows;
    private int cols;
    private boolean fullyFilled; // ensures all tile positions have been filled

    public Racetrack(int rows, int cols){
        tiles = new ArrayList<>();
        airTiles = new ArrayList<>();
        sandTiles = new ArrayList<>();
        iceTiles = new ArrayList<>();
        wallTiles = new ArrayList<>();
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
        if(wallTiles.contains(tile)){
            wallTiles.remove(tile);
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
        wallTiles.clear();
    }

    @Override
    public boolean addAirTile(AirTile newTile) {
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
    public boolean addWallTile(WallTile newTile) {
        if(addTile(newTile)){
            wallTiles.add(newTile);
            return true;
        }
        return false;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public List<Tile> getTiles() {
        return tiles;
    }
}
