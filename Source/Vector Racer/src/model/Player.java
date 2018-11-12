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
        int racerXpos = racer.getPosition().getX();
        int racerYpos = racer.getPosition().getY();
        int racerXvelo = racer.getVelocity().getXVelo();
        int racerYvelo = racer.getVelocity().getYVelo();

        /* get the central point out of the 9 possible next points
            o o o
            o x o
            o o o
         */

        int centralX = racerXpos + racerXvelo;
        int centralY = racerYpos + racerYvelo;

        for(int y = (centralY-1); y <= (centralY+1); y++){
            for(int x = (centralX-1); x <= (centralX+1); x++){
                possibleNextPoints.add(new Point(x, y));
            }
        }

        return possibleNextPoints;
    }

}
