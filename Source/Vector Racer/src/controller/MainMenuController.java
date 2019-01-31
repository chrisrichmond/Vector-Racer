package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.ModelAPI;
import view.View;

import java.io.IOException;

public class MainMenuController implements Controller {

    private ModelAPI model;
    private View view;
    @FXML private Button quitButton;

    public MainMenuController(ModelAPI model, View view){
        this.model = model;
        this.view = view;
    }

    @FXML
    public void playButtonAction(){
        System.out.println("play button pressed");
        try {
            view.getScreenController().activate("play menu");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void quitButtonAction() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }


}
