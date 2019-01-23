package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ModelAPI;
import view.View;

public class MainMenuController implements Controller {

    private ModelAPI model;
    private View view;
    @FXML private javafx.scene.control.Button quitButton;

    public MainMenuController(ModelAPI model, View view){
        this.model = model;
        this.view = view;
    }

    @FXML
    public void playButtonAction(){
        System.out.println("oudoius");
        view.display("playmenu"); // surely can do something better than a String??

    }

    @FXML
    public void quitButtonAction() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }


}
