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
public class HealthBar extends Actor {

    private TextureAtlas atlas;
    private Sprite sprite;
    private String lastSpriteName;
    private final int posX;

    public HealthBar(int posX) {
        atlas = new TextureAtlas("healthbarSheet.pack");
        sprite = atlas.createSprite("health100");
        lastSpriteName = "health100";
        this.posX = posX;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setSize(400, 100);
        sprite.setPosition(posX, 940);
        sprite.draw(batch);
    }

    public void updateSprite(int health) {
        int spriteNameNumber = health/10;
        spriteNameNumber *= 10;
        String name = "health" + spriteNameNumber;

        if (!lastSpriteName.equals(name)) {
            atlas.dispose();
            atlas = new TextureAtlas("healthbarSheet.pack");
            sprite = atlas.createSprite(name);
            lastSpriteName = name;
        }
    }
}
