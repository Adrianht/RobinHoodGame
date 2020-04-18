package com.robinhood.game.model;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Class representing Component in Entity-Component-System.
 * Holds all available components in the game.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class Components {

    // TODO: create names with same convention and change everywhere used
    // TODO: possibly merge playername, playernr, energy, hitpoints, and turn to playerInfo
    // TODO: check that all components are used
    public Energy energy;
    public HitPoints hp;
    public PlayerName name;
    public PlayerNr playernr;
    public Box2dBody box2dBody;
    public Turn turn;
    public ArrowType arrowtype;

    public class ArrowType {
        public ArrowType() {
            Components.this.arrowtype = this;
        }
        public String type = "Normal";
        public int damage = 10;
    }

    public class Energy {
        public Energy() {
            Components.this.energy = this;
        }
        public int value = 20;
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

    public class Box2dBody {
        public Box2dBody() {
            Components.this.box2dBody = this;
        }
        public Body body;
    }

    public class Turn extends Components {
        public Turn() {
            Components.this.turn = this;
        }
        public boolean turn = false;
    }
}
