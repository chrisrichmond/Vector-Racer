package view;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Interface representing the main view component of the Vector Racer system.
 * Implementing classes should present the user interface.
 */
public interface ViewAPI {

    /**
     * Creates the main menu from which the user can progress to the play menu or exit the game
     */
    void createMainMenuPane();

    /**
     * Creates the game area including the region displaying the chosen racetrack and accompanying dialog
     */
    void createGamePane();

    /**
     * Gets the main menu pane
     * @return reference to the main menu pane
     */
    AnchorPane getMainMenuPane();

    /**
     * Gets the game pane
     * @return reference to the game pane
     */
    AnchorPane getGamePane();

    /**
     * Changes the current root node of the main Scene Graph attributed to the primary Stage
     * @param pane the new root content node
     */
    void changeRootContent(Pane pane);

    /**
     * Gets the racetrack pane
     * @return reference to the racetrack pane
     */
    RacetrackPaneAPI getRacetrackPane();

    /**
     * Sets the Handler being used for event handling in the game pane
     * @param gameHandler the Handler to assign
     */
    void setGameHandler(EventHandler gameHandler);

    /**
     * Returns the label used for displaying information to the user during gameplay
     * @return the info label
     */
    Label getInfoLabel();

}
