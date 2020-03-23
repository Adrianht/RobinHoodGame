package com.robinhood.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Archer extends Actor {
    /*

    Example class demonstrating an interface object which
    don't hold game data. Objects containing game data should
    be placed in the model-package

    */

    private Sprite sprite;

    public Archer(String color) {
        switch(color) {
            case "RED":
                sprite = new Sprite(new Texture("redarcher.png"));
                setPosition(30, 200);
                sprite.setSize(200, 200);
                break;
            case "BLUE":
                sprite = new Sprite(new Texture("bluearcher.png"));
                setPosition(400, 200);
                sprite.setSize(200, 200);
                break;
            default:
                // code block
        }
    }

    public void move(Boolean left) {
        if(left) {
            moveBy(-10, 0);
        } else {
            moveBy(10,0);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(this.getX(), this.getY());
        sprite.draw(batch);
    }

}
