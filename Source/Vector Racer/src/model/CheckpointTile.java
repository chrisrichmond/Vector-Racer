package model;

import java.util.Objects;

/**
 * Class representing a checkpoint tile in the game.
 */
public class CheckpointTile extends Tile {

    /**
     * The zone number of this CheckpointTile.
     */
    private int zoneNumber;

    /**
     * Copy constructor - creates a new instance of CheckpointTile.
     * @param original the CheckpointTile to deep copy
     */
    public CheckpointTile(CheckpointTile original){
        super(original.getStartX(), original.getStartY(), true, original.getZoneNumber());
    }

    /**
     * Creates a new instance of CheckpointTile.
     * @param startX the horizontal component marking the position of the CheckpointTile
     * @param startY the vertical component marking the position of the CheckpointTile
     * @param zoneNumber the zone number of the CheckpointTile
     */
    public CheckpointTile(int startX, int startY, int zoneNumber) {
        super(startX, startY, true, 0.0f);
        this.zoneNumber = zoneNumber;
    }

    /**
     * Returns the zone number of this CheckpointTile
     * @return the zone number
     */
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
