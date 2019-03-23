package model;

import model.geometry.Point;
import model.geometry.Vect;

import java.util.Deque;
import java.util.List;

/**
 * Interface representing a Racer in the game.
 */
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
     * @return a double-ended queue representing the current route of this Racer, to be interpreted in Stack order
     */
    Deque<Point> getPointRoute();

    /**
     * Get the next possible positions that the Racer could move to based on its current position, velocity, and nearby vertex traversability
     * @param racetrack the RacetrackAPI that this RacerAPI is currently on
     * @param ai whether or not this RacerAPI's PlayerAPI is an AI or not
     * @return a list of Points representing the next possible positions in no particular order
     */
    List<Point> getPossibleNextPoints(RacetrackAPI racetrack, boolean ai);

    /**
     * Get all 9 next possible positions that the Racer could move to based only on its current position and velocity, not nearby vertex traversability
     * Then use getPossibleNextPoints() to eliminate possible points and return only impossible ones
     * @param racetrack the RacetrackAPI that this RacerAPI is currently on
     * @param ai whether or not this RacerAPI's PlayerAPI is an AI or not
     * @return a list of Points represnting the next "impossible" positions in no particular order
     */
    List<Point> getImpossibleNextPoints(RacetrackAPI racetrack, boolean ai);

    /**
     * Gets the next Point from the RacerAPI's current position and given its velocity remains the same
     * @return the central Point
     */
    Point getNextCentralPoint();

    /**
     * Evaluates the next destination that the Racer should be in after applying Terrain effects from any Tiles it has traversed between its current position and its probationary destination
     * @param racetrack the current Racetrack
     * @param destinationBeforeEffects the probationary position of the Racer before effects are applied, used to evaluate the resultant position
     * @return the resultant destination after Terrain effects have been applied
     */
    void moveWhilstApplyingEffects(RacetrackAPI racetrack, Point destinationBeforeEffects);

    /**
     * Returns the  integer value of the current checkpoint zone this RacerAPI is in.
     * @return the current zone
     */
    int getCurrentZone();

    /**
     * Increment the current zone.
     */
    void nextZone();

    /**
     * Reduces the RacerAPI's velocity to 0 horizontal and 0 vertical.
     */
    public void killVelocity();

    /**
     * Returns the number of times this RacerAPI has crashed.
     * @return the crash count
     */
    int getCrashCount();

    /**
     * Returns this RacerAPI's score (number of points visited + crashes).
     * @return the score
     */
    int getScore();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
