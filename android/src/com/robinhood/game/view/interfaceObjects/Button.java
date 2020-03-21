package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Button extends Actor {
    /*

    Example class demonstrating an interface object which
    don't hold game data. Objects containing game data should
    be placed in the model-package

    */

    private Sprite sprite;

    public Button(String type) {
        switch(type) {
            case "settings":
                sprite = new Sprite(new Texture("setting.png"));
                setPosition(400, 200);
                break;
            case "play":
                sprite = new Sprite(new Texture("play.png"));
                setPosition(50, 200);
                break;
            case "menu":
                sprite = new Sprite(new Texture("menu.png"));
                setPosition(250, 50);
                break;
            case "left":
                sprite = new Sprite(new Texture("left.png"));
                setPosition(50, 0);
                sprite.setSize(100,100);
                break;
            case "right":
                sprite = new Sprite(new Texture("right.png"));
                setPosition(500, 0);
                sprite.setSize(100,100);
                break;
            default:
                // code block
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(this.getX(), this.getY());
        sprite.draw(batch);
    }

}
