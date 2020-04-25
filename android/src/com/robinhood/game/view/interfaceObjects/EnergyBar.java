package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Class with interface object showing the
 * local player's energy value.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class EnergyBar extends Actor {

    private final TextureAtlas atlas;
    private Sprite sprite;
    private int lastEnergyValue = 100;

    public EnergyBar(TextureAtlas textureAtlas) {
        atlas = textureAtlas;
        sprite = atlas.createSprite("energy100");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setSize(300, 75);
        sprite.setPosition(48, 850);
        sprite.draw(batch);
    }

    public void updateSprite(int energy) {
        if (energy != lastEnergyValue) {
            String newSpriteName = "energy" + (energy/10)*10;
            sprite = atlas.createSprite(newSpriteName);
            lastEnergyValue = energy;
        }
    }
}
