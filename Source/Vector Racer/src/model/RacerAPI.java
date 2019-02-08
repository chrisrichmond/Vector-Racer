package model;

import model.geometry.Point;
import model.geometry.Vect;
import java.util.Stack;

public interface RacerAPI {

    /**
     * Get the Racer's current velocity in terms of a horizontal X component, and a vertical Y component
     * @return a vector representing the current velocity
     */
    Vect getVelocity();

    /**
     * Get the Racer's current race status as to whether they have finished or not
     * @return boolean true if finished, false if not
     */
    boolean isFinished();

    /**
     * Get the Racer's current grid coordinates on the Racetrack in cartesian space
     * @return the Point bearing the current position of this Racer
     */
    Point getPosition();

    /**
     * Set the Racer's current grid coordinates on the Racetrack in cartesian space
     * @param position the new position to set the Racer to
     */
    void setPosition(Point position);

    /**
     * Get the Racer's current route of visited points as an ordered list
     * @return
     */
    Stack<Point> getPointRoute();

}
