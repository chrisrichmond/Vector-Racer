package view;

import model.PlayerAPI;
import java.util.List;

/**
 * Interface representing the racetrack display panel in the view.
 */
public interface RacetrackPaneAPI {

    /**
     * Draws all tiles from associated ModelAPI's RacetrackAPI.
     */
    void drawTiles();

    /**
     * Draws all of a PlayerAPI's next possible position circles.
     * @param player the PlayerAPI to draw for
     */
    void drawNextPossiblePositions(PlayerAPI player);

    /**
     * Draws all RacerAPI sprites
     * @param players the PlayerAPI's whose RacerAPI's to draw for.
     */
    void drawRacerSprites(List<PlayerAPI> players);

}
