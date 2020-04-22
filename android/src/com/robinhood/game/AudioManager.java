package com.robinhood.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import com.robinhood.game.view.loader.GameAssetManager;

public class AudioManager {

    private static final AudioManager INSTANCE = new AudioManager();
    private boolean MUSIC_ENABLED = true;
    private boolean SOUND_ENABLED = true;
    private Music themeSong;
    public Sound draw, hit, shoot;

    private AudioManager() {
        GameAssetManager assetManager = new GameAssetManager();

        assetManager.loadMusic();
        assetManager.manager.finishLoading();
        themeSong = assetManager.manager.get(assetManager.music);
        themeSong.setLooping(true);
        themeSong.setVolume(0.5f);
        themeSong.play();

        assetManager.loadSounds();
        assetManager.manager.finishLoading();
        hit = assetManager.manager.get(assetManager.hitSound);
        shoot = assetManager.manager.get(assetManager.shootSound);
        draw = assetManager.manager.get(assetManager.drawSound);
    }

    public static AudioManager getInstance() {
        return INSTANCE;
    }

    public void playSound(String sound) {
        if(SOUND_ENABLED) {
            if(sound.equals("draw")) {
                draw.play();
            } else if(sound.equals("hit")) {
                hit.play();
            } else if(sound.equals("shoot")) {
                shoot.play();
            }
        }
    }

    public boolean getMusicEnabled() {
        return MUSIC_ENABLED;
    }

    public void setMusicEnabled(boolean enabled) {
        MUSIC_ENABLED = enabled;
        if(!enabled){
            themeSong.pause();
        } else{
            themeSong.play();
        }
    }

    public boolean getSoundEnabled() {
        return SOUND_ENABLED;
    }

    public void setSoundEnabled(boolean enabled) {
        SOUND_ENABLED = enabled;
    }
}
