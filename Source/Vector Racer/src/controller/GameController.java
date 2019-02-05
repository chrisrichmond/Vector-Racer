package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import model.ModelAPI;
import view.GamePane;
import view.OldView;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private ModelAPI model;
    private GamePane gamePane;
    private OldView oldView;
    private Scene scene;
    @FXML private Region tileGrid;
    @FXML private Button testButton;

    public GameController(ModelAPI model, GamePane gamePane, OldView oldView){
        this.model = model;
        this.gamePane = gamePane;
        this.oldView = oldView;
    }

    public void setGridSize(String size, int tileSize){
        gamePane.createGrid(size, tileSize);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.scene = oldView.getPrimaryStage().getScene();
        //this.testButton = (Button) scene.lookup("#testButton");
    }
}
