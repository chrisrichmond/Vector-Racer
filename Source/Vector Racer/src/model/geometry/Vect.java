package model.geometry;

public class Vect {

    private Point start;
    private Point end;

    public Vect(int startX, int startY, int endX, int endY){
        this.start = new Point(startX, startY);
        this.end = new Point(endX, endY);
    }

    public Vect(Point start, Point end){
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public int getXVelo(){
        return end.getX()-start.getX();
    }

    public int getYVelo(){
        return end.getY()-start.getY();
    }
}
