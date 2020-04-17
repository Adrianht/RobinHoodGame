package com.robinhood.game.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Arrays;
import java.util.List;

// TODO: maybe separate in to own classes in package Systems
public class Systems {

    // system class handling rendering entities
    public static class UserInput {
        private void move(World world, List<Entity> entities, int movement) {
            Body arrowBody = null;
            for(Entity entity: entities) {
                if (entity.component.arrowtype != null) {
                    arrowBody = entity.component.box2dBody.body;
                    arrowBody.setActive(false);
                }
            }
            for(Entity entity: entities) {
                if(entity.component.turn != null) {
                    if (entity.component.turn.turn & entity.component.energy.value > 0) {
                        entity.component.box2dBody.body.setLinearVelocity(movement, 0);
                        while (entity.component.box2dBody.body.getLinearVelocity().x != 0) {
                            world.step(.001f, 1, 1);
                        }
                        entity.component.energy.value -= 2;
                        arrowBody.setActive(true);
                    }
                }
            }
        }
        public void moveLeft(World world, List<Entity> entities) {
            move(world, entities, -1);
        }
        public void moveRight(World world, List<Entity> entities) {
            move(world, entities, 1);
        }

        /* Checks if the player has enough energy to buy that arrow type, if yes,
        updates the arrow type*/
        public void buyArrow(List<Entity> entities, String type) {
            Entity activePlayer = null;
            Entity arrowEntity = null;
            for(Entity entity: entities) {
                if (entity.component.turn != null && entity.component.turn.turn) {
                    activePlayer = entity;
                } else if (entity.component.arrowtype != null) {
                    arrowEntity = entity;
                }
            }

            // Check if player can afford current buy,
            //  then update arrowtype and player energy
            if(activePlayer != null && arrowEntity != null) {
                int currentEnergy = activePlayer.component.energy.value;

                // Level 2 arrow - cost: 20, damage: 20
                if(type.equals("Level2") && currentEnergy >= 20) {
                    arrowEntity.component.arrowtype.type = "Level2";
                    arrowEntity.component.arrowtype.damage = 20;
                    activePlayer.component.energy.value -= 20;
                    // TODO: remove sysout
                    System.out.println("Arrow2 bought and has damage: " +
                            arrowEntity.component.arrowtype.damage);
                }
                // Level 3 arrow - cost: 50, damage: 50
                else if(type.equals("Level3") && currentEnergy >= 50) {
                    arrowEntity.component.arrowtype.type = "Level3";
                    arrowEntity.component.arrowtype.damage = 50;
                    activePlayer.component.energy.value -= 50;
                    // TODO: remove sysout
                    System.out.println("Arrow3 bought and has damage: " +
                            arrowEntity.component.arrowtype.damage);
                }
                // Level 4 arrow - cost: 70, damage: 70
                else if(type.equals("Level4") && currentEnergy >= 70) {
                    arrowEntity.component.arrowtype.type = "Level4";
                    arrowEntity.component.arrowtype.damage = 70;
                    activePlayer.component.energy.value -= 70;
                    // TODO: remove sysout
                    System.out.println("Arrow4 bought and has damage: " +
                            arrowEntity.component.arrowtype.damage);
                }
            }
        }
    }


    public static class playerInfo {

        // Finds and returns HP values of all players
        public static List<Integer> getHP(List<Entity> entities, int nrOfPlayers){
            List<Integer> points = Arrays.asList(new Integer[nrOfPlayers]);
            for(Entity entity: entities){
                if(entity.component.hp != null){
                    points.set(entity.component.playernr.nr, entity.component.hp.value);
                }
            }
            return points;
        }

        public static List<Integer> getEnergyPoints(List<Entity> entities, int nrOfPlayers){
            List<Integer> points = Arrays.asList(new Integer[nrOfPlayers]);
            for (Entity entity: entities){
                if(entity.component.energy != null){
                    points.set(entity.component.playernr.nr, entity.component.energy.value);
                }
            }
            return points;
        }

        public int getPlayerNr(List<Entity> entities, String username){
            int nr = new Integer(3);
            System.out.println(username);
            for(Entity entity: entities){
                if(entity.component.energy != null){
                    if(entity.component.name.name.equals(username)){
                        System.out.println(entity.component.playernr.nr);
                        nr = entity.component.playernr.nr;
                    }
                }
            }
            return nr;
        }

        public boolean isMyTurn(List<Entity> entities, String username) {
            for (Entity entity: entities){

                if(entity.component.turn != null
                        && entity.component.turn.turn
                        && entity.component.name != null
                        && entity.component.name.name == username ){
                    return true;
                }
            }
            return false;
        }

        public void changeTurn(List<Entity> entities, int nrOfPlayers) {

            int prevPlayerNr = 0;

            // remove current players turn
            for(Entity entity: entities) {
                if(entity.component.turn != null) {
                    if (entity.component.turn.turn) {
                        prevPlayerNr = entity.component.playernr.nr;
                        entity.component.turn.turn = false;
                    }
                }
            }

            // give turn to next player and add 10 energy points
            for(Entity entity: entities) {
                if(entity.component.playernr != null) {
                    if (entity.component.playernr.nr == (prevPlayerNr + 1) % nrOfPlayers) {
                        entity.component.turn.turn = true;
                        if (entity.component.energy.value < 90) {
                            entity.component.energy.value += 10;
                        } else {
                            entity.component.energy.value = 100;
                        }
                    }
                }
            }
        }
    }


    // system class handling animations
    public static class Animation {

        private boolean flying = true;
        private Body[] contactBodies = new Body[2];

        public void arrowAnimation(World world, final List<Entity> entities, Vector2 vector2) {

            world.setContactListener(new ContactListener() {
                @Override
                public void beginContact(Contact contact) {
                    Fixture fixtureA = contact.getFixtureA();
                    Fixture fixtureB = contact.getFixtureB();
                    contactBodies[0] = fixtureA.getBody();
                    contactBodies[1] = fixtureB.getBody();
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

            for(Entity entity: entities) {
                // finds arrow object
                if (entity.component.arrowtype != null) {
                    entity.component.box2dBody.body.setLinearVelocity(vector2.scl(-.05f));
                    flying = true;
                    while(flying) {
                        world.step(.001f, 1, 1);
                    }

                    Body hitBody;
                    // ignore arrow entity
                    if (contactBodies[0] != entity.component.box2dBody.body) {
                        hitBody = contactBodies[0];
                    } else {
                        hitBody = contactBodies[1];
                    }
                    hitBody.setLinearVelocity(0,0);

                    // hit player body object
                    for(Entity entity2: entities) {
                        if(entity2.component.playernr != null
                                && hitBody == entity2.component.box2dBody.body) {
                            entity2.component.hp.value -= entity.component.arrowtype.damage;
                        }
                    }

                    // remove used arrow
                    entity.component.arrowtype = null;
                    world.destroyBody(entity.component.box2dBody.body);
                }
            }
        }
    }
}
