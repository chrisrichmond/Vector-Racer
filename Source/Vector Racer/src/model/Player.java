package model;

import javafx.scene.paint.Color;
import model.geometry.Point;
import model.geometry.Vect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Player {

    private String name;
    private RacerAPI racer;
    private Color color;

    public Player(String name, RacerAPI racer, Color color) {
        this.name = name;
        this.racer = racer;
        this.color = color;
    }

    public List<Point> getPossibleNextPoints(){
        return racer.getPossibleNextPoints();
    }

    public RacerAPI getRacer(){
        return racer;
    }

    public boolean isFinished(){
        return racer.isFinished();
    }

    public int getNumberOfMovesMade(){
        return racer.getPointRoute().size()-1;
    }

    public Color getColor(){
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return name.equals(player.name) &&
                getRacer().equals(player.getRacer()) &&
                getColor().equals(player.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getRacer(), getColor());
    }
}
