package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.*;

public class Racer implements RacerAPI{

    private Vect velocity;
    private boolean finished;
    private Deque<Point> pointRoute;

    public Racer(Point startPosition){
        this.velocity = new Vect(startPosition, startPosition);
        this.finished = false;
        pointRoute = new ArrayDeque<>();
        pointRoute.push(startPosition);
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
    public Point moveWhilstApplyingEffects(RacetrackAPI racetrack, Point destinationBeforeEffects) {
        Point currentPosition = pointRoute.peek();
        Point destinationAfterEffects = destinationBeforeEffects;
        Set<Terrain> terrainTraversed = racetrack.getTerrainBetween(currentPosition, destinationBeforeEffects);

        float totalResistance = 0.0f;
        for(Terrain currentTerrain: terrainTraversed){
            if(!currentTerrain.isTraversable()){
                // cannot move through non-traversable terrain, return position as the same
                return currentPosition;
            }else{
                totalResistance = totalResistance + currentTerrain.getResistance();
            }
        }



        return destinationAfterEffects;
    }
}
