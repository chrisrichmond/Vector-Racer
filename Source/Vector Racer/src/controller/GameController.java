package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import model.ModelAPI;
import view.GamePane;
import view.View;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Controller, Initializable {

    private ModelAPI model;
    private GamePane gamePane;
    private View view;
    private Scene scene;
    @FXML private Region tileGrid;
    @FXML private Button testButton;

    public GameController(ModelAPI model, GamePane gamePane, View view){
        this.model = model;
        this.gamePane = gamePane;
        this.view = view;
    }

    public void setGridSize(String size, int tileSize){
        gamePane.createGrid(size, tileSize);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.scene = view.getPrimaryStage().getScene();
        //this.testButton = (Button) scene.lookup("#testButton");
    }
}
