package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.robinhood.game.model.Model;

public class Arrow extends Actor {

    private final Model model;
    private final Sprite sprite;
    private boolean newShot = true;
    private boolean prevWasLeft = true;
    float startPosX, startPosY;

    public Arrow(Model model) {
        this.model = model;
        sprite = new Sprite(new Texture("arrowsprite.png"));
        sprite.rotate(40);
        sprite.setSize(80, 80);
    }

    @Override
    // FIXME-ola: logic..
    public void draw(Batch batch, float parentAlpha) {
        Body activeArrow = model.getActiveArrowBody();
        if (activeArrow != null) {
            if (newShot) {
                if(activeArrow.getLinearVelocity().x < 0 && prevWasLeft) {
                    startPosX = 750 + sprite.getWidth();
                    startPosY = 540 + sprite.getHeight();
                    sprite.rotate90(true);
                    sprite.rotate90(true);
                } else {
                    startPosX = 750;
                    startPosY = 540;
                }
            }
            sprite.setPosition(
                    startPosX + activeArrow.getPosition().x * 56,
                    startPosY + + activeArrow.getPosition().y * 46);
            sprite.draw(batch);
            newShot = false;
        } else {
            if(!newShot) {
                newShot = true;
                sprite.rotate90(false);
                sprite.rotate90(false);
            }
        }
    }
}
