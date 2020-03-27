package model;


import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

public class Systems {

    // system class handling rendering entities
    public static class UserInput {
        public void moveLeft(List<Entity> entities) {
            for(Entity entity: entities) {
                if (entity.component.turn.turn) {
                    entity.component.pos.x -= 20;
                    break;
                }
            }
        }
        public void moveRight(List<Entity> entities) {
            for(Entity entity: entities) {
                if (entity.component.turn.turn) {
                    entity.component.pos.x += 20;
                    break;
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

    // possibly class to handle arrow animation
    public static class Animation {

        // TODO: add appropriate method

    }

    // possibly class to handle Collision check
    public static class Collision {

        // TODO: add appropriate method

    }

}
