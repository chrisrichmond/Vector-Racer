package model;

import model.geometry.Grid;
import model.geometry.Point;

import java.util.List;

public class Racetrack {

    private Grid grid;
    private List<Terrain> tiles;

    public Racetrack(String size) {

        switch(size){
            case("small"):
                //grid = new Grid();
                break;
            case("medium"):

                break;
            case("large"):

                break;
                default:

        }
    }
}
