package controller;

import javafx.fxml.FXML;
import model.ModelAPI;
import view.OldView;

public class PlayMenuController implements Controller {

    private ModelAPI model;
    private OldView oldView;

    public PlayMenuController(ModelAPI model, OldView oldView){
        this.model = model;
        this.oldView = oldView;
    }

    @FXML
    public void pvpButtonAction(){
        // ask user to select a size of racetrack
        // examine racetrack dimensions
        // set tile grid dimensions based on racetrack size
        // load tiles in from racetrack from file at appropriate uniform dimensions
        // display game pane


        // testing setting region size from code
        //oldView.setGameGridSize(0,0); // TEST
        oldView.getScreenManager().activate("game");   // todo need to change this later as settings need to be selected after this point such as choosing a racetrack etc.
    }


}
