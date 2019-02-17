package model;

import javafx.scene.paint.Color;
import model.geometry.Point;
import model.geometry.Vect;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private String name;
    private RacerAPI racer;
    private boolean finished;
    private Color color;

    public Player(String name, RacerAPI racer, Color color) {
        this.name = name;
        this.racer = racer;
        this.finished = false;
        this.color = color;
    }

    public List<Point> getPossibleNextPoints(){
        return racer.getPossibleNextPoints();
    }

    public RacerAPI getRacer(){
        return racer;
    }

    public void finish(){
        finished = true;
    }

    public Color getColor(){
        return color;
    }

}
