package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

/***
 *
 * @author group 11
 * @since 2020-04-25
 *
 */

public class EnergyBar extends Actor {

    private TextureAtlas atlas;
    private Sprite sprite;
    private String lastSpriteName;

    public EnergyBar(int posX) {
        atlas = new TextureAtlas("energyBar.txt");
        sprite = atlas.createSprite("energy100");
        lastSpriteName = "energy100";
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setSize(300, 75);
        sprite.setPosition(150, 850);
        sprite.draw(batch);
    }

    public void updateSprite(int health) {
        int spriteNameNumber = health/10;
        spriteNameNumber *= 10;
        String name = "energy" + spriteNameNumber;

        if (!lastSpriteName.equals(name)) {
            atlas.dispose();
            atlas = new TextureAtlas("energyBar.txt");
            sprite = atlas.createSprite(name);
            lastSpriteName = name;
        }
    }
}
