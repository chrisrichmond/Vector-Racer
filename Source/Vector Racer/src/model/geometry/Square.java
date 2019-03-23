package model.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class which represents a square within 2-dimensional Cartesian space.
 */
public class Square {

    /**
     * The Point representing the top left corner of this Square.
     */
    private Point start;

    /**
     * The Point representing the bottom right corner of this Square.
     */
    private Point end;

    /**
     * The List of Points marking the 4 equidistant corners of this Square.
     */
    private List<Point> corners;

    /**
     * Copy constructor - creates a new instance of Square.
     * @param original the Square to deep copy
     */
    public Square(Square original){
        this.start = new Point(original.getStart());
        this.end = new Point(original.getEnd());
        this.corners = new ArrayList<>(original.getCorners());
    }

    /**
     * Creates a new instance of Square.
     * @param start the Point marking the top left corner of the Square
     */
    public Square(Point start){
        this.start = start;
        this.end = new Point(start.getX()+1, start.getY()+1);

        corners = new ArrayList<>();
        corners.add(start);
        corners.add(new Point(start.getX()+1, start.getY()));
        corners.add(new Point(start.getX(), start.getY()+1));
        corners.add(end);
    }

    /**
     * Creates a new instance of Square.
     * @param startX the horizontal component of the Point marking the top left corner of the Square
     * @param startY the vertical component of the Point marking the top left corner of the Square
     */
    public Square(int startX, int startY){
        this.start = new Point(startX, startY);
        this.end = new Point(startX+1, startY+1);

        corners = new ArrayList<>();
        corners.add(start);
        corners.add(new Point(start.getX()+1, start.getY()));
        corners.add(new Point(start.getX(), start.getY()+1));
        corners.add(end);
    }

    /**
     * Convenience method which returns the horizontal component of the start Point of this Square.
     * @return the x-coordinate of the start Point
     */
    public int getStartX(){
        return start.getX();
    }

    /**
     * Convenience method which returns the vertical component of the start Point of this Square.
     * @return the y-coordinate of the start Point
     */
    public int getStartY(){
        return start.getY();
    }

    /**
     * Returns the start Point of this Square.
     * @return the start Point
     */
    public Point getStart(){
        return start;
    }

    /**
     * Returns the end Point of this Square.
     * @return the end Point
     */
    public Point getEnd(){
        return end;
    }

    /**
     * Returns the List of Points representing the corners of this Square.
     * @return the List of corner Points
     */
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
