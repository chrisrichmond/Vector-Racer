package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.*;

public class Racer implements RacerAPI{

    private Vect velocity;
    private boolean finished;
    private Deque<Point> pointRoute;
    private int currentZone;
    private int crashCount;

    public Racer(RacerAPI original){
        this.velocity = new Vect(original.getVelocity());
        this.finished = original.isFinished();
        this.pointRoute = new LinkedList<>();
        for(Point point: original.getPointRoute()) {    // fixme added this for deep cloning(?)
            this.pointRoute.add(new Point(point));
        }
        this.currentZone = original.getCurrentZone();
        this.crashCount = original.getCrashCount();
    }

    public Racer(Point startPosition){
        this.velocity = new Vect(startPosition, startPosition);
        this.finished = false;
        pointRoute = new LinkedList<>();
        pointRoute.push(startPosition);
        this.currentZone = 0;
        this.crashCount = 0;
    }

    @Override
    public Vect getVelocity() {
        return velocity;
    }

    @Override
    public boolean isFinished() {
//        System.out.println("finished in Racer: "+finished);
        return finished;
    }

    @Override
    public Point getPosition() {
        return pointRoute.peek();
    }

    @Override
    public void setPosition(Point position) {
        velocity = new Vect(pointRoute.peek(), position);
        pointRoute.push(position);
    }

    @Override
    public void killVelocity(){
        velocity = new Vect(getPosition(), getPosition());
    }

    @Override
    public int getCrashCount() {
        return crashCount;
    }

    @Override
    public int getScore() {
        return pointRoute.size()+crashCount;
    }

    @Override
    public Deque<Point> getPointRoute() {
        return pointRoute;
    }

    @Override
    public Point getNextCentralPoint(){

        /* get the central point out of the 9 possible next points
            o o o
            o o o
            o o o
         */

        return new Point(getPosition().getX() + getVelocity().getXVelo(), getPosition().getY() + getVelocity().getYVelo());
    }

    @Override
    public List<Point> getPossibleNextPoints(RacetrackAPI racetrack) {
        List<Point> possibleNextPoints = new ArrayList<>();

        int centralX = getNextCentralPoint().getX();
        int centralY = getNextCentralPoint().getY();

//        boolean traversablePath = true; // fixme
        for(int y = (centralY-1); y <= (centralY+1); y++){
            for(int x = (centralX-1); x <= (centralX+1); x++){
                if(racetrack.isVertexTraversable(new Point(x, y))) {
//      fixme              Set<Terrain> terrainBetween = racetrack.getTerrainBetween(this.getPosition(), new Point(x,y));
//                    for(Terrain currentTerrain: terrainBetween){
//                        if(!currentTerrain.isTraversable()){
//                            traversablePath = false;
//                        }
//                    }
//                    if(traversablePath) {
                        possibleNextPoints.add(new Point(x, y));
//                    }
                }
            }
        }
        if(possibleNextPoints.size() == 0){
            crashCount++;
            possibleNextPoints.add(getPosition());
            killVelocity();
        }

        return possibleNextPoints;
    }

    @Override
    public List<Point> getImpossibleNextPoints(RacetrackAPI racetrack){
        List<Point> impossibleNextPoints = new ArrayList<>();

        int centralX = getNextCentralPoint().getX();
        int centralY = getNextCentralPoint().getY();

        for(int y = (centralY-1); y <= (centralY+1); y++){
            for(int x = (centralX-1); x <= (centralX+1); x++){
                Point currentPoint = new Point(x, y);
                if(!getPossibleNextPoints(racetrack).contains(currentPoint)) {
                    impossibleNextPoints.add(new Point(x, y));
                }
            }
        }

        return impossibleNextPoints;
    }

    @Override
    public void moveWhilstApplyingEffects(RacetrackAPI racetrack, Point destinationBeforeEffects) {
        Point currentPosition = pointRoute.peek();
        Point destinationAfterEffects = destinationBeforeEffects;
        Set<Terrain> terrainTraversed = racetrack.getTerrainBetween(currentPosition, destinationBeforeEffects);

        float totalResistance = 0.0f;
        if(terrainTraversed != null) {
            for (Terrain currentTerrain : terrainTraversed) {
                if (!currentTerrain.isTraversable()) {
                    // cannot move through non-traversable terrain, return position as the same
                    setPosition(currentPosition);
                    killVelocity();
                    return;
                } else {
                    totalResistance = totalResistance + currentTerrain.getResistance();
                }
                if (currentTerrain instanceof CheckpointTile) {
                    if (((CheckpointTile) currentTerrain).getZoneNumber() == (currentZone + 1)) {
                        nextZone();
                    } else if (((((CheckpointTile) currentTerrain).getZoneNumber() == 0) && (currentZone == racetrack.getFinalZone()))) {
                        finished = true;
                        System.out.println("PLAYER FINISHED (in Racer)");
                        System.out.println(this);
                        killVelocity();
                        return;
                    }
                }
            }
        }

        // todo set destinationAfterEffects based on velocity with applied resistance

        Vect tempVelo = new Vect(currentPosition, destinationBeforeEffects);
        int xVelo = tempVelo.getXVelo();
        int yVelo = tempVelo.getYVelo();
        if( (tempVelo.getXVelo() > 1) || tempVelo.getXVelo() < -1) {
            xVelo = tempVelo.getXVelo() - (int) totalResistance;
        }
        if( (tempVelo.getYVelo() > 1) || tempVelo.getYVelo() < -1) {
            yVelo = tempVelo.getYVelo() - (int) totalResistance;
        }
        destinationAfterEffects = new Point(currentPosition.getX()+xVelo, currentPosition.getY()+yVelo);

//        setPosition(destinationAfterEffects); //fixme
        setPosition(destinationBeforeEffects);
    }

    @Override
    public int getCurrentZone() {
        return currentZone;
    }

    @Override
    public void nextZone() {
        currentZone = currentZone + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Racer)) return false;
        Racer racer = (Racer) o;
        return isFinished() == racer.isFinished() &&
                getCurrentZone() == racer.getCurrentZone() &&
                Objects.equals(getVelocity(), racer.getVelocity()) &&
                Objects.equals(getPointRoute(), racer.getPointRoute());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVelocity(), isFinished(), getPointRoute(), getCurrentZone());
    }
}
