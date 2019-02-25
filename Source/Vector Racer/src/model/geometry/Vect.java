package model.geometry;

public class Vect {

    protected Point start;
    protected Point end;
    protected double gradient;

    public Vect(int startX, int startY, int endX, int endY){
        this.start = new Point(startX, startY);
        this.end = new Point(endX, endY);
        calcGradient();
    }

    public Vect(Point start, Point end){
        this.start = start;
        this.end = end;
        calcGradient();
    }

    private void calcGradient(){
        try {
            this.gradient = ((double)end.getY() - (double)start.getY()) / ((double)end.getX() - (double)start.getX());
        }catch(Exception e){
            this.gradient = 0;
        }
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

    public double getGradient(){
        return gradient;
    }

    public double getXfromY(double y){
        // x = (y-b)/m + a , when a is any x coordinate on the line and b is its corresponding y coordinate
        double a = start.getX();
        double b = start.getY();
        double m = gradient;
        return ( (y-b)/m + a);
    }

    public double getYfromX(double x){
        // y = m(x-a) + b , when a is any x coordinate on the line and b is its corresponding y coordinate
        double a = start.getX();
        double b = start.getY();
        double m = gradient;
        return ( m*(x-a) + b);
    }
}
