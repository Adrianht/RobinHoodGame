package com.robinhood.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.robinhood.game.RobinHood;

import com.robinhood.game.model.Model;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class Controller extends RobinHood {

    // only examples
    private Model model;
    private RobinHood game;
    private DatabaseReference fbConn;


    public Controller(RobinHood game) {
        this.game = game;
        this.model = new Model();
        fbConn = FirebaseDatabase.getInstance().getReference();

    }

    // Method called from com.robinhood.game.views to update fb and com.robinhood.game.model
    public void move(Boolean left) {
        fbConn.setValue(left);
        model.move(left);
    }

    // Method called from com.robinhood.game.views to update fb and com.robinhood.game.model
    public void buyArrow(String type) {
        fbConn.setValue(type);
        model.buyArrow(type);
    }

    // Method called from com.robinhood.game.views to update fb and com.robinhood.game.model
    public void drawBow(Vector2 vector2) {
        fbConn.setValue(vector2);
        model.drawBow(vector2);
    }

    // Method called from com.robinhood.game.views to navigate through the application
    public void navigateTo(String destination) {
        switch(destination) {
            case "MENU":
                // super.setView(new MenuView(this, com.robinhood.game.model));
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

    // Method to call com.robinhood.game.model about sound settings change
    public void changeSound() {
        model.changeSound();
    }

    // Method to initate Firebase-connector and find another player
    //public void findPlayer() {
    //    this.fbConn = new FirebaseConnector(this);
    //}

    // Method called to initiate game after Firebase has found opponent
    public void initiateGame() {
        model.initiateGame();
    }

    // TODO: there should be a method callable from MenuView to quit the game
}
