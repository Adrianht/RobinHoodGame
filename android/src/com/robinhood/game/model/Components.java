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

    public ArrowType arrowType;
    public Box2dBody box2dBody;
    public PlayerInfo playerInfo;

    public class ArrowType {
        public ArrowType() {
            Components.this.arrowType = this;
        }
        public String type = "Level1";
        public int damage = 10;
    }

    public class Box2dBody {
        public Box2dBody() {
            Components.this.box2dBody = this;
        }
        public Body body;
    }

    public class PlayerInfo {
        public PlayerInfo() {
            Components.this.playerInfo = this;
        }
        public String username;
        public int index;
        public int hitPoints = 100;
        public int energy = 20;
        public boolean isPlayersTurn = false;
    }
}
