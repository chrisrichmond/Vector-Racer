package prototypes.mouseeventhandling;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseHandler implements EventHandler {

    @Override
    public void handle(Event event){
        handle((MouseEvent) event);
    }

    public void handle(MouseEvent event) {
        System.out.println("Mouse click detected on: "+event.getSource());
        System.out.println("getX() : "+event.getX());
        System.out.println("getSceneX() : "+event.getSceneX());
        System.out.println("getScreenX() : "+event.getScreenX());
    }
}
