package model;

import javafx.scene.paint.Color;

public class WallTile extends Tile{

    public WallTile(int startX, int startY) {
        super(startX, startY, false);
        setColor(Color.BLACK);
    }
}
