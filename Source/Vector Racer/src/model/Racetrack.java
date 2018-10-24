package model;

import model.geometry.Grid;
import model.geometry.Point;

import java.util.List;

public class Racetrack {

    private Grid grid;
    private Terrain[][] tiles;

    public Racetrack(String size) {

        switch(size){
            case("small"):
                grid = new Grid(new Point(0,0), new Point(60, 40));
                break;
            case("medium"):
                grid = new Grid(new Point(0,0), new Point(100, 75));
                break;
            case("large"):
                grid = new Grid(new Point(0,0), new Point(140, 100));
                break;
                default:

        }
    }

    public Grid getGrid() {
        return grid;
    }

    public Terrain[][] getTiles() {
        return tiles;
    }
}
