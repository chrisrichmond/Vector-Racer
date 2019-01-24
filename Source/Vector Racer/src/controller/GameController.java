package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import model.ModelAPI;

public class GameController implements Controller {

    private ModelAPI model;
    @FXML private Region tileGrid;
    @FXML private Button testButton;

    public GameController(ModelAPI model){
        this.model = model;
    }

    public void setGridSize(){
        testButton.setMinSize(300,300);
        testButton.setMaxSize(300,300);

//        tileGrid.setMinSize();
//        tileGrid.setMaxSize();
    }

}
