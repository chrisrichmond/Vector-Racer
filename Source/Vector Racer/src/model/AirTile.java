package model;

import javafx.scene.paint.Color;

public class AirTile extends Tile {

    public AirTile(int startX, int startY) {
        super(startX, startY, true, 0.0f, 0.0f, 0.0f);
        setColor(Color.TRANSPARENT);
    }
}
