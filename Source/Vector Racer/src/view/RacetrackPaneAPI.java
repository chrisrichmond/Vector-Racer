package view;

import model.PlayerAPI;

import java.util.List;

public interface RacetrackPaneAPI {

    void drawTiles();

    void drawNextPossiblePositions(PlayerAPI player);

    void drawRacerSprites(List<PlayerAPI> players);

}
