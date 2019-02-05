package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public interface ViewAPI {

    void createMainMenuPane();

    void createPlayMenuPane();

    void createGamePane();

    GridPane getMainMenuPane();

    GridPane getPlayMenuPane();

    BorderPane getGamePane();

}
