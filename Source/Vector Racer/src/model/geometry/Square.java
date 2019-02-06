package model.geometry;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Square {

    private Point start;
    private Point end;
    private List<Point> corners;

    public Square(Point start){
        this.start = start;
        this.end = new Point(start.getX()+1, start.getY()+1);       //todo need to check which way axes go etc, all this kind of shit might not work properly in JavaFX, used to Swing directions

        corners = new ArrayList<>();
        corners.add(start);
        corners.add(new Point(start.getX()+1, start.getY()));
        corners.add(new Point(start.getX(), start.getY()+1));
        corners.add(end);
    }

    public Square(int startX, int startY){
        this.start = new Point(startX, startY);
        this.end = new Point(startX+1, startY+1);

        corners = new ArrayList<>();
        corners.add(start);
        corners.add(new Point(start.getX()+1, start.getY()));
        corners.add(new Point(start.getX(), start.getY()+1));
        corners.add(end);
    }

    public int getStartX(){
        return start.getX();
    }

    public int getStartY(){
        return start.getY();
    }

    public List<Point> getCorners(){
        return corners;
    }

}
