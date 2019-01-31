package utilities;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class VectorConstants {

    public static final String ERROR_FXML = "errorscreen.fxml";
    public static final String MAINMENU_FXML = "mainmenu.fxml";
    public static final String PLAYMENU_FXML = "playmenu.fxml";
    public static final Rectangle2D PRIMARY_SCREEN_BOUNDS = Screen.getPrimary().getVisualBounds();
    public static final int GP_PREFWIDTH = (int) PRIMARY_SCREEN_BOUNDS.getWidth();   // preferred width of the game pane
    public static final int GP_PREFHEIGHT = (int) PRIMARY_SCREEN_BOUNDS.getHeight();  // preferred height of the game pane
    public static final int TILESIZE = 40;

    public static final int SMALL_ROWS = 10;   // Y
    public static final int SMALL_COLS = 20;   // X
    public static final int MEDIUM_ROWS = 20;  // Y
    public static final int MEDIUM_COLS = 40;  // X
    public static final int LARGE_ROWS = 40;   // Y
    public static final int LARGE_COLS = 80;  // X
}
