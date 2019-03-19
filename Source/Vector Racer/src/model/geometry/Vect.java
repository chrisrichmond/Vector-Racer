package model.geometry;

import java.util.Objects;

/**
 * Class which represents a line vector in 2-dimensional Cartesian space.
 */
public class Vect {

    /**
     * The Point representing the start of this Vector.
     */
    protected Point start;

    /**
     * The Point representing the end of this Vect.
     */
    protected Point end;

    /**
     * The value of the slope of this Vect.
     */
    protected double gradient;

    /**
     * Copy constructor - creates a new instance of Vect.
     * @param original the Vect to deep copy
     */
    public Vect(Vect original){
        this.start = new Point(original.getStart());
        this.end = new Point(original.getEnd());
        this.gradient = original.getGradient();
    }

    /**
     * Creates a new instance of Vect.
     * @param startX the horizontal component of the start Point of this Vect
     * @param startY the vertical component of the start Point of this Vect
     * @param endX the horizontal component of the end Point of this Vect
     * @param endY the vertical component of the end Point of this Vect
     */
    public Vect(int startX, int startY, int endX, int endY){
        this.start = new Point(startX, startY);
        this.end = new Point(endX, endY);
        calcGradient();
    }

    /**
     * Creates a new instance of Vect.
     * @param start the start Point of this Vect
     * @param end the end Point of this Vect
     */
    public Vect(Point start, Point end){
        this.start = start;
        this.end = end;
        calcGradient();
    }

    /**
     * Calculates and assigns the value of this Vect's gradient.
     * Assigns a value of 0 in cases where the slope is infinite.
     */
    private void calcGradient(){
        try {
            this.gradient = ((double)end.getY() - (double)start.getY()) / ((double)end.getX() - (double)start.getX());
        }catch(Exception e){
            this.gradient = 0;
        }
    }

    /**
     * Returns the start Point of this Vect
     * @return the start Point
     */
    public Point getStart() {
        return start;
    }

    /**
     * Returns the end Point of this Vect
     * @return the end Point
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Returns the horizontal magnitude of this Vect
     * @return the difference between the start and end Point x-coordinates
     */
    public int getXVelo(){
        return end.getX()-start.getX();
    }

    /**
     * Returns the vertical magnitude of this Vect
     * @return the difference between the start and end Point y-coordinates
     */
    public int getYVelo(){
        return end.getY()-start.getY();
    }

    /**
     * Returns the slope of this Vect
     * @return the gradient value of this Vect
     */
    public double getGradient(){
        return gradient;
    }

    /**
     * Calculates the corresponding horizontal component on this Vect line using a given vertical coordinate
     * @param y the y-coordinate
     * @return the corresponding x-coordinate
     */
    public double getXfromY(double y){
        // x = (y-b)/m + a , when a is any x coordinate on the line and b is its corresponding y coordinate
        double a = start.getX();
        double b = start.getY();
        double m = gradient;
        return ( (y-b)/m + a);
    }

    /**
     * Calculates the corresponding vertical component on this Vect line using a given horizontal coordinate
     * @param x the x-coordinate
     * @return the corresponding y-coordinate
     */
    public double getYfromX(double x){
        // y = m(x-a) + b , when a is any x coordinate on the line and b is its corresponding y coordinate
        double a = start.getX();
        double b = start.getY();
        double m = gradient;
        return ( m*(x-a) + b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vect)) return false;
        Vect vect = (Vect) o;
        return Double.compare(vect.getGradient(), getGradient()) == 0 &&
                getStart().equals(vect.getStart()) &&
                getEnd().equals(vect.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd(), getGradient());
    }
}
