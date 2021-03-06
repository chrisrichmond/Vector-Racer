package model;

import model.geometry.Point;
import model.geometry.Vect;
import java.util.*;

/**
 * Class representing a racetrack in the game.
 */
public class Racetrack implements RacetrackAPI{

    /**
     * The List of all Tiles in this Racetrack.
     */
    private List<Tile> tiles;

    /**
     * The List of all AirTiles in this Racetrack.
     */
    private List<AirTile> airTiles;

    /**
     * The List of all SandTiles in this Racetrack.
     */
    private List<SandTile> sandTiles;

    /**
     * The List of all IceTiles in this Racetrack.
     */
    private List<IceTile> iceTiles;

    /**
     * The List of all WallTiles in this Racetrack.
     */
    private List<WallTile> wallTiles;

    /**
     * The List of all CheckpointTiles in this Racetrack.
     */
    private List<CheckpointTile> checkpointTiles;

    /**
     * The number of rows in this Racetrack.
     */
    private int rows;

    /**
     * The number of colummns in this Racetrack.
     */
    private int cols;

    /**
     * The Point representing the position on this Racetrack where Racers will spawn.
     */
    private Point startPosition;

    /**
     * The value of the last zone that a Racer must be in before they can finish the race.
     */
    private int finalZone;

    /**
     * The name of this Racetrack.
     */
    private String name;

    /**
     * Copy constructor - creates a new instance of Racetrack.
     * @param original the Racetrack to deep copy
     */
    public Racetrack(RacetrackAPI original){

        this.tiles = new ArrayList<>(original.getTiles());
        this.airTiles = new ArrayList<>(original.getAirTiles());
        this.sandTiles = new ArrayList<>(original.getSandTiles());
        this.iceTiles = new ArrayList<>(original.getIceTiles());
        this.wallTiles = new ArrayList<>(original.getWallTiles());
        this.checkpointTiles = new ArrayList<>(original.getCheckpointTiles());

        this.rows = original.getRows();
        this.cols = original.getCols();
        this.startPosition = new Point(original.getStartPosition());
        this.finalZone = original.getFinalZone();
        this.name = original.getName();
    }

    /**
     * Creates a new instance of Racetrack.
     * @param rows the number of rows
     * @param cols the number of columns
     * @param startPosition the position Racers will spawn at
     * @param finalZone the last zone before the finish
     */
    public Racetrack(int rows, int cols, Point startPosition, int finalZone){
        tiles = new ArrayList<>();
        airTiles = new ArrayList<>();
        sandTiles = new ArrayList<>();
        iceTiles = new ArrayList<>();
        wallTiles = new ArrayList<>();
        checkpointTiles = new ArrayList<>();
        this.rows = rows;
        this.cols = cols;
        this.startPosition = startPosition;
        this.finalZone = finalZone;
        this.name = name;
    }

    @Override
    public boolean addTile(Tile newTile){
        if( (newTile.getStartX()<0) || (newTile.getStartX()>=cols) || (newTile.getStartY()<0) || (newTile.getStartY()>=rows)){
            return false;
        }

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
        if(checkpointTiles.contains(tile)){
            checkpointTiles.remove(tile);
        }
        if(!success){
            System.out.println("Couldn't find specified tile");
        }

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
            addTile(newTile);
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
    public List<CheckpointTile> getCheckpointTiles() {
        return checkpointTiles;
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
        Vect line = new Vect(start, end);
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

        return terrain;
    }

    @Override
    public int getFinalZone() {
        return finalZone;
    }

    @Override
    public boolean isVertexTraversable(Point vertex) {
        int row = vertex.getY();
        int col = vertex.getX();
        try {
            Tile topLeft = getTile(row-1, col-1);
            Tile topRight = getTile(row-1, col);
            Tile bottomLeft = getTile(row, col-1);
            Tile bottomRight = getTile(row, col);

            if(!topLeft.isTraversable())
                return false;
            if(!topRight.isTraversable())
                return false;
            if(!bottomLeft.isTraversable())
                return false;
            if(!bottomRight.isTraversable())
                return false;
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

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

        return null;
    }
}
