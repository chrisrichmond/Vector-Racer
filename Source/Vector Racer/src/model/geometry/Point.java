package model.geometry;

import model.RacerAPI;
import model.Terrain;

import java.util.ArrayList;
import java.util.List;

public class Point {

    private int x;
    private int y;
    private List<RacerAPI> racers;  // the list of racers currently occupying this space (there may be more than one racer to each space)
    private Terrain terrain;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, List<RacerAPI> racers){
        this.racers = racers;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public List<RacerAPI> getRacers(){
        return racers;
    }

    public Terrain getTerrain() {
        return terrain;
    }
}
