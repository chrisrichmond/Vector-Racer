import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelAPI;
import view.View;

public class Driver extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /**
         * Initialisation of MVC using Observer Pattern // todo look into JavaFX Properties
         *
         * [1] - Model is created and initialises internal data structures.
         * [2] - View is created, taking reference to the Model as a parameter.
         * [3] - View subscribes to change-propagation mechanism (Observer) of the Model using attach method.
         * [4] - View creates Controller. Passes Controller a reference to itself and to Model.
         * [5] - Controller subscribes to change-propagation mechanism of the Model using attach method.
         * [6] - Controller starts processing events
         *
         */

        ModelAPI model = new Model();
        View view = new View(model, primaryStage);
        Controller controller = new Controller(model, view, primaryStage);
    }
}
