package controller;

import com.badlogic.gdx.math.Vector2;
import com.robinhood.game.RobinHood;

import model.Model;
import view.MenuView;

public class Controller extends RobinHood {

    // only examples
    private Model model;
    private RobinHood game;
    private FirebaseConnector fbConn;

    public Controller(RobinHood game) {
        this.game = game;
        this.model = new Model();
        this.fbConn = new FirebaseConnector();
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
                super.setView(new MenuView(this, model));
                break;
            case "SETTINGS":
                // code block
                break;
            case "LOADING":
                // code block
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
}
