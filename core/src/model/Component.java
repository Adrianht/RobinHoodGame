package model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.event.ComponentEvent;

/*
     Component in ECS
     Holds all components possible for entities
     within RobinHood-game
 */
public class Component {

    // holders for all possible components
    public Energy energy;
    public GameActor actor;
    public HitPoints hp;
    public PlayerName name;
    public Position pos;
    public Turf turf;
    public Turn turn;

    // constructors for all possible components
    public class Energy {
        public Energy() {
            Component.this.energy = this;
        }
        public int value = 20;
    }
    public class GameActor extends Actor {
        public GameActor() {
            Component.this.actor = this;
        }
        public Sprite sprite;

        @Override
        public void draw(Batch batch, float parentAlpha) {
            sprite.setPosition(Component.this.pos.x, Component.this.pos.y);
            sprite.draw(batch);
        }
    }
    public class HitPoints {
        public HitPoints() {
            Component.this.hp = this;
        }
        public int value = 100;
    }
    public class PlayerName {
        public PlayerName() {
            Component.this.name = this;
        }
        public String name;
    }
    public class Position {
        public Position() {
            Component.this.pos = this;
        }
        public float x, y;
    }
    public class Turf {
        public Turf() {
            Component.this.turf = this;
        }
        public int[] y;
    }
    public class Turn extends Component {
        public Turn() {
            Component.this.turn = this;
        }
        public boolean turn = false;
    }

}
