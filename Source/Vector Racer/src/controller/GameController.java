package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import model.ModelAPI;
import view.View;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Controller, Initializable {

    private ModelAPI model;
    private Pane gamePane;
    private View view;
    private Scene scene;
    @FXML private Region tileGrid;
    @FXML private Button testButton;

    public GameController(ModelAPI model, Pane gamePane, View view){
        this.model = model;
        this.gamePane = gamePane;
        this.view = view;
    }

    public void setGridSize(){
//        testButton.setMinSize(300,300);
//        testButton.setMaxSize(300,300);
        //testButton.setPrefSize(300,300);

        testButton.setText("howdy");

//        tileGrid.setMinSize();
//        tileGrid.setMaxSize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.scene = view.getPrimaryStage().getScene();
        //this.testButton = (Button) scene.lookup("#testButton");
    }
}
