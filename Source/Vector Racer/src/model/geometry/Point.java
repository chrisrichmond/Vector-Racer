package model.geometry;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * Class which represents a single point in 2-dimensional Cartesian space.
 */
public class Point {

    /**
     * The horizontal component of this Point.
     */
    private int x;

    /**
     * The vertical component of this Point.
     */
    private int y;

    /**
     * Copy constructor - creates a new instance of Point.
     * @param original the Point to deep copy
     */
    public Point(Point original){
        this.x = original.getX();
        this.y = original.getY();
    }

    /**
     * Creates a new instance of Point.
     * @param x the horizontal component of this Point
     * @param y the vertical component of this Point
     */
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the horizontal component of this Point.
     * @return the x coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * Returns the vertical component of this Point.
     * @return the y coordinate
     */
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}
