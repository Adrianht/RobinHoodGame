package com.robinhood.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.robinhood.game.RobinHood;

import com.robinhood.game.model.Model;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.robinhood.game.view.*;

import java.util.Random;
import java.util.UUID;

public class Controller {

    private Model model;
    private RobinHood game;
    private DatabaseReference mDatabase;
    UUID uuid = UUID.randomUUID();


    Random rand = new Random();
    int gameRoom;
    String gameRoomUID;
    int playerName;



    public Controller(RobinHood game) {
        this.game = game;
        this.model = new Model();
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        this.gameRoomUID = uuid.toString();
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
        mDatabase.child(gameRoomUID).setValue(type);
        //fbConn.buyArrow(type);
        model.buyArrow(type);
    }

    // Method called from views to update fb and model
    public void drawBow(Vector2 vector2) {
        mDatabase.child(gameRoomUID).setValue(vector2);
        //fbConn.drawBow(vector2);
        model.drawBow(vector2);
    }

    // Method to call model about sound settings change
    public void changeSound() {
        model.changeSound();
    }

    // Method to initate Firebase-connector and find another player
    public void findPlayer() {
        playerName = rand.nextInt(1000);
        mDatabase.child("rooms").child(gameRoomUID).setValue(playerName);
    }

    // Method called to initiate game after Firebase has found opponent
    public void initiateGame() {
        model.initiateGame();
    }

    // TODO: description
    public void exitApplication() {
        // TODO: this should be a method callable from MenuView to quit the application
        Gdx.app.exit();
    }
}
