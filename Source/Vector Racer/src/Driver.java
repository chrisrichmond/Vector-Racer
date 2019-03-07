import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelAPI;
import utilities.VectorConstants;
import view.View;
import view.ViewAPI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Driver extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /**
         * OLD Initialisation of MVC using Observer Pattern
         *
         * [1] - Model is created and initialises internal data structures.
         * [2] - View is created, taking reference to the Model as a parameter.
         * [3] - View subscribes to change-propagation mechanism (Observer) of the Model using attach method.
         * [4] - Controller is created, taking reference to both View and to Model.
         * [5] - Controller subscribes to change-propagation mechanism of the Model using attach method.
         * [6] - Controller starts processing events
         *
         */

        /**
         * NEW Streamlined Initialisation of MVC using Observer Pattern
         *
         * [1] - Model is created and initialises internal data structures.
         * [2] - View is created, taking reference to the Model as a parameter.
         * [3] - Controller is created, taking reference to both View and to Model.
         * [4] - Controller subscribes to change-propagation mechanism of the Model using attach method.
         * [5] - Controller starts processing events
         *
         */

        // Filesystem Setup
        File vrDir = new File(VectorConstants.VR_PATH);
        if(!(vrDir.exists())){
            vrDir.mkdir();
        }

        copyAllFilesFromDir(new File("./resources/racetracks"), vrDir);

        ModelAPI model = new Model();
        ViewAPI view = new View(model, primaryStage);
        Controller controller = new Controller(model, view, primaryStage);
    }

    private void copyAllFilesFromDir(File dir, File destinationDir){
        for(File currentFile: dir.listFiles()){
            if(currentFile.isDirectory()){
                copyAllFilesFromDir(currentFile, destinationDir);
            }else{
                boolean fileAlreadyExists = false;
                for(File currentDestFile: destinationDir.listFiles()) {
                    if (currentFile.getName().equals(currentDestFile.getName())){
                        fileAlreadyExists = true;
                    }
                }
                if(!fileAlreadyExists) {
                    try {
                        Files.copy(currentFile.toPath(), Paths.get(destinationDir.toString()+"\\"+currentFile.getName()));
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
