package model;

import com.badlogic.gdx.math.Vector2;

public class Model {

    /*
        This is the class and related classes which
        should store all game data

        Begin by placing all information in few places
        and spread it when classes get to large.
     */

    // only examples
    private Player player1, player2;
    private Stage stage;
    private SoundBar soundBar;

    // constructor
    public Model() {
        this.soundBar = new SoundBar();

        /*
        TODO:

        should initate all non-game objects

         */
    }

    // TODO: add description
    public SoundBar getSoundBar() {
        return this.soundBar.getSoundBar();
    }

    // TODO: add description
    public void changeSound() {
        this.soundBar.getSoundBar().changeSound();
    }

    // TODO: add description
    public void move(Boolean left) {
        // TODO: update appropriate objects
    }

    // TODO: add description
    public void buyArrow(String type) {
        // TODO: update appropriate objects
    }

    // TODO: add description
    public void drawBow(Vector2 vector2) {
        // TODO: update appropriate objects
    }

    // TODO: add description
    public void initiateGame() {
         /*
        TODO:

        should initate all game objects (e.g. Player, arraw)

         */
    }
}
