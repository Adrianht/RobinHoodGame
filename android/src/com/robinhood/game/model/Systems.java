package com.robinhood.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

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

    // Inner class handling actions related to user input
    public static class UserInputSystem {

        // Finds and execute move action on active player
        public void moveActivePlayer(
                World world,
                List<Entity> entities,
                boolean moveIsLeft) {
            Entity activeArrow = SystemsHelpers.findActiveArrow(entities);
            activeArrow.components.box2dBody.body.setActive(false);

            Entity activePlayer = SystemsHelpers.findActivePlayer(entities);
            if (activePlayer.components.playerInfo.energy > 0) {
                if (moveIsLeft) {
                    activePlayer.components.box2dBody.body
                            .setLinearVelocity(-1, 0);
                } else {
                    activePlayer.components.box2dBody.body
                            .setLinearVelocity(1, 0);
                }
                while (activePlayer.components.box2dBody.body
                        .getLinearVelocity().x != 0) {
                    world.step(.001f, 1, 1);
                }
                activePlayer.components.playerInfo.energy -= 2;
            }

            activeArrow.components.box2dBody.body.setActive(true);
        }

        // Finds and change the active arrow entity
        public void buyArrow(List<Entity> entities, String type) {

            Entity activePlayer = SystemsHelpers.findActivePlayer(entities);
            Entity activeArrow = SystemsHelpers.findActiveArrow(entities);

            // Level 2 arrow - cost: 20, damage: 20
            if(type.equals("Level2")
                    && activePlayer.components.playerInfo.energy >= 20) {
                activeArrow.components.arrowType.type = "Level2";
                activeArrow.components.arrowType.damage = 20;
                activePlayer.components.playerInfo.energy -= 20;
            }
            // Level 3 arrow - cost: 50, damage: 50
            else if(type.equals("Level3")
                    && activePlayer.components.playerInfo.energy >= 50) {
                activeArrow.components.arrowType.type = "Level3";
                activeArrow.components.arrowType.damage = 50;
                activePlayer.components.playerInfo.energy -= 50;
            }
            // Level 4 arrow - cost: 70, damage: 70
            else if(type.equals("Level4")
                    && activePlayer.components.playerInfo.energy >= 70) {
                activeArrow.components.arrowType.type = "Level4";
                activeArrow.components.arrowType.damage = 70;
                activePlayer.components.playerInfo.energy -= 70;
            }
        }
    }

    // Inner class handling retrieval of player information
    public static class PlayerInfoSystem {

        // Find and return hit point values of all players
        public int[] getPlayersHitPoints(List<Entity> entities){
            List<Entity> players = SystemsHelpers.findPlayers(entities);
            int[] points = new int[players.size()];
            for(Entity player: players){
                points[player.components.playerInfo.index] =
                        player.components.playerInfo.hitPoints;
            }
            return points;
        }

        // Find and return energy points of local player
        public static int getMyEnergyPoints(List<Entity> entities, String username){
            int energyPoints = 0;
            List<Entity> players = SystemsHelpers.findPlayers(entities);
            for (Entity player: players) {
                if (player.components.playerInfo.username.equals(username)){
                    energyPoints = player.components.playerInfo.energy;
                }
            }
            return energyPoints;
        }


        // Checks if it is the local players turn
        public boolean isMyTurn(List<Entity> entities, String username) {
            Entity activePlayer = SystemsHelpers.findActivePlayer(entities);
            return username.equals(activePlayer.components.playerInfo.username);
        }

        // Change turn between players after attack has happened
        public void changeActivePlayer(List<Entity> entities) {
            Entity prevActivePlayer = SystemsHelpers.findActivePlayer(entities);
            int prevActiveIndex = prevActivePlayer.components.playerInfo.index;
            prevActivePlayer.components.playerInfo.isMyTurn = false;

            List<Entity> players = SystemsHelpers.findPlayers(entities);
            int nextActiveIndex = (prevActiveIndex + 1) % players.size();
            for(Entity player: players) {
                if(player.components.playerInfo.index == nextActiveIndex) {
                    player.components.playerInfo.isMyTurn = true;
                    if (player.components.playerInfo.energy > 90) {
                        player.components.playerInfo.energy = 100;
                    } else {
                        player.components.playerInfo.energy += 10;
                    }
                }
            }
        }
    }

    // Inner class handling arrow actions
    public static class ArrowSystem {

        private boolean flying = true;
        private Body[] contactBodies = new Body[2];

        // Runs arrow animation and related logic
        public void arrowAnimation(
                World world,
                List<Entity> entities,
                Vector2 vector2) {

            world.setContactListener(new ContactListener() {
                @Override
                public void beginContact(Contact contact) {
                    contactBodies[0] = contact.getFixtureA().getBody();
                    contactBodies[1] = contact.getFixtureB().getBody();
                    flying = false;
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

            Entity activeArrow = SystemsHelpers.findActiveArrow(entities);
            activeArrow.components.box2dBody.body.setLinearVelocity(vector2.scl(-.05f));
            flying = true;
            while(flying) {
                world.step(.001f, 1, 1);
            }

            Body hitBody = contactBodies[0];
            if(contactBodies[0] == activeArrow.components.box2dBody.body) {
                hitBody = contactBodies[1];
            }
            hitBody.setLinearVelocity(0,0);

            List<Entity> players = SystemsHelpers.findPlayers(entities);
            for(Entity player: players) {
                if(hitBody == player.components.box2dBody.body) {
                    player.components.playerInfo.hitPoints -=
                            activeArrow.components.arrowType.damage;
                    break;
                }
            }

            activeArrow.removeComponent("arrowType");
            world.destroyBody(activeArrow.components.box2dBody.body);
        }
    }
}
