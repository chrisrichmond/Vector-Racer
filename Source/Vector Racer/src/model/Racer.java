package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.*;

public class Racer implements RacerAPI{

    private Vect velocity;
    private boolean finished;
    private Deque<Point> pointRoute;
    private int currentZone;

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
    public List<Point> getPossibleNextPoints() {
        List<Point> possibleNextPoints = new ArrayList<>();

        int xPos = getPosition().getX();
        int yPos = getPosition().getY();
        int xVelocity = getVelocity().getXVelo();
        int yVelocity = getVelocity().getYVelo();

        /* get the central point out of the 9 possible next points
            o o o
            o o o
            o o o
         */

        int centralX = xPos + xVelocity;
        int centralY = yPos + yVelocity;

        for(int y = (centralY-1); y <= (centralY+1); y++){
            for(int x = (centralX-1); x <= (centralX+1); x++){
                possibleNextPoints.add(new Point(x, y));
            }
        }

        return possibleNextPoints;
    }

    @Override
    public void moveWhilstApplyingEffects(RacetrackAPI racetrack, Point destinationBeforeEffects) {
        Point currentPosition = pointRoute.peek();
        Point destinationAfterEffects = destinationBeforeEffects;
        Set<Terrain> terrainTraversed = racetrack.getTerrainBetween(currentPosition, destinationBeforeEffects);

        float totalResistance = 0.0f;
        for(Terrain currentTerrain: terrainTraversed){
            if(!currentTerrain.isTraversable()){
                // cannot move through non-traversable terrain, return position as the same
                setPosition(currentPosition);
                killVelocity();
                return;
            }else{
                totalResistance = totalResistance + currentTerrain.getResistance();
            }

            if(currentTerrain instanceof CheckpointTile){
                if(((CheckpointTile)currentTerrain).getZoneNumber() == (currentZone+1)){
                    nextZone();
                }else if( ((((CheckpointTile)currentTerrain).getZoneNumber() == 0) && (currentZone > 0))){
                    finished = true;
                    setPosition(new Point( ((Tile)currentTerrain).getStartX(), ((Tile)currentTerrain).getStartY()));
                    killVelocity();
                    return;
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
}
