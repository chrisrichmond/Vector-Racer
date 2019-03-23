package model;

import javafx.scene.paint.Color;
import java.util.Objects;

/**
 * Class which represents a player in the game.
 */
public class Player implements PlayerAPI{

    /**
     * The player's name.
     */
    protected String name;

    /**
     * The Racer associated with this Player.
     */
    protected Racer racer;

    /**
     * The Color associated with this Player.
     */
    protected Color color;

    /**
     * Whether or not this Player is an AI.
     */
    protected boolean ai;

    /**
     * Copy constructor - creates new instance of Player.
     * @param original the Player to deep copy
     */
    public Player(PlayerAPI original){
        this.name = new String(original.getName());
        this.racer = new Racer(original.getRacer());
        this.color = original.getColor();
        this.ai = original.isAI();
    }

    /**
     * Creates new instance of Player.
     * @param name the name to assign to this Player
     * @param racer the racer to associate with this Player
     * @param color the color to associate with this Player
     * @param ai whether or not this Player is an AI
     */
    public Player(String name, RacerAPI racer, Color color, boolean ai) {
        this.name = name;
        this.racer = (Racer) racer;
        this.color = color;
        this.ai = ai;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public RacerAPI getRacer(){
        return racer;
    }

    @Override
    public boolean isFinished(){
        return racer.isFinished();
    }

    @Override
    public int getNumberOfMovesMade(){
        return racer.getPointRoute().size()-1;
    }

    @Override
    public Color getColor(){
        return color;
    }

    @Override
    public boolean isAI() {
        return ai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return name.equals(player.name) &&
                getRacer().equals(player.getRacer()) &&
                getColor().equals(player.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getRacer(), getColor());
    }


}
