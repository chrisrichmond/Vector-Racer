package model;

import model.geometry.Point;
import model.geometry.Vect;

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
}
