package model.geometry;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Square {

    private Point start;
    private Point end;
    private List<Point> corners;

    public Square(Square original){
        this.start = new Point(original.getStart());
        this.end = new Point(original.getEnd());
        this.corners = new ArrayList<Point>(original.getCorners());
    }

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

    public Point getStart(){
        return start;
    }

    public Point getEnd(){
        return end;
    }

    public List<Point> getCorners(){
        return corners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square square = (Square) o;
        return start.equals(square.start) &&
                end.equals(square.end) &&
                getCorners().equals(square.getCorners());
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, getCorners());
    }
}
