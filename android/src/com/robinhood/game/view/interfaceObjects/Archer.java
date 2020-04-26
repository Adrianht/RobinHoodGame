package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private BitmapFont font = new BitmapFont();
    private String username;

    public Archer(Entity playerEntity, Texture archerTexture) {
        entity = playerEntity;
        sprite = new Sprite(archerTexture);
        sprite.setSize(80, 120);
        username = playerEntity.components.playerInfo.username;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Body body = entity.components.box2dBody.body;
        if (body != null) {
            float posX = box2dPosToGdxPosX(body);
            float posY = box2dPosToGdxPosY(body);
            font.setColor(Color.WHITE);
            font.draw(
                    batch,
                    username,
                    posX,
                    posY + sprite.getHeight() + 20);
            sprite.setPosition(posX, posY);
            sprite.draw(batch);
        }
    }

    private static float box2dPosToGdxPosX(Body body){
        return Gdx.graphics.getWidth()/2f
                - .5f * Gdx.graphics.getWidth() / 32
                + body.getPosition().x * Gdx.graphics.getWidth() / 32;
    }

    private static float box2dPosToGdxPosY(Body body){
        return Gdx.graphics.getHeight()/2f
                - 1.2f * Gdx.graphics.getHeight() / 24
                + body.getPosition().y * Gdx.graphics.getHeight() / 24;
    }
}
