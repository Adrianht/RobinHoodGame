package com.robinhood.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing System in Entity-Component-System.
 * Holds all systems of the game.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class Systems {

    private final Model model;
    public String action = "";

    public Systems(Model model) {
        this.model = model;
    }

    public void UserInputSystem(List<Entity> entities) {
        String userInput = model.getUserInput();
        Entity[] activePlayerAndArrow =
                findActivePlayerAndArrow(entities);

        if (userInput.equals("left") || userInput.equals("right")) {
            if(activePlayerAndArrow[0].components.playerInfo.energy > 1) {
                activePlayerAndArrow[0]
                        .components.box2dBody.body
                        .setLinearVelocity(-1, 0);
                if (userInput.equals("right")) {
                    activePlayerAndArrow[0]
                            .components.box2dBody.body
                            .setLinearVelocity(1, 0);
                }
                activePlayerAndArrow[0]
                        .components.playerInfo.energy -= 2;
                action = "move";
            }
        } else if (userInput.substring(0,3).equals("Lev")) {
            // Level 2 arrow - cost: 20, damage: 20
            // Level 3 arrow - cost: 40, damage: 40
            // Level 4 arrow - cost: 60, damage: 60
            int purchaseLevel = Integer.parseInt(
                    userInput.substring(userInput.length() - 1));
            int purchaseCostNDam = (purchaseLevel-1) * 20;
            if (activePlayerAndArrow[0].components.playerInfo.energy
                    >= purchaseCostNDam) {
                activePlayerAndArrow[0]
                        .components.playerInfo.energy -= purchaseCostNDam;
                activePlayerAndArrow[1]
                        .components.arrowType.damage = purchaseCostNDam;
            }
        } else {
            action = "draw";
        }
    }

    public void AnimationSystem(List<Entity> entities) {
        Entity[] activePlayerAndArrow = findActivePlayerAndArrow(entities);

        if (action.equals("move")) {
            activePlayerAndArrow[1]
                    .components.box2dBody.body.setActive(false);
            while (activePlayerAndArrow[0].components.box2dBody.body
                    .getLinearVelocity().x != 0) {
                model.getWorld().step(
                        .001f,
                        1,
                        1);
            }
            activePlayerAndArrow[1]
                    .components.box2dBody.body.setActive(true);
        } else if (action.equals("draw")) {
            Vector2 vector2 = new Vector2().fromString(model.getUserInput());
            activePlayerAndArrow[1]
                    .components.box2dBody.body
                    .setLinearVelocity(vector2.scl(-.05f));

            Body[] collidingBodies = null;
            while(collidingBodies == null) {
                model.getWorld().step(
                        .001f,
                        1,
                        1);
                collidingBodies = model.getCollidingBodies();
            }
            Body hitBody = collidingBodies[0];
            if(collidingBodies[0] == activePlayerAndArrow[1]
                    .components.box2dBody.body) {
                hitBody = collidingBodies[1];
            }
            hitBody.setLinearVelocity(0,0);

            List<Entity> players = findPlayers(entities);
            for(Entity player: players) {
                if(hitBody == player.components.box2dBody.body) {
                    player.components.playerInfo.hitPoints -=
                            activePlayerAndArrow[1]
                                    .components.arrowType.damage;
                    break;
                }
            }

            activePlayerAndArrow[1]
                    .removeComponent("arrowType");
            model.getWorld().destroyBody(
                    activePlayerAndArrow[1].components.box2dBody.body);
        }
    }

    public void GameInfoSystem(List<Entity> entities) {
        List<Entity> players = findPlayers(entities);
        String myUsername = model.getMyUsername();
        Entity[] activePlayerAndArrow = findActivePlayerAndArrow(entities);
        String activePlayerUsername =
                activePlayerAndArrow[0].components.playerInfo.username;

        if(action.equals("draw")) {
            int prevActiveIndex =
                    activePlayerAndArrow[0].components.playerInfo.index;
            int nextActiveIndex = (prevActiveIndex + 1) % players.size();
            for(Entity player: players){
                if (player.components.playerInfo.index == nextActiveIndex) {
                    activePlayerUsername =
                            player.components.playerInfo.username;
                    if (player.components.playerInfo.energy > 90) {
                        player.components.playerInfo.energy = 100;
                    } else {
                        player.components.playerInfo.energy += 10;
                    }
                    break;
                }
            }
        }

        int[] hitPointValues = new int[players.size()];
        int survivorCount = 0;
        String possibleWinner = "";
        for(Entity player: players){
            player.components.playerInfo.isPlayersTurn =
                    activePlayerUsername.equals(
                            player.components.playerInfo.username);
            model.setIsMyTurn(myUsername.equals(activePlayerUsername));
            if (myUsername.equals(player.components.playerInfo.username)){
                model.setMyEnergyPoints(player.components.playerInfo.energy);
            }
            hitPointValues[player.components.playerInfo.index] =
                    player.components.playerInfo.hitPoints;
            if(player.components.playerInfo.hitPoints > 0) {
                survivorCount++;
                possibleWinner = player.components.playerInfo.username;
            }
        }
        if (survivorCount < 2) {
            model.setGameWinner(possibleWinner);
        }
        model.setHitPointValues(hitPointValues);
    }

    private static List<Entity> findPlayers(List<Entity> entities) {
        List<Entity> players = new ArrayList<>();
        for(Entity entity: entities) {
            if (entity.components.playerInfo != null) {
                players.add(entity);
            }
        }
        return players;
    }

    private static Entity[] findActivePlayerAndArrow(List<Entity> entities) {
        Entity[] activePlayerAndArrow = new Entity[2];
        for(Entity entity: entities) {
            if (entity.components.playerInfo != null &&
                    entity.components.playerInfo.isPlayersTurn) {
                activePlayerAndArrow[0] = entity;
            } else if (entity.components.arrowType != null) {
                activePlayerAndArrow[1] = entity;
            }
        }
        return activePlayerAndArrow;
    }
}
