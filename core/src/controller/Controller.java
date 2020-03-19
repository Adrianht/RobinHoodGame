package controller;

import com.badlogic.gdx.math.Vector2;
import com.robinhood.game.RobinHood;

import model.Model;
import view.LoadingView;
import view.MenuView;
import view.SettingsView;

public class Controller extends RobinHood {

    // only examples
    private Model model;
    private RobinHood game;
    private FirebaseConnector fbConn;

    public Controller(RobinHood game) {
        this.game = game;
        this.model = new Model();

        //game.setView(new SettingsView(this, model));
    }

    // Method called from views to update fb and model
    public void move(Boolean left) {
        fbConn.move(left);
        model.move(left);
    }

    // Method called from views to update fb and model
    public void buyArrow(String type) {
        fbConn.buyArrow(type);
        model.buyArrow(type);
    }

    // Method called from views to update fb and model
    public void drawBow(Vector2 vector2) {
        fbConn.drawBow(vector2);
        model.drawBow(vector2);
    }

    // Method called from views to navigate through the application
    public void navigateTo(String destination) {
        switch(destination) {
            case "MENU":
                game.setView(new MenuView(this));
                break;
            case "SETTINGS":
                game.setView(new SettingsView(this, model));
                break;
            case "LOADING":
                game.setView(new LoadingView(this, model));
                break;
            case "GAME":
                // code block
                break;
            default:
                // code block
        }
    }

    // Method to call model about sound settings change
    public void changeSound() {
        model.changeSound();
    }

    // Method to initate Firebase-connector and find another player
    public void findPlayer() {
        this.fbConn = new FirebaseConnector(this);
    }

    // Method called to initiate game after Firebase has found opponent
    public void initiateGame() {
        model.initiateGame();
    }

    // TODO: there should be a method callable from MenuView to quit the game
}
