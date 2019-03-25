package model;

import model.geometry.Point;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void setup() {
        ModelAPI model = new Model();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("racetracks/testingtrack.vrff").getFile());
    }

    @Test
    public void getRacetrack() {
    }

    @Test
    public void getCurrentState() {
    }

    @Test
    public void loadFileTestingTrack() throws FileNotFoundException {
        ModelAPI model = new Model();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("racetracks/testingtrack.vrff").getFile());
        model.setup(file, "p1", "p2", false);

//        assertEquals(getRacetrack());
    }

    @Test
    public void createEmptyRacetrack() {
        RacetrackAPI racetrack = new Racetrack(10, 20, new Point(5, 5), 3);
        assertEquals(10, racetrack.getRows());
        assertEquals(20, racetrack.getCols());
        assertEquals(new Point(5, 5), racetrack.getStartPosition());
        assertEquals(3, racetrack.getFinalZone());

    }

    @Test
    public void addAirTile() {
        RacetrackAPI racetrack = new Racetrack(20, 40, new Point(10, 10), 3);

    }

    @Test
    public void addSandTile() {
    }

    @Test
    public void addIceTile() {
    }

    @Test
    public void addWallTile() {
    }

    @Test
    public void addCheckpointTile() {
    }

    @Test
    public void fillRemainderWith() {
    }

    @Test
    public void gridPointInput() {
    }

    @Test
    public void setStartPosition() {
    }

    @Test
    public void getWinner() {
    }

    @Test
    public void attach() {
    }

    @Test
    public void detach() {
    }

    @Test
    public void notifyObservers() {
    }
}