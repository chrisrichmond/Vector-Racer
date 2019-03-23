package model;

import javafx.scene.paint.Color;

/**
 * Interface which represents a player in the game.
 */
public interface PlayerAPI {

    /**
     * Returns the name of this PlayerAPI.
     * @return the name of this PlayerAPI
     */
    String getName();

    /**
     * Returns the Racer associated with this PlayerAPI.
     * @return the Racer of this PlayerAPI
     */
    RacerAPI getRacer();

    /**
     * Returns whether or not this player is finished.
     * @return true if finished, false if not
     */
    boolean isFinished();

    /**
     * Returns the number of moves this PlayerAPI has made
     * @return the number of moves made so far
     */
    int getNumberOfMovesMade();

    /**
     * Returns the Color associated with this PlayerAPI.
     * @return the Color of this PlayerAPI
     */
    Color getColor();

    /**
     * Returns whether or not this PlayerAPI is an AI.
     * @return true if PlayerAPI is an AI, false if not
     */
    boolean isAI();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
