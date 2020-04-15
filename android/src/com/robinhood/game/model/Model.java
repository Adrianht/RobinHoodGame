package com.robinhood.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
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

    // box2D
    // TODO: make world private and make getter
    public World world;
    private EntityFactory entityFactory;


    // Method to initiate a new game after two players are matched
    // TODO-Ola: coordinate with controller.initateGame-input
    // TODO: separate into helper-methods
    public void initiateGame(String username1, String username2) {

        // initiate box2d
        world = new World(new Vector2(0,-10f), true);
        entityFactory = new EntityFactory(world);


        // Initiate ground entity
        entities.add(entityFactory.createGround());


        // Initiate player entities
        // FIXME: try to avoid hard codings
        //int[][] startPositions = {{30, 200},{400, 200}};
        int[] bodyDefPos = {-10, 10};
        String[] playerNames = {username1, username2};
        //String[] playerColor = {"RED", "BLUE"};
        for (int i = 0; i < nrOfPlayers; i++) {
            Entity player = entityFactory.createPlayer(
                playerNames[i],
                bodyDefPos[i],
                i
            );
            entities.add(player);
        }


        // Initiate arrow
        entities.add(entityFactory.newArrow());


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
            userInputSystem.moveLeft(world, entities);
        } else {
            userInputSystem.moveRight(world, entities);
        }
        //entities.add(entityFactory.newArrow());
    }

    /* Method called from Controller to buy an arrow, the check and update of weapon type is done
    * in Systems.java  */
    public void buyArrow(String type) {
        userInputSystem.buyArrow(entities, type);
    }

    // Method runs animation and change players turn
    public void drawBow(Vector2 vector2) {
        System.out.println("draw called");
        animationSystem.arrowAnimation(world, entities, vector2);
        userInputSystem.changeTurn(entities, nrOfPlayers);
        entities.add(entityFactory.newArrow());
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
