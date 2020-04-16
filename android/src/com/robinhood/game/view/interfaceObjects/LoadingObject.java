package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class LoadingObject extends Actor {
    /*

    Example class demonstrating an interface object which
    don't hold game data. Objects containing game data should
    be placed in the model-package

    */

    private Sprite sprite;

    public LoadingObject() {
        sprite = new Sprite(new Texture("loadingObj.png"));
        setPosition(500, 500);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(this.getX(), this.getY());
        sprite.draw(batch);
    }

}
