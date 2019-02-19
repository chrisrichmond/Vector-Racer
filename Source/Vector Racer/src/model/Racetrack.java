package model;

import model.geometry.Point;
import model.geometry.Vect;
import utilities.VectorConstants;

import java.util.ArrayList;
import java.util.List;

public class Racetrack implements RacetrackAPI{

    private List<Tile> tiles;
    private List<AirTile> airTiles;
    private List<SandTile> sandTiles;
    private List<IceTile> iceTiles;
    private List<WallTile> wallTiles;
    private int rows;
    private int cols;
    private boolean fullyFilled; // ensures all tile positions have been filled
    private Point startPosition;

    public Racetrack(int rows, int cols, Point startPosition){
        tiles = new ArrayList<>();
        airTiles = new ArrayList<>();
        sandTiles = new ArrayList<>();
        iceTiles = new ArrayList<>();
        wallTiles = new ArrayList<>();
        this.rows = rows;
        this.cols = cols;
        this.startPosition = startPosition;
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
    public List<AirTile> getAirTiles() {
        return airTiles;
    }

    @Override
    public List<SandTile> getSandTiles() {
        return sandTiles;
    }

    @Override
    public List<IceTile> getIceTiles() {
        return iceTiles;
    }

    @Override
    public List<WallTile> getWallTiles() {
        return wallTiles;
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
    public Point getStartPosition() {
        return startPosition;
    }

    @Override
    public void setStartPosition(Point startPosition){
        this.startPosition = startPosition;
    }

    @Override
    public boolean isTouchingWall(RacerAPI racer) {
        List<Point> wallCorners = new ArrayList<>();

        for(WallTile currentWallTile: wallTiles){
            wallCorners.addAll(currentWallTile.getCorners());
        }

        if(wallCorners.contains(racer.getPosition())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Terrain> getTerrainBetween(Point start, Point end) {
        // todo must do validation checking before calling this function to ensure Points are within racetrack bounds
        Vect line = new Vect(start, end);
        List<Terrain> terrain = new ArrayList<>();
        if(start.getX() > end.getX()){
            // ensures start point is always on the left of end or inline
            Point temp = start;
            start = end;
            end = temp;
        }

        // todo may need to check for end point being above start point?

        for(int x = start.getX(); x <= end.getX(); x++){
            double y = Math.floor(line.getYfromX(x));

            // try adding tiles at CARTESIAN coordinates(x, y) and (x-1, y)

            Tile temp = getTile((int)y, x);
            if(!(terrain.contains(temp)))
                terrain.add(temp);
            temp = getTile((int) y, x-1);

            if(!(terrain.contains(temp)))
                terrain.add(temp);

        }
        for(int y = start.getY(); y <= end.getY(); y++){
            double x = Math.floor(line.getXfromY(y));

            // try adding tiles at CARTESIAN coordinates (x, y) and (x, y-1)

            Tile temp = getTile(y, (int) x);
            if(!(terrain.contains(temp)))
                terrain.add(temp);
            temp = getTile(y-1, (int) x);
            if(!(terrain.contains(temp)))
                terrain.add(temp);

        }

        for(Terrain currentTerrain: terrain){
            System.out.println("row: "+((Tile)currentTerrain).getStartY());
            System.out.println("col: "+((Tile)currentTerrain).getStartX());
        }

        return terrain;
    }

//    @Override
//    public boolean isWithinBounds(Point position) {
//
//
//        return false;
//    }

    @Override
    public List<Tile> getTiles() {
        return tiles;
    }

    @Override
    public Tile getTile(int row, int col) {
        if( (row < 0) || (row > rows) || (col < 0) || (col > cols) )
            return null;

        for(Tile currentTile: tiles){
            if( (currentTile.getStartY() == col) && (currentTile.getStartX() == row) ) {
                return currentTile;
            }
        }

        return null;
    }
}
