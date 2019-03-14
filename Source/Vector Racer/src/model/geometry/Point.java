package model.geometry;

import model.RacerAPI;
import model.Terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {

    private int x;
    private int y;

    public Point(Point original){
        this.x = original.getX();
        this.y = original.getY();
    }

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

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(!(obj instanceof Point))
            return false;
        if(obj == this) return true;
        return ( (this.x == ((Point) obj).x) && (this.y == ((Point) obj).y) );
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
