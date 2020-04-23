package com.robinhood.game.view.interfaceObjects;

import android.content.res.AssetManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.robinhood.game.assetManagers.GameAssetManager;

/**
 * Class with interface object showing a players
 * hit point value.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class HealthBar extends Actor {

    private TextureAtlas atlas;
    private Sprite sprite;
    private String lastSpriteName;
    private final int posX;

    public HealthBar(int posX, GameAssetManager assetManager) {
        this.posX = posX;
        this.atlas = assetManager.get(assetManager.healthBarAtlas);
        createNewAtlas("health100");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setSize(400, 100);
        sprite.setPosition(posX, 940);
        sprite.draw(batch);
    }

    public void updateSprite(int health) {
        String newSpriteName = "health" + (health/10)*10;
        if (!lastSpriteName.equals(newSpriteName)) {
            atlas.dispose();
            createNewAtlas(newSpriteName);
        }
    }

    private void createNewAtlas(String spriteName) {
        sprite = atlas.createSprite(spriteName);
        lastSpriteName = spriteName;
    }
}
