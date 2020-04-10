package controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.robinhood.game.RobinHood;

import java.util.List;

import model.Model;
import view.*;

public class Controller {

    private Model model;
    private RobinHood game;

    public Controller(RobinHood game) {
        this.game = game;
        this.model = new Model();
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
                //TODO: this method should be called from somewhere else
                model.initiateGame();

                game.setView(new GameView(this, model));
                break;
            case "GAMEOVER":
                game.setView(new GameOverView(this,model));
                break;
            default:
                game.setView(new MenuView(this));
        }
    }

    // Method called from GameView to update fb and model
    public void move(Boolean left) {
        //fbConn.move(left);
        model.move(left);
    }

    // Method called from views to update fb and model
    public void buyArrow(String type) {
        //fbConn.buyArrow(type);
        model.buyArrow(type);
    }

    // Method called from views to update fb and model
    public void drawBow(Vector2 vector2) {
        //fbConn.drawBow(vector2);
        model.drawBow(vector2);
    }

    // Method to call model about sound settings change
    public void changeSound() {
        model.changeSound();
    }

    // Method to initate Firebase-connector and find another player
    public void findPlayer() {
    }

    // Method called to initiate game after Firebase has found opponent
    public void initiateGame() {
        model.initiateGame();
    }

    // Method to exit application, called from MenuView
    public void exitApplication() {
        Gdx.app.exit();
    }

    public List<Integer> getHP(){
        return model.getHP();
    }
    public List<Integer> getEnergy(){
        return model.getEnergy();
    }
}
