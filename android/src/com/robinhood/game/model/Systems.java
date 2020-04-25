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
        Entity activeArrow = findActiveArrow(entities);
        Entity activePlayer = findActivePlayer(entities);
        Body playerBody = activePlayer.components.box2dBody.body;

        if (userInput.equals("left") || userInput.equals("right")) {
            if(activePlayer.components.playerInfo.energy > 1
                    && Math.abs(playerBody.getPosition().x) < 13) {
                playerBody.setLinearVelocity(-2, 0);
                if (userInput.equals("right")) {
                    playerBody.setLinearVelocity(2, 0);
                }
                activePlayer.components.playerInfo.energy -= 2;
                action = "move";
            }
        } else if (userInput.substring(0,3).equals("Lev")) {
            // "Level2" -> cost: 20, damage: 20
            // "Level3" -> cost: 40, damage: 40
            // "Level4" -> cost: 60, damage: 60
            int purchaseLevel = Integer.parseInt(
                    userInput.substring(userInput.length() - 1));
            int purchaseCostNDam = (purchaseLevel-1) * 20;
            if (activePlayer.components.playerInfo.energy
                    >= purchaseCostNDam) {
                activePlayer.components.playerInfo.energy
                        -= purchaseCostNDam;
                activeArrow.components.arrowType.type = userInput;
                activeArrow.components.arrowType.damage =
                        purchaseCostNDam;
            }
        } else {
            activeArrow.components.box2dBody.body =
                    BodyFactory.getInstance().getBody(
                            "arrow",
                            model.getWorld(),
                            playerBody.getPosition().x,
                            userInput);
            action = "draw";
        }
    }

    public void AnimationSystem(List<Entity> entities) {
        if (action.equals("move")) {
            Entity activePlayer = findActivePlayer(entities);
            while (activePlayer.components.box2dBody.body
                    .getLinearVelocity().x != 0) {
                model.getWorld().step(
                        .001f,
                        1,
                        1);
            }
        } else if (action.equals("draw")) {
            Entity activeArrow = findActiveArrow(entities);
            Vector2 vector2 = new Vector2()
                    .fromString(model.getUserInput());
            activeArrow.components.box2dBody.body
                    .setLinearVelocity(vector2.scl(-.05f));
            while(model.getCollidingBodies() == null) {
                model.getWorld().step(
                        0.0005f,
                        1,
                        1);
                model.setFlyingArrowEntity(activeArrow);
            }
            model.setFlyingArrowEntity(null);
        }
    }

    public void GameInfoSystem(List<Entity> entities) {
        List<Entity> players = findPlayers(entities);
        Entity activePlayer = findActivePlayer(entities);
        Entity activeArrow = findActiveArrow(entities);
        Body[] collidingBodies = model.getCollidingBodies();

        if(action.equals("draw")) {
            for(Entity player: players) {
                Components.PlayerInfo playerInfo =
                        player.components.playerInfo;
                Body playerBody =
                        player.components.box2dBody.body;
                if(playerBody == collidingBodies[0]
                        || playerBody == collidingBodies[1]) {
                    playerBody.setLinearVelocity(0,0);
                    playerInfo.hitPoints -=
                            activeArrow.components.arrowType.damage;
                    if(playerInfo.hitPoints <= 0) {
                        playerInfo.hitPoints = 0;
                        model.getWorld().destroyBody(
                                player.components.box2dBody.body);
                        player.components.box2dBody.body = null;
                    }
                }
                if(player == activePlayer) {
                    playerInfo.isPlayersTurn = false;
                }
            }

            int prevIndex = activePlayer.components.playerInfo.index;
            Entity nextActivePlayer =
                    findNextActivePlayer(players, prevIndex);
            if(nextActivePlayer == activePlayer) {
                model.setGameWinner(
                        activePlayer.components.playerInfo.username);
            } else {
                nextActivePlayer.components.playerInfo.isPlayersTurn = true;
                if (nextActivePlayer.components.playerInfo.energy > 90) {
                    nextActivePlayer.components.playerInfo.energy = 100;
                } else {
                    nextActivePlayer.components.playerInfo.energy += 10;
                }
            }
            ArrowEntityPool.getInstance().releaseObject(activeArrow);
        }
    }

    // Systems helper methods
    private static List<Entity> findPlayers(List<Entity> entities) {
        List<Entity> players = new ArrayList<>();
        for(Entity entity: entities) {
            if (entity.components.playerInfo != null) {
                players.add(entity);
            }
        }
        return players;
    }

    private static Entity findActivePlayer(List<Entity> entities) {
        for(Entity entity: entities) {
            if (entity.components.playerInfo != null &&
                    entity.components.playerInfo.isPlayersTurn) {
                return entity;
            }
        }
        return null;
    }

    private static Entity findActiveArrow(List<Entity> entities) {
        for(Entity entity: entities) {
            if (entity.components.arrowType != null) {
                return entity;
            }
        }
        return null;
    }

    private static Entity findNextActivePlayer(
            List<Entity> players,
            int prevIndex) {
        Entity nextPlayerEntity = null;
        int nextActiveIndex = (prevIndex + 1) % players.size();
        while(nextPlayerEntity == null) {
            for(Entity player: players) {
                Components.PlayerInfo playerInfo =
                        player.components.playerInfo;
                if(playerInfo.index == nextActiveIndex
                        && playerInfo.hitPoints > 0) {
                    nextPlayerEntity = player;
                    break;
                }
            }
            nextActiveIndex = (nextActiveIndex+1) % players.size();
        }
        return nextPlayerEntity;
    }
}
