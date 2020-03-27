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
    private Arena arena;
    private SoundBar soundBar;
    private Boolean player1turn = true;

    // constructor
    public Model() {
        this.soundBar = new SoundBar();
    }

    // TODO: add description
    public SoundBar getSoundBar() {
        return this.soundBar.getSoundBar();
    }

    // TODO: add description
    public void changeSound() {
        this.soundBar.getSoundBar().changeSound();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    // TODO: add description
    public void move(Boolean left) {
        // TODO: update appropriate objects
        if(player1turn && player1.getEnergyPoints() > 0) {
            if (left) {
                player1.getArcher().moveBy(-10, 0);
                player1.setEnergyPoints(player1.getEnergyPoints()-2); //It costs 2 points for each move, can change this
                System.out.println("Player 1 moved left, energy points reduced to: " + player1.getEnergyPoints());
            } else {
                player1.getArcher().moveBy(10, 0);
                player1.setEnergyPoints(player1.getEnergyPoints()-2); //It costs 2 points for each move, can change this
                System.out.println("Player 1 moved right, energy points reduced to: " + player1.getEnergyPoints());
            }
        } else if (player2.getEnergyPoints() > 0) {
            if (left) {
                player2.getArcher().moveBy(-10, 0);
                player2.setEnergyPoints(player1.getEnergyPoints()-2); //It costs 2 points for each move, can change this
                System.out.println("Player 2 moved left, energy points reduced to: " + player2.getEnergyPoints());
            } else {
                player2.getArcher().moveBy(10, 0);
                player2.setEnergyPoints(player1.getEnergyPoints()-2); //It costs 2 points for each move, can change this
                System.out.println("Player 2 moved left, energy points reduced to: " + player2.getEnergyPoints());
            }
        }
        player1turn = !player1turn; //Temporary, should be in method drawBow() below
    }

    // TODO: add description
    public void buyArrow(String type) {
        // TODO: update appropriate objects
    }

    // TODO: add description
    public void drawBow(Vector2 vector2) {
        // TODO: update appropriate objects
        player1turn = !player1turn;
    }

    // TODO: add description
    public void initiateGame() {
        player1 = new Player("LARS", "RED");
        player2 = new Player("NINA", "BLUE");
         /*
        TODO:

        should initate all game objects (e.g. Player, arrow)

         */
    }

}
