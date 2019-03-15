package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.*;

public class Racer implements RacerAPI{

    private Vect velocity;
    private boolean finished;
    private Deque<Point> pointRoute;
    private int currentZone;

    public Racer(RacerAPI original){
        this.velocity = new Vect(original.getVelocity());
        this.finished = original.isFinished();
        this.pointRoute = new ArrayDeque<>(original.getPointRoute());
    }

    public Racer(Point startPosition){
        this.velocity = new Vect(startPosition, startPosition);
        this.finished = false;
        pointRoute = new ArrayDeque<>();
        pointRoute.push(startPosition);
        this.currentZone = 0;
    }

    @Override
    public Vect getVelocity() {
        return velocity;
    }

    @Override
    public boolean isFinished() {
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

        for(int y = (centralY-1); y <= (centralY+1); y++){
            for(int x = (centralX-1); x <= (centralX+1); x++){
                if(racetrack.isVertexTraversable(new Point(x, y))) {
                    possibleNextPoints.add(new Point(x, y));
                }
            }
        }
        if(possibleNextPoints.size() == 0){
//            if(centralX <= 0){
//                centralX = 1;
//            }else if(centralX > racetrack.getCols()-1){
//                centralX = racetrack.getCols()-2;
//            }
//            if(centralY <= 0){
//                centralY = 1;
//            }else if(centralY > racetrack.getCols()-1){
//                centralY = racetrack.getCols()-2;
//            }
//            possibleNextPoints.add(new Point(centralX, centralY));

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
                        // todo setPosition(new Point( ((Tile)currentTerrain).getStartX(), ((Tile)currentTerrain).getStartY()));
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

        setPosition(destinationAfterEffects);
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
