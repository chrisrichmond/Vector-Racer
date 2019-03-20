package view;

import com.jfoenix.controls.JFXButton;
import controller.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ModelAPI;
import utilities.VectorConstants;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Paths;

public class View implements ViewAPI{

    // File Handling
    private FileChooser fileChooser;
    private File vrDir;

    // Model Backend
    private ModelAPI model;

    // Content Handlers
    private EventHandler gameHandler;

    // Primary Stage and Root Content Node
    private Stage primaryStage;
    private Parent root;

    // Racetrack Game Area Pane and Info TextArea
    private RacetrackPane racetrackPane;
    private TextArea infoTextArea;

    // Views (Full Screen Content Panes)
    private AnchorPane mainMenuPane;
//    private GridPane mainMenuPane;
    private GridPane playMenuPane;
    private BorderPane gamePane;


    public View(ModelAPI model, Stage primaryStage) throws Exception{

        // File Handling
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Vector Racer Files (*"+VectorConstants.VR_FILE_EXTENSION+")", ("*"+VectorConstants.VR_FILE_EXTENSION)));
        vrDir = new File(VectorConstants.VR_PATH);
        fileChooser.setInitialDirectory(vrDir);

        // Model Backend
        this.model = model;

        // Primary Stage
        this.primaryStage = primaryStage;

        // Racetrack Game Area Pane

        // Views (Full Screen Content Panes)
        createMainMenuPane();
        createPlayMenuPane();

        // Create main scene with main menu as default root content, and show
        primaryStage.setMaximized(false);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(mainMenuPane));
        primaryStage.show();


    }

    @Override
    public void createMainMenuPane(){
        mainMenuPane = new AnchorPane();

        mainMenuPane.setPrefSize(600.0, 350.0);
        ImageView backgroundImageView = new ImageView();
//        try {
//            URL url = new URL("file:vrsplash.jpg");
//            System.out.println(url);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
        Image image = new Image(Paths.get("vrsplash.png").toUri().toString());
//        System.out.println(backgroundImageView);
        backgroundImageView.setImage(image);
//        backgroundImageView.setCache(true);
        backgroundImageView.setFitWidth(600.0);
        backgroundImageView.setFitHeight(400.0);
        backgroundImageView.setPickOnBounds(true);
        backgroundImageView.setPreserveRatio(true);


        VBox vbox = new VBox();
        vbox.setPrefSize(178.0, 200.0);

        JFXButton playButton = new JFXButton();
        playButton.setPrefSize(171, 25);
        playButton.setText("PLAY");
        playButton.setFont(new Font("Consolas Bold Italic", 20.0));
        JFXButton quitButton = new JFXButton();
        playButton.setPrefSize(171, 37);
        playButton.setText("QUIT");
        playButton.setFont(new Font("Consolas Bold Italic", 20.0));

        vbox.getChildren().addAll(playButton, quitButton);

        mainMenuPane.getChildren().addAll(backgroundImageView, vbox);


//        mainMenuPane = new GridPane();
//
//        Button playButton = new Button("Play");
//        Button testButton = new Button("test");
//        Button quitButton = new Button("Quit");
//
//        playButton.setOnAction(e -> changeRootContent(playMenuPane));
//        testButton.setOnAction(e -> System.out.println("TEST BUTTON CLICK"));
//        quitButton.setOnAction(e -> primaryStage.close());
//
//        GridPane.setConstraints(playButton, 1,1);
//        GridPane.setConstraints(testButton, 1,2);
//        GridPane.setConstraints(quitButton, 1,3);
//        ColumnConstraints column0 = new ColumnConstraints();
//        ColumnConstraints column1 = new ColumnConstraints();
//        ColumnConstraints column2 = new ColumnConstraints();
//        column0.setPercentWidth(25);
//        column1.setPercentWidth(50);
//        column2.setPercentWidth(25);
//        mainMenuPane.getColumnConstraints().addAll(column0, column1, column2);
//
//        mainMenuPane.getChildren().addAll(playButton, testButton, quitButton);
//
//        mainMenuPane.setMinSize(500, 500);
    }

    @Override
    public void createPlayMenuPane(){
        playMenuPane = new GridPane();

        Button pvpButton = new Button("Player vs Player");

        pvpButton.setOnAction(e -> {
            fileChooser.setTitle("Choose a Racetrack to load");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if(selectedFile != null) {
                try {
                    model.setup(selectedFile, "player1", "player2", VectorConstants.AI_MODE);
                    createGamePane();
                    changeRootContent(gamePane);
                    model.start();
                } catch (Exception ex) {
                    System.out.println("file not found");
                    ex.printStackTrace();
                }
            }
        });

        playMenuPane.getChildren().addAll(pvpButton);
        playMenuPane.setMinSize(600, 600);
    }

    @Override
    public void createGamePane(){
        gamePane = new BorderPane();
        racetrackPane = new RacetrackPane(model.getRacetrack());
        infoTextArea = new TextArea();
        infoTextArea.setText(model.getRacetrack().getStartPosition().toString());
        BorderPane.setAlignment(racetrackPane, Pos.CENTER);
        BorderPane.setAlignment(infoTextArea, Pos.BASELINE_CENTER);

        Reflection reflection = new Reflection();
        reflection.setFraction(0.25);
        racetrackPane.setEffect(reflection);
        gamePane.getChildren().add(racetrackPane);
        gamePane.getChildren().add(infoTextArea);
        racetrackPane.addEventFilter(MouseEvent.MOUSE_PRESSED, gameHandler);

    }

    @Override
    public AnchorPane getMainMenuPane(){
        return mainMenuPane;
    }

    @Override
    public GridPane getPlayMenuPane(){
        return playMenuPane;
    }

    @Override
    public BorderPane getGamePane() {
        return gamePane;
    }

    @Override
    public void changeRootContent(Pane pane) {
        primaryStage.hide();
        primaryStage.getScene().setRoot(pane);
        primaryStage.show();
    }

    @Override
    public RacetrackPaneAPI getRacetrackPane() {
        return racetrackPane;
    }


    @Override
    public void setGameHandler(EventHandler gameHandler){
        this.gameHandler = gameHandler;
    }

}
