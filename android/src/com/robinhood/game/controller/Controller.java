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

    private final RobinHood game;
    private final FBConnector fbconnector;
    private final Model model = new Model();
    private final int nrOfPlayers = 2;

    public Controller(RobinHood game) {
        this.game = game;
        this.fbconnector = new FBConnector(this);
    }

    // Navigation method called from views
    public void navigateTo(String destination) {
        switch(destination) {
            case "SETTINGS":
                game.setView(new SettingsView(this, model));
                break;
            case "LOADING":
                game.setView(new LoadingView(this, model));
                break;
            case "INSTRUCTIONS":
                game.setView(new InstructionsView(this));
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

    // Called from FBConnector to notify model of change
    public void notifyChangeInFirebase(String userInput) {
        model.notifyChangeInFirebase(userInput);
    }

    // Reset model and firebase game room
    public void endGameInstance() {
        fbconnector.removeRoom();
        model.resetModelData();
    }

    // Method called to enable/disable game music
    public void setMusicEnabled(boolean enabled) {
        model.setMusicEnabled(enabled);
    }

    // Method called to enable/disable game sounds
    public void setSoundEnabled(boolean enabled) {
        model.setSoundEnabled(enabled);
    }

    // Notify firebase of an available player
    public void findPlayers() {
        fbconnector.findPlayers(
                model.getMyUsername(),
                nrOfPlayers);
    }

    // Method to cancel a players search for opponent
    public void cancelFindPlayer() {
        fbconnector.cancelFindPlayer(model.getMyUsername());
    }

    // Initate game when opponent(s) are found
    public void initiateGame(List<String> usernames) {
        model.initiateGame(usernames);
    }

    // Method to exit application, called from MenuView
    public void exitApplication() {
        Gdx.app.exit();
    }

    // Method sets username in model
    public void setMyUsername(String username){
        model.setMyUsername(username);
    }
}
