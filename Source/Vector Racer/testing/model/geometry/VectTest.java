package model.geometry;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class VectTest {

    @Test
    public void getPositiveXVelo() {
        Vect vect = new Vect(3, 1, 5, 7);
        assertEquals(2, vect.getXVelo());
    }

    @Test
    public void getNegativeXVelo() {
        Vect vect = new Vect(3, 1, -5, -7);
        assertEquals(-8, vect.getXVelo());
    }

    @Test
    public void getPositiveYVelo() {
        Vect vect = new Vect(3, 1, 5, 7);
        assertEquals(6, vect.getYVelo());
    }

    @Test
    public void getNegativeYVelo() {
        Vect vect = new Vect(3, 1, -5, -7);
        assertEquals(-8, vect.getYVelo());
    }

    @Test
    public void getNormalGradient() {
        Vect vect = new Vect(1, 3, 2, 6);
        assertEquals(3, vect.getGradient(), 0.01);
    }

    @Test
    public void getZeroGradient() {
        Vect vect = new Vect(1, 1, 2, 1);
        assertEquals(0, vect.getGradient(), 0.01);
    }

    @Test
    public void getUndefinedGradient() {
        Vect vect = new Vect(0, 3, 0, 6);
        assertEquals(Double.POSITIVE_INFINITY, vect.getGradient(), 0.01);
    }

    @Test
    public void getXfromY() {
        Vect vect = new Vect(0, 0, 4, 2);
        assertEquals(2, vect.getXfromY(1), 0.01);
    }

    @Test
    public void getYfromX() {
        Vect vect = new Vect(0, 0, 4, 2);
        assertEquals(1, vect.getYfromX(2), 0.01);
    }
}