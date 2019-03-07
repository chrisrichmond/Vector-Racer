package view;

import model.Player;

import java.util.List;

public interface RacetrackPaneAPI {

    void drawTiles();

    void drawNextPossiblePositions(Player player);

    void drawRacerSprites(List<Player> players);

}
