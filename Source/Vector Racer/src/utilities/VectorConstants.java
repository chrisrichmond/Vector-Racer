package utilities;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.util.List;

public class VectorConstants {

    public static final boolean IS_DEBUG_MODE = true;

    public static final String VR_FILE_EXTENSION = ".vrff";

    public static final String HOME_PATH = System.getProperty("user.home");
    public static final String VR_PATH = HOME_PATH + "/VectorRacerFiles";

    public static final String AI_ALGORITHM = "bfs";

    public static final Rectangle2D PRIMARY_SCREEN_BOUNDS = Screen.getPrimary().getVisualBounds();
    public static final int GP_PREFWIDTH = (int) PRIMARY_SCREEN_BOUNDS.getWidth();   // preferred width of the game pane
    public static final int GP_PREFHEIGHT = (int) PRIMARY_SCREEN_BOUNDS.getHeight();  // preferred height of the game pane
    public static final int TILESIZE = 20;

    public static final int MAX_ROWS = (int) ((GP_PREFHEIGHT*0.75)/TILESIZE);
    public static final int MAX_COLS = (int) ((GP_PREFWIDTH*0.75)/TILESIZE);

    public static final Color GRIDLINE_COLOR = Color.BLACK;
    public static final Color AIR_COLOR = Color.TRANSPARENT;
    public static final Color SAND_COLOR = Color.SANDYBROWN;
    public static final Color ICE_COLOR = Color.CYAN;
    public static final Color WALL_COLOR = Color.BLACK;
    public static final Color[] CHECKPOINT_COLORS = {
            Color.BLACK,        // 0
            Color.SEAGREEN,     // 1
            Color.VIOLET,       // 2
            Color.TAN,          // 3
            Color.SIENNA,       // 4
            Color.YELLOWGREEN,  // 5
            Color.SALMON,       // 6
            Color.TEAL,         // 7
            Color.YELLOW,       // 8
            Color.TURQUOISE     // 9
    };

    public static final Color CIRCLESELECTOR_COLOR = Color.GREEN;
    public static final double RACERTRAIL_THICKNESS = 2.0;


}
