package utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

public class ScreenManager {

    private HashMap<String, Object> screenMap;
    private Scene main;

    public ScreenManager(Scene main){
        screenMap = new HashMap<>();
        this.main = main;
    }

    /**
     * Adds an element of type FXMLLoader to the screenMap HashMap
     * @param name a String keyword to use as the key
     * @param fxml an FXMLLoader to store as the value
     */
    public void add(String name, FXMLLoader fxml){
        if(!(screenMap.containsKey(name))) {
            screenMap.put(name, fxml);
        }else{
            System.out.println("Screen(FXMLLoader) with this name already added to screenMap: '"+name+"'");
        }
    }

    /**
     * Adds an element of type Pane to the screenMap HashMap
     * @param name a String keyword to use as the key
     * @param pane a Pane to store as the value
     */
    public void add(String name, Pane pane){
        if(!(screenMap.containsKey(name))) {
            screenMap.put(name, pane);
        }else{
            System.out.println("Screen(Pane) with this name already added to screenMap: '"+name+"'");
        }
    }

    /**
     * Removes the specified element key-value pair from the screenMap HashMap
     * @param name the key of the key-value pair to be removed
     */
    public void remove(String name){
        screenMap.remove(name);
    }

    /**
     * Sets the corresponding value of the specified name as the root content for the main Scene
     * @param name the key of the content to be displayed from the screenMap HashMap
     */
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
