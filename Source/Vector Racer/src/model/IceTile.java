package model;

import javafx.scene.paint.Color;
import model.geometry.Square;

public class IceTile extends Tile {

    public IceTile(int startX, int startY){
        super(startX, startY, true, 0.0f, 0.5f, 0.0f);
        setColor(Color.CYAN);
    }
}
