package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import model.ModelAPI;

public class GameController implements Controller {

    private ModelAPI model;
    @FXML private Region tileGrid;

    public GameController(ModelAPI model){
        this.model = model;
    }

    public void setGridSize(){
        tileGrid.setMinSize();
        tileGrid.setMaxSize();
    }

}
