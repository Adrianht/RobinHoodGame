package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DragIndicator extends Actor {

    public Sprite sprite;

    public DragIndicator() {
        sprite = new Sprite(new Texture("dragInd.png"));
        sprite.setSize(0, 0);
        sprite.setOrigin(0, 20);
        setPosition(900, 600);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(this.getX(), this.getY());
        sprite.draw(batch);
    }

}
