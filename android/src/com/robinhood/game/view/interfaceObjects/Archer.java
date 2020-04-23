package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.robinhood.game.model.Entity;

/**
 * Class with interface object showing the archer of a player.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class Archer extends Actor {

    private Sprite sprite;
    private final Entity entity;

    public Archer(Entity playerEntity, Texture playerTexture) {
        entity = playerEntity;
        sprite = new Sprite(playerTexture);
        sprite.setSize(40, 60);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Body body = entity.components.box2dBody.body;
        sprite.setPosition(
                box2dPosToGdxPosX(body),
                box2dPosToGdxPosY(body.getPosition().y));
        sprite.draw(batch);
    }

    private static float box2dPosToGdxPosX(Body body){
        return Gdx.graphics.getWidth()/2f
                - .25f * Gdx.graphics.getWidth() / 32
                + body.getPosition().x * Gdx.graphics.getWidth() / 32;
    }

    private static float box2dPosToGdxPosY(float box2dPosY){
        return Gdx.graphics.getHeight()/2f
                - .7f * Gdx.graphics.getHeight() / 24
                + box2dPosY * Gdx.graphics.getHeight() / 24;
    }
}
