package prototypes.mouseeventhandling;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class PaneController {

    private View view;
    private MouseHandler mouseHandler;

    public PaneController(View view){
        this.view = view;
        this.mouseHandler = new MouseHandler();
    }

    public void assignMouseClickHandler(Node node){
        node.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseHandler);
    }

}
