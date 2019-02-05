package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public interface ViewAPI {

    void createMainMenuPane();

    void createPlayMenuPane();

    void createGamePane();

    GridPane getMainMenuPane();

    GridPane getPlayMenuPane();

    BorderPane getGamePane();

    void changeRootContent(Pane pane);

}
