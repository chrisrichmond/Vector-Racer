package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Racer implements RacerAPI{

    private Vect velocity;
    private boolean finished;
    private Stack<Point> pointRoute;

    public Racer(Point startPosition){
        this.velocity = new Vect(startPosition, startPosition);
        this.finished = false;
        pointRoute = new Stack<>();
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
    public Stack<Point> getPointRoute() {
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

        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("xPos: "+xPos);
        System.out.println("yPos: "+yPos);
        System.out.println("xVelocity: "+xVelocity);
        System.out.println("yVelocity: "+yVelocity);
        System.out.println("centralX: "+centralX);
        System.out.println("centralY: "+centralY);

        for(int y = (centralY-1); y <= (centralY+1); y++){
            for(int x = (centralX-1); x <= (centralX+1); x++){
                possibleNextPoints.add(new Point(x, y));
            }
        }

        return possibleNextPoints;
    }
}
