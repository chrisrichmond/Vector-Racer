package model.geometry;

import model.RacerAPI;
import model.Terrain;

import java.util.ArrayList;
import java.util.List;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

}
