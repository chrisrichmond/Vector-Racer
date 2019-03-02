package model;

import java.util.Objects;

public class CheckpointTile extends Tile {

    private int zoneNumber;

    public CheckpointTile(int startX, int startY, int zoneNumber) {
        super(startX, startY, true, 0.0f);
        this.zoneNumber = zoneNumber;
    }

    public int getZoneNumber(){
        return zoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckpointTile)) return false;
        if (!super.equals(o)) return false;
        CheckpointTile that = (CheckpointTile) o;
        return getZoneNumber() == that.getZoneNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getZoneNumber());
    }
}
