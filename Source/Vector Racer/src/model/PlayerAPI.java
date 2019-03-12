package model;

import javafx.scene.paint.Color;
import model.geometry.Point;

import java.util.List;

public interface PlayerAPI {

    List<Point> getPossibleNextPoints();

    String getName();

    RacerAPI getRacer();

    boolean isFinished();

    int getNumberOfMovesMade();

    Color getColor();

    boolean isAI();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
