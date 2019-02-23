package model;

public class CheckpointTile extends Tile {

    private int zoneNumber;

    public CheckpointTile(int startX, int startY, int zoneNumber) {
        super(startX, startY, true, 0.0f, 0.0f, 0.0f);
        this.zoneNumber = zoneNumber;
    }


}
