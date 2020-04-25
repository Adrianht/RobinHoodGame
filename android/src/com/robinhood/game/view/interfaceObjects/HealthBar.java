package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.robinhood.game.model.Entity;

/**
 * Class with interface object showing a players
 * hit point value.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class HealthBar extends Actor {

    private final Entity player;
    private final int posX;
    private final TextureAtlas atlas;
    private Sprite sprite;
    private int lastHitPointValue;

    public HealthBar(Entity player, int posX, TextureAtlas textureAtlas) {
        this.player = player;
        this.posX = posX;
        this.atlas = textureAtlas;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        int hitPointValue = player.components.playerInfo.hitPoints;
        if (hitPointValue != lastHitPointValue) {
            String newSpriteName = "health" + (hitPointValue/10)*10;
            sprite = atlas.createSprite(newSpriteName);
            lastHitPointValue = hitPointValue;
        }
        sprite.setSize(400, 100);
        sprite.setPosition(posX, 940);
        sprite.draw(batch);
    }
}
