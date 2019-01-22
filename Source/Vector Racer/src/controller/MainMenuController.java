package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ModelAPI;

public class MainMenuController implements Controller {

    private ModelAPI model;
    @FXML private javafx.scene.control.Button quitButton;

    public MainMenuController(ModelAPI model){
        this.model = model;
    }

    @FXML
    public void quitButtonAction() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }


}
