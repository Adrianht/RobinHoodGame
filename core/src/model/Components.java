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
public class Components {

    // holders for all possible components
    public Energy energy;
    public GameActor actor;
    public HitPoints hp;
    public PlayerName name;
    public PlayerNr playernr;
    public Position pos;
    public Turf turf;
    public Turn turn;
    public ArrowType arrowtype;

    // constructors for all possible components
    public class ArrowType {
        public ArrowType() {
            Components.this.arrowtype = this;
        }
        public String type = "Normal";
        public int damage = 10;
        public int cost = 0;

        /* As for now Level2 weapons costs 20 and damages 20,
        Level3 costs 50 and damages 50, Level4 costs 70 and damages 70*/
        public int getDamage(String type) {
            switch (type) {
                case "Normal":
                    return 10;
                case "Level2":
                    return 20;
                case "Level3":
                    return 50;
                case "Level4":
                    return 70;
                default:
                    return 10;
            }
        }

        public int getCost(String type) {
            switch (type) {
                case "Normal":
                    return 0;
                case "Level2":
                    return 20;
                case "Level3":
                    return 50;
                case "Level4":
                    return 70;
                default:
                    return 0;
            }
        }
    }

    public class Energy {
        public Energy() {
            Components.this.energy = this;
        }
        public int value = 20;
    }
    public class GameActor extends Actor {
        public GameActor() {
            Components.this.actor = this;
        }
        public Sprite sprite;

        @Override
        public void draw(Batch batch, float parentAlpha) {
            sprite.setPosition(Components.this.pos.x, Components.this.pos.y);
            sprite.draw(batch);
        }
    }
    public class HitPoints {
        public HitPoints() {
            Components.this.hp = this;
        }
        public int value = 100;
    }
    public class PlayerName {
        public PlayerName() {
            Components.this.name = this;
        }
        public String name;
    }
    public class PlayerNr {
        public PlayerNr() {
            Components.this.playernr = this;
        }
        public int nr;
    }
    public class Position {
        public Position() {
            Components.this.pos = this;
        }
        public float x, y;
    }
    public class Turf {
        public Turf() {
            Components.this.turf = this;
        }
        public int[] y;
    }
    public class Turn extends Components {
        public Turn() {
            Components.this.turn = this;
        }
        public boolean turn = false;
    }

}
