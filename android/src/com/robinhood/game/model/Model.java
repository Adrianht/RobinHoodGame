package com.robinhood.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

/*
    Model of the MVC pattern
    Access point for View and the class triggering appropriate
        system functions related to ECS
 */

public class Model {

    // SoundBar is the only class outside ECS because
    //  because it is not active in-game
    // TODO: check soundbar logic and minimalize code
    private SoundBar soundBar;

    private String myUsername = "Username";

    private boolean gameInitialized = false;
    private final int nrOfPlayers = 2;

    // ECS related fields - list index might be used as entity id
    private List<Entity> entities = new ArrayList<>();
    private Systems.Render renderingSystem;
    private Systems.Animation animationSystem;
    private Systems.UserInput userInputSystem;
    private Systems.playerInfo playerInfoSystem;


    // Method to initiate a new game after two players are matched
    // TODO-Ola: coordinate with controller.initateGame-input
    public void initiateGame(String username1, String username2) {

        Entity entity;

        /*
         TODO: initiate Arena entity

        entity = new Entity();
        entity.addComponent("turf");
        entity.addComponent("render");

        entities.add(entity);

         */

        // Initiate player entities
        int[][] startPositions = {{30, 200},{400, 200}};
        String[] playerNames = {username1, username2};
        String[] playerColor = {"RED", "BLUE"};
        for (int i = 0; i < nrOfPlayers; i++) {
            entity = new Entity();
            entity.addComponent("turn");
            if(i == 0) {
                entity.component.turn.turn = true;
            }
            entity.addComponent("playernr");
            entity.component.playernr.nr = i;
            entity.addComponent("energy");
            entity.addComponent("hp");
            entity.addComponent("name");
            entity.component.name.name = playerNames[i];
            entity.addComponent("pos");
            entity.component.pos.x = startPositions[i][0];
            entity.component.pos.y = startPositions[i][1];
            entity.addComponent("actor");

            //TODO : possibly create help-method of following switch
            switch(playerColor[i]) {
                case "RED":
                    entity.component.actor.sprite = new Sprite(new Texture("redarcher.png"));
                    break;
                case "BLUE":
                    entity.component.actor.sprite = new Sprite(new Texture("bluearcher.png"));
                    break;
                default:
                    entity.component.actor.sprite = new Sprite(new Texture("redarcher.png"));
                    break;
            }
            entity.component.actor.sprite.setSize(200, 200);
            entities.add(entity);
        }

        // Initiate arrow
        entity = new Entity();
        entity.addComponent("pos");
        entity.component.pos.x = 30;
        entity.component.pos.y = 200;
        entity.addComponent("actor");
        entity.component.actor.sprite = new Sprite(new Texture("arrow.png"));
        entity.component.actor.sprite.setSize(100, 100);
        entity.addComponent("arrowType");
        entities.add(entity);

        // Initiate game system possibilities
        renderingSystem = new Systems.Render();
        userInputSystem = new Systems.UserInput();
        animationSystem = new Systems.Animation();
        playerInfoSystem = new Systems.playerInfo();

        this.gameInitialized = true;
    }

    // Called in GameView to return all active actors
    //  includes archers, arrow(s), and arena
    public List<Actor> getActors() {
        return renderingSystem.getActors(entities);
    }

    // Method called from Controller to move the active player
    public void move(Boolean left) {
        if(left) {
            userInputSystem.moveLeft(entities);
        } else {
            userInputSystem.moveRight(entities);
        }
    }

    /* Method called from Controller to buy an arrow, the check and update of weapon type is done
    * in Systems.java  */
    public void buyArrow(String type) {
        userInputSystem.buyArrow(entities, type);
    }

    // Method runs animation and change players turn
    public void drawBow(Vector2 vector2) {
        animationSystem.arrowAnimation(entities, vector2);
        userInputSystem.changeTurn(entities, nrOfPlayers);
    }

    // Method that return this game instance's soundbar object
    public SoundBar getSoundBar() {
        return this.soundBar.getSoundBar();
    }

    // Method used to change sound setting
    public void changeSound() {
        this.soundBar.getSoundBar().changeSound();
    }

    // Method used to fetch players hit point values
    public List<Integer> getHP(){
        return playerInfoSystem.getHP(entities, nrOfPlayers);
    }

    // Method used to fetch players energy values
    public List<Integer> getEnergy(){
        return playerInfoSystem.getEnergyPoints(entities, nrOfPlayers);
    }

    // Method used to check if it's this player's turn
    public boolean isMyTurn(){
        return playerInfoSystem.isMyTurn(entities, myUsername);
    }

    // Method returns username
    public String getMyUsername() {
        return myUsername;
    }

    // Method set username
    public void setMyUsername(String username) {
         this.myUsername = username;
    }

    // Method returns if game is initialized
    public boolean isGameInitialized() {
        return gameInitialized;
    }

    // Method returns if game is initialized
    public void resetModelData() {
        entities.clear();
        gameInitialized = false;
    }

}
