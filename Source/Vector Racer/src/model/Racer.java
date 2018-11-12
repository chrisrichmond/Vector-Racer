package model;

import model.geometry.Point;
import model.geometry.Vect;

public class Racer implements RacerAPI{

    private Vect velocity;
    private boolean finished;
    private Point position;

    public Racer(Point startPosition){
        this.position = startPosition;
    }

    @Override
    public Vect getVelocity() {
        return velocity;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Point getPosition() {
        return position;
    }
}
