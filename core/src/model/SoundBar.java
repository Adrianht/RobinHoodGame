package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


public final class SoundBar extends Actor {
    /*

    Singleton class

    Example class demonstrating an interface object which
    holds game data. Objects containing game data should
    be placed in the model-package.

    */

    private static final SoundBar soundBar = new SoundBar();
    private Boolean soundActive = true;

    private Sprite sprite;

    private SoundBar() {
        sprite = new Sprite(new Texture("soundon.png"));
        setPosition(500, 400);
        sprite.setSize(100,70);
    }

    // TODO: add description
    public Boolean getSound() {
        return soundActive;
    }

    // TODO: add description
    public void changeSound() {
        if(soundActive) {
            soundActive = false;
            sprite = new Sprite(new Texture("soundoff.png"));
            sprite.setSize(100, 70);
        } else {
            soundActive = true;
            sprite = new Sprite(new Texture("soundon.png"));
            sprite.setSize(100, 70);
        }
    }

    public static SoundBar getSoundBar() {
        return soundBar;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(this.getX(), this.getY());
        sprite.draw(batch);
    }

}
