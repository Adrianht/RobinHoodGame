package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.robinhood.game.model.Entity;
import com.robinhood.game.model.Model;

/**
 * Class with interface object showing the arrow
 * in shot animation.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class Arrow extends Actor {

    private final Model model;
    private Sprite sprite;
    private float startPosX, startPosY;
    private boolean isNewShot;

    public Arrow(Model model) {
        this.model = model;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Entity activeArrow = model.getActiveArrowEntity();
        if (activeArrow != null) {
            Body arrowBody =
                    activeArrow.components.box2dBody.body;
            if (isNewShot) {
                createSprite(activeArrow);
                isNewShot = false;
            }
            sprite.setPosition(
                    startPosX + arrowBody.getPosition().x * 56,
                    startPosY + arrowBody.getPosition().y * 46);
            sprite.draw(batch);
        } else {
            isNewShot = true;
        }
    }

    // FIXME: post assetManager
    private void createSprite(Entity arrowEntity) {
        String arrowType = arrowEntity.components.arrowType.type;
        Body arrowBody = arrowEntity.components.box2dBody.body;
        String assetPath = "buy" + arrowType + ".png";
        sprite = new Sprite(new Texture(assetPath));
        sprite.setSize(80, 80);
        sprite.setRotation(0);
        switch (arrowType) {
            case "Level2":
                // TODO: sprite.rotate(40);
                sprite.rotate(40);
                break;
            case "Level3":
                // TODO: sprite.rotate(40);
                break;
            case "Level4":
                // TODO: sprite.rotate(40);
                break;
            default:
                sprite.rotate(40);
        }
        if(arrowBody.getLinearVelocity().x < 0) {
            startPosX = 750;
            startPosY = 540;
        } else {
            startPosX = 750 + sprite.getWidth();
            startPosY = 560;
            sprite.rotate90(true);
            sprite.rotate90(true);
        }
    }
}
