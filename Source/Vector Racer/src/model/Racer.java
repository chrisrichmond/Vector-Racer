package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
        List<Tile> tilesTraversed = new ArrayList<>();

//        if(currentPosition.getY() <= destinationBeforeEffects.getY()){
//            // the current row is less than the destination row or equal to it
//            if(currentPosition.getX() <= ) {
//
//
//                for (int row = currentPosition.getY(); row < destinationBeforeEffects.getY(); row++) {
//                    for (int col = currentPosition.getX(); col < destinationBeforeEffects.getY(); row++)
//                }
//            }
//        }else{
//            // the current row is greater than the destination row
//            for(int row())
//
//        }
//
//        racetrack.getTile()
//        tilesTraversed

        return destinationAfterEffects;
    }
}
