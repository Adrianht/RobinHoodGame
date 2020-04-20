package com.robinhood.game.controller;

import com.badlogic.gdx.Gdx;
import com.robinhood.game.RobinHood;

import com.robinhood.game.model.Model;
import com.robinhood.game.view.*;
import java.util.List;

/**
 * Class representing Controller in Model-View-Controller.
 * Handles actions from View component and head of
 * the Firebase Connector.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
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
                game.setView(new SettingsView(this, model));
                break;
            case "LOADING":
                game.setView(new LoadingView(this, model));
                break;
            case "GAME":
                game.setView(new GameView(this, model));
                break;
            case "GAMEOVER":
                game.setView(new GameOverView(this, model));
                break;
            default:
                game.setView(new MenuView(this));
        }
    }

    // Method called from GameView to update firebase
    public void actionToFirebase(String action) {
        if(model.isMyTurn()) {
            fbconnector.exportActionToFirebase(action);
        }
    }

    // Method called from FBConnector to notify model of change
    public void notifyChangeInFirebase(String userInput) {
        model.notifyChangeInFirebase(userInput);
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
    public void setMusicEnabled(boolean enabled) { model.setMusicEnabled(enabled); }

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

    // Method sets username in model
    public void setUsername(String username){
        model.setMyUsername(username);
    }

}
