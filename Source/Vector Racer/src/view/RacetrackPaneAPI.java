package view;

import model.PlayerAPI;

import java.util.List;

public interface RacetrackPaneAPI {

    /**
     *
     */
    void drawTiles();

    /**
     *
     * @param player
     */
    void drawNextPossiblePositions(PlayerAPI player);

    /**
     *
     * @param players
     */
    void drawRacerSprites(List<PlayerAPI> players);

}
