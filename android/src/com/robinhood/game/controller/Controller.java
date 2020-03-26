package com.robinhood.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.robinhood.game.RobinHood;

import com.robinhood.game.model.Model;
import com.robinhood.game.view.*;



public class Controller {

    private Model model;
    private RobinHood game;
    private MatchMaker matchMaker;

    public Controller(RobinHood game) {
        this.game = game;
        this.model = new Model();
        this.matchMaker = new MatchMaker();
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
                //TODO: all from somewhere else
                model.initiateGame();

                game.setView(new GameView(this, model));
                break;
            default:
                game.setView(new MenuView(this));
        }
    }

    // Method called from views to update fb and model
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
        matchMaker.initFindPlayer();
    }

    // Method called to initiate game after Firebase has found opponent
    public void initiateGame() {
        model.initiateGame();
    }

    // TODO: description
    public void exitApplication() {
        // TODO: this should be a method callable from MenuView to quit the application
    }
}
