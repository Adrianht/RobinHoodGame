package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Class with interface object showing a player arrows
 * expected direction and power.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class DragIndicator extends Actor {

    public Sprite sprite;

    public DragIndicator(Texture dragIndicatorTexture) {
        sprite = new Sprite(dragIndicatorTexture);
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
