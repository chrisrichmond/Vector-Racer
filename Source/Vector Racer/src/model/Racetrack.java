package model;

import model.geometry.Point;
import model.geometry.Vect;
import utilities.VectorConstants;

import java.util.*;

public class Racetrack implements RacetrackAPI{

    private List<Tile> tiles;
    private List<AirTile> airTiles;
    private List<SandTile> sandTiles;
    private List<IceTile> iceTiles;
    private List<WallTile> wallTiles;
    private List<CheckpointTile> checkpointTiles;
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
        checkpointTiles = new ArrayList<>();
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
            System.out.println("should return false: "+tiles.contains(tile));
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
        if(checkpointTiles.contains(tile)){
            checkpointTiles.remove(tile);
        }
        if(!success){
            System.out.println("Couldn't find specified tile");
        }

        System.out.println("Does tiles still contain this?: "+tiles.contains(tile));
        return success;
    }

    @Override
    public boolean removeTile(int row, int col) {
        for(Iterator<Tile> iterator = tiles.iterator(); iterator.hasNext();){
            Tile currentTile = iterator.next();
            if( (row == currentTile.getStartY()) && (col == currentTile.getStartX()) ){
                return removeTile(currentTile);
            }
        }

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
    public boolean addCheckpointTile(CheckpointTile newTile) {
        if(addTile(newTile)){
            checkpointTiles.add(newTile);
            return true;
        }else if(removeTile(newTile.getStartY(), newTile.getStartX())){
            System.out.println("newTile.getStartY(): "+newTile.getStartY()+" newTile.getStartX(): "+newTile.getStartX());
            System.out.println("REMOVING AND ADDING AGAIN--------------------------------------------");
            System.out.println("before trying to add tile again");
            addTile(newTile);
            System.out.println("after trying to add tile again");
            checkpointTiles.add(newTile);
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
    public Set<Terrain> getTerrainBetween(Point start, Point end) {
        // todo must do validation checking before calling this function to ensure Points are within racetrack bounds
        Vect line = new Vect(start, end);
        System.out.println("m = "+line.getGradient());
        Set<Terrain> terrain = new HashSet<>();
        if(start.getX() > end.getX()){
            // ensures start point is always on the left of end or inline
            Point temp = start;
            start = end;
            end = temp;
        }

        if(!(start.equals(end))) {

            for (int x = start.getX() + 1; x <= end.getX(); x++) {
                // looping through all values of x after start

                if(start.getY() == end.getY()){
                    // horizontal gradient

                    int y = start.getY();
                    terrain.add(getTile(y-1, x-1));
                    terrain.add(getTile(y, x-1));
                }else if( (line.getYfromX(x)) - (Math.floor(line.getYfromX(x))) == 0){
                    // intersection is on a vertex before carrying out Math.floor()

                    int y = (int) Math.floor(line.getYfromX(x));
                    if(line.getGradient() > 0){
                        // gradient is positive
                        terrain.add(getTile(y-1, x-1));
                        if(x != end.getX()) {
                            // not on final iteration through loop
                            terrain.add(getTile(y, x));
                        }
                    }else if(line.getGradient() < 0){
                        // gradient is negative
                        terrain.add(getTile(y, x-1));
                        if(x != end.getX()){
                            // not on final iteration through loop
                            terrain.add(getTile(y-1, x));
                        }

                    }
                }else{
                    // intersection is on an edge before carrying out Math.floor()

                    int y = (int) Math.floor(line.getYfromX(x));
                    terrain.add(getTile(y, x-1));
                    if(x != end.getX()) {
                        // not on final iteration through loop
                        terrain.add(getTile(y, x));
                    }
                }



            }

        }

        if(start.getY() > end.getY()){
            // ensures start point is always on the left of end or inline
            Point temp = start;
            start = end;
            end = temp;
        }

        for (int y = start.getY() + 1; y <= end.getY(); y++) {
            // looping through all values of y after start

            if(start.getX() == end.getX()){
                // vertical gradient

                int x = start.getX();
                terrain.add(getTile(y-1, x-1));
                terrain.add(getTile(y-1, x));
            }else if( (line.getXfromY(y)) - (Math.floor(line.getXfromY(y))) == 0){
                // intersection is on a vertex before carrying out Math.floor()

                int x = (int) Math.floor(line.getXfromY(y));
                if(line.getGradient() > 0){
                    // gradient is positive
                    terrain.add(getTile(y-1, x-1));
                    if(y != end.getY()) {
                        // not on final iteration through loop
                        terrain.add(getTile(y, x));
                    }
                }else if(line.getGradient() < 0){
                    // gradient is negative
                    terrain.add(getTile(y-1, x));
                    if(y != end.getY()){
                        // not on final iteration through loop
                        terrain.add(getTile(y, x-1));
                    }
                }
            }else{
                // intersection is on an edge before carrying out Math.floor()

                int x = (int) Math.floor(line.getXfromY(y));
                terrain.add(getTile(y-1, x));
                if(y != end.getY()) {
                    // not on final iteration through loop
                    terrain.add(getTile(y, x));
                }
            }
        }

        for(Terrain currentTerrain: terrain) {
            System.out.println("TERRAIN @ [ROW:"+((Tile)currentTerrain).getStartY()+" COL:"+((Tile)currentTerrain).getStartX()+"]");
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
        if( (row < 0) || (row > rows) || (col < 0) || (col > cols) ) {
            return null;
        }

        for(Tile currentTile: tiles){
            if( (currentTile.getStartY() == row) && (currentTile.getStartX() == col) ) {
                return currentTile;
            }
        }

        System.out.println("end of getTile returning null?");
        return null;
    }
}
