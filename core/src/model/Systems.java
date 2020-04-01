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

}
