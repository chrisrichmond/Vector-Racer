package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import model.ModelAPI;

public class GameController implements Controller {

    private ModelAPI model;

    public GameController(ModelAPI model){
        this.model = model;
    }

    @FXML
    public void onClickTest(MouseEvent mouseEvent) {

    }

}
