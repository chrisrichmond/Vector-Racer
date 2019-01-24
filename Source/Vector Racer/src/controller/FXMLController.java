package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

public class FXMLController implements Controller {

    private HashMap<String, FXMLLoader> screenMap;
    private Scene main;

    public FXMLController(Scene main){
        screenMap = new HashMap<>();
        this.main = main;
    }

    public void add(String name, FXMLLoader fxml){
        screenMap.put(name, fxml);
    }

    public void remove(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        try {
            main.setRoot(screenMap.get(name).load());
        }catch (IOException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            System.out.println("Tried switching to invalid screen name - may not be stored or error in String name");
        }
    }

}
