package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HealthBar extends Actor {

    /***
     *
     * @author group 11
     * @since 2020-04-25
     * @param health is taken as argument and respective health-bar is rendered based on the
     *               players health
     */

    private TextureAtlas atlas;
    public Sprite sprite;
    private int posX;
    // private static HealthBar thisInstance;

    // TODO: Dispose / Singleton?

    public HealthBar(int health, int posx) {
        atlas = new TextureAtlas("healthbarSheet.pack");
        sprite = new Sprite();
        this.posX = posx;

        if (health <= 20) {
            sprite = atlas.createSprite("health10");
        }
        else if (health <= 30) {
            sprite = atlas.createSprite("health20");
        }
        else if (health <= 40) {
            sprite = atlas.createSprite("health30");
        }
        else if (health <= 50) {
            sprite = atlas.createSprite("health40");
        }
        else if (health <= 60) {
            sprite = atlas.createSprite("health50");
        }
        else if (health <= 70) {
            sprite = atlas.createSprite("health60");
        }
        else if (health <= 80) {
            sprite = atlas.createSprite("health70");
        }
        else if (health <= 90) {
            sprite = atlas.createSprite("health80");
        }
        else if (health < 100) {
            sprite = atlas.createSprite("health90");
        }
        else if (health == 100) {
            sprite = atlas.createSprite("health100");
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setSize(400, 100);
        sprite.setPosition(posX, 940);
        sprite.draw(batch);
        // dispose();
    }

    public void dispose() {
        // atlas.dispose();
        clear();
    }
}
