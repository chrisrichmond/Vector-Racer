package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

public class ScreenController implements Controller {

    private HashMap<String, Object> screenMap;
    private Scene main;

    public ScreenController(Scene main){
        screenMap = new HashMap<>();
        this.main = main;
    }

    public void add(String name, FXMLLoader fxml){
        if(!(screenMap.containsKey(name))) {
            screenMap.put(name, fxml);
        }else{
            System.out.println("Screen(FXMLLoader) with this name already added to screenMap: '"+name+"'");
        }
    }

    public void add(String name, Pane pane){
        if(!(screenMap.containsKey(name))) {
            screenMap.put(name, pane);
        }else{
            System.out.println("Screen(Pane) with this name already added to screenMap: '"+name+"'");
        }
    }

    public void remove(String name){
        screenMap.remove(name);
    }

    public void activate(String name){

        System.out.println("Attempting to load '"+name+"'");
        try {
            if(screenMap.get(name) instanceof FXMLLoader) {
                FXMLLoader fxmlLoader = (FXMLLoader) (screenMap.get(name));

                main.setRoot(fxmlLoader.load());
            }else if(screenMap.get(name) instanceof Pane){
                Pane pane = (Pane) (screenMap.get(name));

                main.setRoot(pane);
            }else{
                System.out.println("Invalid instance type of screen");
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            System.out.println("Tried switching to invalid screen name - may not be stored or error in String name");
        }
    }

}
