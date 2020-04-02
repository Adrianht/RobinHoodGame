package model;

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
    private SoundBar soundBar;

    // ECS related fields - list index might be used as entity id
    private List<Entity> entities = new ArrayList<>();
    private Systems.Render renderingSystem;
    private Systems.Animation animationSystem;
    private Systems.UserInput userInputSystem;

    // ECS related sources - remove pre delivery:
    // http://vasir.net/blog/game-development/how-to-build-entity-component-system-in-javascript
    // related GitHub-repo: https://github.com/erikhazzard/RectangleEater/tree/master/scripts

    // https://codereview.stackexchange.com/questions/163215/entity-component-system-ecs


    // Method to initiate a new game after two players are matched
    public void initiateGame() {

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
        String[] playerNames = {"LARS", "NINA"};
        String[] playerColor = {"RED", "BLUE"};
        for (int i = 0; i < 2; i++) {
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

    // TODO: add description
    public void buyArrow(String type) {
        // TODO: update appropriate objects
    }

    // Method runs animation and change players turn
    public void drawBow(Vector2 vector2) {
        boolean shotIsVital = animationSystem.arrowAnimationShotIsVital(entities, vector2);
        if (shotIsVital) {
            // TODO: GAME OVER
        }
        userInputSystem.changeTurn(entities);
    }

    // Method that return this game instance's soundbar object
    public SoundBar getSoundBar() {
        return this.soundBar.getSoundBar();
    }

    // Method used to change sound setting
    public void changeSound() {
        this.soundBar.getSoundBar().changeSound();
    }

}
