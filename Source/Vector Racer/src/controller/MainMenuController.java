package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ModelAPI;
import view.View;

public class MainMenuController implements EventHandler {

    private ModelAPI model;
    private View view;

    public MainMenuController(ModelAPI model, View view){
        this.model = model;
        this.view = view;

        view.getMainMenuPane().addEventFilter(MouseEvent.MOUSE_CLICKED, this::handle);
    }

//    @FXML
//    public void playButtonAction(){
//        System.out.println("play button pressed");
//        try {
//            view.getScreenManager().activate("play menu");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    public void quitButtonAction() {
//        Stage stage = (Stage) quitButton.getScene().getWindow();
//        stage.close();
//    }


    @Override
    public void handle(Event event) {
        System.out.println("event type: "+ event.getEventType());
        System.out.println("event target: "+ event.getTarget());
        System.out.println("event source: "+ event.getSource());
    }
}
