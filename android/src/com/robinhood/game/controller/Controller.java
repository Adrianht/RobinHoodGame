package com.robinhood.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.robinhood.game.RobinHood;

import com.robinhood.game.model.Model;
import com.robinhood.game.view.*;
import java.util.List;

public class Controller {

    private RobinHood game;
    private FBConnector fbconnector;
    private Model model;
    private final int nrOfPlayers = 2;

    public Controller(RobinHood game) {
        this.game = game;
        this.fbconnector = new FBConnector(this);
        this.model = new Model();
    }

    // Method called from views to navigate through the application
    public void navigateTo(String destination) {
        switch(destination) {
            case "MENU":
                game.setView(new MenuView(this));
                break;
            case "SETTINGS":
                game.setView(new SettingsView(this));
                break;
            case "LOADING":
                game.setView(new LoadingView(this, model));
                break;
            case "GAME":
                game.setView(new GameView(this, model));
                break;
            case "GAMEOVER":
                game.setView(new GameOverView(this,model));
                break;
            default:
                game.setView(new MenuView(this));
        }
    }

    // Method called from GameView to update fb
    public void move(Boolean left) {
        if(model.isMyTurn()) {
            fbconnector.setMove(left);
        }
    }

    // Method called from FBConnector to update model
    public void registerMove(Boolean left) {
        model.move(left);
    }

    // Method called from views to update fb
    public void buyArrow(String type) {
        if(model.isMyTurn()) {
            fbconnector.setBuy(type);
        }
    }

    // Method called from FBConnector to update model
    public void registerBuy(String type) {
        model.buyArrow(type);
    }

    // Method called from views to update fb and model
    public void drawBow(Vector2 vector2) {
        if(model.isMyTurn()) {
            fbconnector.setDraw(vector2);
        }
    }

    // Method called from views to update fb and model
    public void registerDraw(Vector2 vector2) {
        model.drawBow(vector2);
    }

    // Method called from GameView when only one player has hp
    public void handleGameOver() {
        this.navigateTo("GAMEOVER");
    }

    // Method called to delete game data from firebase and model
    public void endGameInstance() {
        fbconnector.removeRoom();
        model.resetModelData();
    }

    // NEW Music/Sound methods
    public boolean getMusicEnabled() {
        return model.getMusicEnabled();
    }

    public void setMusicEnabled(boolean enabled) { model.setMusicEnabled(enabled); }

    public boolean getSoundEnabled() {
        return model.getSoundEnabled();
    }

    public void setSoundEnabled(boolean enabled) { model.setSoundEnabled(enabled); }

    // Method to initiate Firebase-connector and find another player
    public void findPlayer() {
        fbconnector.findPlayers(model.getMyUsername(), nrOfPlayers);
    }

    // Method to cancel a players search for opponent
    public void cancelFindPlayer() {
        fbconnector.cancelFindPlayer(model.getMyUsername());
    }

    // Method called to initiate game after Firebase has found opponent
    public void initiateGame(List<String> usernames) {
        model.initiateGame(usernames);
    }

    // Method returns if game is initialized
    public boolean isGameInitialized() {
        return model.isGameInitialized();
    }

    // Method to exit application, called from MenuView
    public void exitApplication() {
        Gdx.app.exit();
    }

    // Method call model about players hit point values
    public List<Integer> getHP(){
        return model.getHP();
    }

    // Method call model about players energy values
    public List<Integer> getEnergy(){
        return model.getEnergy();
    }

    // Method sets username in model
    public void setUsername(String username){
        model.setMyUsername(username);
    }

}
