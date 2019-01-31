package prototypes.mouseeventhandling;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends Application {

    private PaneController paneController;

    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private Rectangle area;

    public static void main(String[] args){
        launch(args);
    }

    public View(){
        paneController = new PaneController(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        root = new StackPane();
        area = new Rectangle(500, 250);
        root.getChildren().add(area);
        scene = new Scene(root);

        paneController.assignMouseClickHandler(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mouse Handling Prototype");
        primaryStage.show();
    }
}
