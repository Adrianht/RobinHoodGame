package com.robinhood.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

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

    private Boolean MUSIC_ENABLED = true;
    private Boolean SOUND_ENABLED = true;

    // ECS fields
    private List<Entity> entities = new ArrayList<>();
    private Systems systems;

    // Box2D fields
    private World world;
    private EntityFactory entityFactory;

    private boolean gameInitialized = false;

    // Data fields
    private String myUsername = "Username";
    private int[] hitPointValues;
    private int myEnergyPoints;
    private boolean isMyTurn;
    private Body[] collidingBodies;
    private String userInput, gameWinner;

    // Method to initiate a new game after two players are matched
    public void initiateGame(List<String> usernames) {

        // Initiate Box2D
        world = new World(new Vector2(0,-10f), true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                collidingBodies = new Body[2];
                collidingBodies[0] = contact.getFixtureA().getBody();
                collidingBodies[1] = contact.getFixtureB().getBody();
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
        entityFactory = new EntityFactory(world);

        // Initiate entities
        entities.add(entityFactory.createGround());
        entities.add(entityFactory.newArrow());
        int playerSpace = 24 / (usernames.size()-1);
        setIsMyTurn(myUsername.equals(usernames.get(0)));
        for (int i = 0; i < usernames.size(); i++) {
            Entity player = entityFactory.createPlayer(
                usernames.get(i),
                (playerSpace*i - 12),
                i
            );
            entities.add(player);
        }

        // Initiate game systems
        systems = new Systems(this);
        systems.GameInfoSystem(entities);

        this.gameInitialized = true;

        // FIXME: this run on iteration to land players on ground
        //    attempt to find workaround
        world.step(.001f, 1, 1);
    }

    // Method called after every game action
    public void gameLoop() {
        systems.UserInputSystem(entities);
        systems.AnimationSystem(entities);
        systems.GameInfoSystem(entities);
        if (systems.action == "draw") {
            entities.add(entityFactory.newArrow());
        }
        systems.action = "";
        collidingBodies = null;
    }

    // Method called on change in Firebase Real-time db
    public void notifyChangeInFirebase(String userInput) {
        this.userInput = userInput;
        gameLoop();
    }

    // Method returns if game is initialized
    public boolean isGameInitialized() {
        return gameInitialized;
    }

    // Method used to fetch players hit point values
    public int[] getHitPointValues(){
        return hitPointValues;
    }

    public void setHitPointValues(int[] hitPointValues) {
        this.hitPointValues = hitPointValues;
    }

    // Method used to fetch players energy values
    public int getMyEnergyPoints(){
        return myEnergyPoints;
    }

    public void setMyEnergyPoints(int myEnergyPoints) {
        this.myEnergyPoints = myEnergyPoints;
    }

    // Method used to check if it's this player's turn
    public boolean isMyTurn(){
        return isMyTurn;
    }

    public void setIsMyTurn(boolean isMyTurn) {
        this.isMyTurn = isMyTurn;
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
    public void resetModelData() {
        entities.clear();
        gameInitialized = false;
    }

    // NEW Music/Sound methods
    public boolean getMusicEnabled() { return MUSIC_ENABLED; }

    public void setMusicEnabled(boolean enabled) { MUSIC_ENABLED = enabled; }

    public boolean getSoundEnabled() {
        return SOUND_ENABLED;
    }

    public void setSoundEnabled(boolean enabled) { SOUND_ENABLED = enabled; }

    public String getGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(String gameWinner) {
        this.gameWinner = gameWinner;
    }

    public World getWorld() {
        return world;
    }

    public Body[] getCollidingBodies() {
        return collidingBodies;
    }

    public String getUserInput() {
        return userInput;
    }
}
