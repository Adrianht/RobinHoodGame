package com.robinhood.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.robinhood.game.RobinHood;

<<<<<<< HEAD:android/src/com/robinhood/game/controller/Controller.java
import com.robinhood.game.model.Model;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
=======
import model.Model;
import view.GameView;
import view.LoadingView;
import view.MenuView;
import view.SettingsView;
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/controller/Controller.java

public class Controller {

    private Model model;
    private RobinHood game;
    private DatabaseReference fbConn;


    public Controller(RobinHood game) {
        this.game = game;
        this.model = new Model();
        fbConn = FirebaseDatabase.getInstance().getReference();

    }

<<<<<<< HEAD:android/src/com/robinhood/game/controller/Controller.java
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
=======
    // Method called from views to navigate through the application
    public void navigateTo(String destination) {
        switch(destination) {
            case "MENU":
                game.setView(new MenuView(this));
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/controller/Controller.java
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

<<<<<<< HEAD:android/src/com/robinhood/game/controller/Controller.java
    // Method to call com.robinhood.game.model about sound settings change
=======
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
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/controller/Controller.java
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

    // TODO: description
    public void exitApplication() {
        // TODO: this should be a method callable from MenuView to quit the application
    }
}
