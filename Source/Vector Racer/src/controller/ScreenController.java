package controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ScreenController implements Controller {

    private HashMap<String, Pane> screenMap;
    private Scene main;

    public ScreenController(Scene main){
        this.main = main;
    }

    public void add(String name, Pane pane){
        screenMap.put(name, pane);
    }

    public void remove(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        main.setRoot(screenMap.get(name));
    }

}
