package model;

import model.geometry.Point;
import model.geometry.Vect;

public interface RacerAPI {

    /**
     * Get the Racer's current velocity in terms of a horizontal X component, and a vertical Y component
     * @return a vector represeting the current velocity
     */
    public Vect getVelocity();

    /**
     * Get the Racer's current race status as to whether they have finished or not
     * @return boolean true if finished, false if not
     */
    public boolean isFinished();

    /**
     * Get the Racer's current grid coordinates on the Racetrack in cartesian space
     * @return the Point bearing the current position of this Racer
     */
    public Point getPosition();

    public RacerAPI

}
