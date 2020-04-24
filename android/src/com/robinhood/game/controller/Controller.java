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

    // Observer pattern methods used by view package to navigate between views
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

    // Observer pattern methods used communicate with FirebaseRtD
    public void findPlayers() {
        fbconnector.findPlayers(
                model.getMyUsername(),
                nrOfPlayers);
    }

    public void cancelFindPlayer() {
        fbconnector.cancelFindPlayer(model.getMyUsername());
    }

    public void actionToFirebase(String action) {
        fbconnector.exportActionToFirebase(action);
    }

    public void notifyChangeInFirebase(String userInput) {
        model.notifyChangeInFirebase(userInput);
    }

    public void endGameInstance() {
        fbconnector.removeRoom();
    }

    // Observer pattern methods used communicate with model package
    public void setMyUsername(String username){
        model.setMyUsername(username);
    }

    public void initiateGame(List<String> usernames) {
        model.initiateGame(usernames);
    }

    // Method to exit application
    public void exitApplication() {
        Gdx.app.exit();
    }
}
