package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.robinhood.game.model.Entity;
import com.robinhood.game.model.Model;

/**
 * TODO: add description.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
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
        Entity activeArrow = model.getActiveArrowEntity();
        if (activeArrow != null) {
            Body activeArrowBody =
                    activeArrow.components.box2dBody.body;
            if (newShot) {
                updateSprite(
                        activeArrow.components.arrowType.type);
                startPosX = 750;
                startPosY = 540;
                if(activeArrowBody.getLinearVelocity().x > 0) {
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
                    startPosX + activeArrowBody.getPosition().x * 56,
                    startPosY + activeArrowBody.getPosition().y * 46);
            sprite.draw(batch);
        } else {
            newShot = true;
        }
    }

    private void updateSprite(String arrowType) {
        // TODO: after asset manager, change sprite on @param arrowType
        // arrowTypes are Level1,Level2,Level3,Level4
        // Level1 is default
    }
}
