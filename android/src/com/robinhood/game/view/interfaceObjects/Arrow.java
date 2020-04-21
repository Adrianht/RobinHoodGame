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
    public void draw(Batch batch, float parentAlpha) {
        Body activeArrow = model.getActiveArrowBody();
        if (activeArrow != null) {
            if (newShot) {
                startPosX = 750;
                startPosY = 540;
                if(activeArrow.getLinearVelocity().x > 0) {
                    startPosX = 750 + sprite.getWidth();
                    startPosY = 560;
                    if (prevWasLeft) {
                        sprite.rotate90(true);
                        sprite.rotate90(true);
                    }
                    prevWasLeft = false;
                } else {
                    if (!prevWasLeft) {
                        sprite.rotate90(true);
                        sprite.rotate90(true);
                    }
                    prevWasLeft = true;
                }
                newShot = false;
            }
            sprite.setPosition(
                    startPosX + activeArrow.getPosition().x * 56,
                    startPosY + activeArrow.getPosition().y * 46);
            sprite.draw(batch);
        } else {
            newShot = true;
        }
    }
}
