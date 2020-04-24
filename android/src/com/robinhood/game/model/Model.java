package com.robinhood.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.robinhood.game.assetManagers.AudioManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing Model in Model-View-Controller.
 * Holds all data and runs the game loop.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class Model {

    // Box2D-related fields
    private World world;
    private Body[] collidingBodies;

    // ECS-related fields
    private List<Entity> entities;
    private Systems systems;

    // Game data fields
    private String myUsername = "Username";
    private int myEnergyPoints;
    private boolean isMyTurn;
    private String userInput, gameWinner;
    private Entity[] playerEntities;
    private Entity activeArrowEntity;

    // Methods to set up or tear down game info fields
    public void initiateGame(List<String> usernames) {
        world = new World(new Vector2(0,-98f), true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                collidingBodies = new Body[2];
                collidingBodies[0] = contact.getFixtureA().getBody();
                collidingBodies[1] = contact.getFixtureB().getBody();
                AudioManager.getInstance().playSound("hit");
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }
        });

        entities = new ArrayList<>();
        createEntity("arrow");
        createEntity("ground");
        int playerSpace = 24 / (usernames.size()-1);
        // TODO: remove: setIsMyTurn(myUsername.equals(usernames.get(0)));
        this.playerEntities = new Entity[usernames.size()];
        for (int i = 0; i < usernames.size(); i++) {
            Entity playerEntity = createEntity(
                    "player",
                    playerSpace*i - 12);
            playerEntity.components.playerInfo.index = i;
            playerEntity.components.playerInfo.username =
                    usernames.get(i);
            playerEntity.components.playerInfo.isPlayersTurn =
                    i == 0;
            this.playerEntities[i] = playerEntity;
        }

        systems = new Systems(this);
        systems.GameInfoSystem(entities);
    }

    public Entity createEntity(String type) {
        return createEntity(type, 0);
    }

    private Entity createEntity(String type, float posX) {
        Entity entity;
        if(type.equals("arrow")) {
            entity = ArrowEntityPool.getInstance().getObject();
        } else {
            entity = new Entity();
            entity.addComponent("box2dBody");
            entity.components.box2dBody.body =
                    BodyFactory.getInstance().getBody(type, world, posX);
            if(type.equals("player")) {
                entity.addComponent("playerInfo");
            }
        }
        entities.add(entity);
        return entity;
    }

    public void resetModelData() {
        entities.clear();
        gameWinner = null;
        playerEntities = null;
    }

    // Method called on change in Firebase Real-time db
    public void notifyChangeInFirebase(String userInput) {
        this.userInput = userInput;
        gameLoop();
    }

    private void gameLoop() {
        systems.UserInputSystem(entities);
        systems.AnimationSystem(entities);
        systems.GameInfoSystem(entities);
        systems.action = "";
        collidingBodies = null;
    }

    // Field getters
    public World getWorld() {
        return world;
    }
    public Body[] getCollidingBodies() {
        return collidingBodies;
    }
    public Entity[] getPlayerEntities() {
        return playerEntities;
    }
    public Entity getActiveArrowEntity() {
        return activeArrowEntity;
    }
    public String getMyUsername() {
        return myUsername;
    }
    public String getGameWinner() {
        return gameWinner;
    }
    public String getUserInput() {
        return userInput;
    }
    public int getMyEnergyPoints(){
        return myEnergyPoints;
    }
    public boolean isMyTurn(){
        return isMyTurn;
    }
    public boolean isGameInitialized() {
        return playerEntities != null;
    }

    // Field setters
    public void setMyUsername(String username) {
        this.myUsername = username;
    }
    public void setGameWinner(String gameWinner) {
        this.gameWinner = gameWinner;
    }
    public void setMyEnergyPoints(int myEnergyPoints) {
        this.myEnergyPoints = myEnergyPoints;
    }
    public void setIsMyTurn(boolean isMyTurn) {
        this.isMyTurn = isMyTurn;
    }
    public void setActiveArrowEntity(Entity activeArrowEntity) {
        this.activeArrowEntity = activeArrowEntity;
    }
}
