package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected String name;
    protected RacerAPI racer;

    public Player(String name, RacerAPI racer) {
        this.name = name;
        this.racer = racer;
    }

    public List<Point> getPossibleNextPoints(){
        List<Point> possibleNextPoints = new ArrayList<>();

        possibleNextPoints.add(racer.getPosition()

        return possibleNextPoints;
    }

}
