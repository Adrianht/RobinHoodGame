package com.robinhood.game.model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robinhood.game.controller.Controller;


public final class SoundBar extends Actor {
    /*

    Singleton class

    Example class demonstrating an interface object which
    holds game data. Objects containing game data should
    be placed in the model-package.

    */
    private Controller controller;

    private static final SoundBar soundBar = new SoundBar();
    private Boolean soundActive = true;
    private Boolean listenerInit = false;

    private Sprite sprite;

    private SoundBar() {
        sprite = new Sprite(new Texture("soundon.png"));
        sprite.setSize(100, 70);
        setBounds(500, 400, sprite.getWidth(), sprite.getHeight());
    }

    // Adds clickListener to the soundBar.
    // Singleton objects are typically not constructed with an argument,
    // but since we need to use the controller for the changeSound() function
    // this method works around that. Maybe SoundBar should not be a singleton?
    public void addListener(Controller cont){
        soundBar.controller = cont;

        listenerInit = true;

        soundBar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("CHANGE SOUND SETTINGS");
                controller.changeSound();
            }
        });
    }

    // TODO: add description
    public Boolean getListener() {
        return listenerInit;
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
            sprite.setSize(100,70);
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
