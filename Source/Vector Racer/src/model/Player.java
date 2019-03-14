package model;

import javafx.scene.paint.Color;
import model.geometry.Point;
import model.geometry.Vect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player implements PlayerAPI{

    protected String name;
    protected Racer racer;
    protected Color color;
    protected boolean ai;

    public Player(PlayerAPI original){
        this.name = new String(original.getName());
        this.racer = new Racer(original.getRacer());
        this.color = original.getColor();
        this.ai = original.isAI();
    }

    public Player(String name, RacerAPI racer, Color color, boolean ai) {
        this.name = name;
        this.racer = (Racer) racer;
        this.color = color;
        this.ai = ai;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public RacerAPI getRacer(){
        return racer;
    }

    @Override
    public boolean isFinished(){
        return racer.isFinished();
    }

    @Override
    public int getNumberOfMovesMade(){
        return racer.getPointRoute().size()-1;
    }

    @Override
    public Color getColor(){
        return color;
    }

    @Override
    public boolean isAI() {
        return ai;
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
