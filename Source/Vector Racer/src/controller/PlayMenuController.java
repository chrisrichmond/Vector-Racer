package controller;

import model.ModelAPI;
import view.View;

public class PlayMenuController implements Controller {

    private ModelAPI model;
    private View view;

    public PlayMenuController(ModelAPI model, View view){
        this.model = model;
        this.view = view;
    }

}
