package model.geometry;

import java.awt.geom.Rectangle2D;

public class Square {

    private Point start;
    private Point end;

    public Square(){
        start = null;
        end = null;
    }

    public Square(Point start){
        this.start = start;
        this.end = new Point(start.getX()+1, start.getY()-1);       //todo need to check which way axes go etc, all this kind of shit might not work properly in JavaFX, used to Swing directions
    }

    public Square(int startX, int startY){
        this.start = new Point(startX, startY);
        this.end = new Point(startX+1, startY-1);
    }



}
