package prototypes.changepropagation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import prototypes.mouseeventhandling.MouseHandler;

public class PersonView extends Application {

    // External Model and Controller Declarations
    private PersonModel chrisModel;
    private PersonController chrisController;

    // Internal View Declarations
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private Button button;

    public static void main(String[] args){
        launch(args);
    }

    public PersonView(){
        chrisModel = new PersonModel();
        chrisController = new PersonController(this, chrisModel);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        button = new Button("Submit");
        root = new StackPane();


        //chrisModel.firstNameProperty().addListener(chrisController);

        root.getChildren().add(button);
        scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Person Prototype");
        primaryStage.show();

    }

    public Scene getScene(){
        return scene;
    }
}
