package model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

public class Systems {

    // system class handling rendering entities
    public static class UserInput {
        public void moveLeft(List<Entity> entities) {
            for(Entity entity: entities) {
                if (entity.component.turn.turn & entity.component.energy.value > 0) {
                    entity.component.pos.x -= 20;
                    entity.component.energy.value -= 2;
                    System.out.println("Player, whose turn it is moved left, energy points reduced to: " +
                            entity.component.energy.value);
                }
            }
        }
        public void moveRight(List<Entity> entities) {
            for(Entity entity: entities) {
                if (entity.component.turn.turn & entity.component.energy.value > 0) {
                    entity.component.pos.x += 20;
                    entity.component.energy.value -= 2;
                    System.out.println("Player, whose turn it is moved right, energy points reduced to: " +
                            entity.component.energy.value);
                }
            }
        }
        public void drawBow(List<Entity> entities, Vector2 vector2) {

            // FIXME: try to find more elegant solution

            int nrOfPlayers = 0;
            int prevPlayerNr = 0;

            for(Entity entity: entities) {
                if (entity.component.playernr != null) {
                    nrOfPlayers++;
                }
                if (entity.component.turn.turn) {
                    prevPlayerNr = entity.component.playernr.nr;
                    entity.component.turn.turn = false;
                }
            }
            for(Entity entity: entities) {
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

        /*
           TODO:
           add system methods related to buying arrows and attacking

        */

    }

    public static class gameOver{



        public boolean gameIsOver(List<Entity> entities){
            for(Entity entity : entities){
                if(entity.component.hp.value < 1){
                    System.out.println("Game over...");
                    return true;
                }
            }
            return false;
        }


        public  static int getHP(List<Entity> entities){
            int points = 0;
            for(Entity entity : entities){
                if(entity.component.hp != null){
                    points = entity.component.hp.value;
                }
            }
            return points;
        }

        public int getEnergyPoints(List<Entity> entities){
            int points = 0;
            for (Entity entity : entities){
                if(entity.component.energy != null){
                    points = entity.component.energy.value;
                }
            }
            return points;
        }



    }


    // system class handling rendering entities
    public static class Render {
        public List<Actor> getActors(List<Entity> entities) {
            List<Actor> actors = new ArrayList<>();

            for(Entity entity: entities) {
                if (entity.component.actor != null) {
                    actors.add(entity.component.actor);
                }
            }

            return actors;
        }

        /*
           TODO:
           add system method update() to change correct rendering actor(s)

        public void update(EntityManager manager) {
            int required_components = Component.POS | Component.RENDER;
            for (int i = 0; i < manager.size; i++) {
                if ((manager.flag[i] & required_components) == required_components) {
                    System.out.println(String.format("%s: (%f x, %f y)", manager.rendering[i].name, manager.pos[i].y, manager.pos[i].y));
                }
            }
        }
        */
    }

    // possibly class to handle arrow animation
    public static class Animation {

        // TODO: add appropriate method

    }

    // possibly class to handle Collision check
    public static class Collision {

        // TODO: add appropriate method

    }

}
