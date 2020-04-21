package com.robinhood.game;

import com.badlogic.gdx.audio.Music;
import com.robinhood.game.view.loader.GameAssetManager;

public class AudioManager {

    private static final AudioManager INSTANCE = new AudioManager();
    private Boolean MUSIC_ENABLED = true;
    private Boolean SOUND_ENABLED = true;
    private Music themeSong;
    // TODO: add sounds

    private AudioManager() {
        GameAssetManager assetManager = new GameAssetManager();
        assetManager.loadMusic();
        assetManager.manager.finishLoading();
        themeSong = assetManager.manager.get(assetManager.music);
        themeSong.setLooping(true);
        themeSong.setVolume(0.5f);
        themeSong.play();

        // TODO: initate sound
    }

    public static AudioManager getInstance() {
        return INSTANCE;
    }

    public boolean getMusicEnabled() { return MUSIC_ENABLED; }

    public void setMusicEnabled(boolean enabled) { MUSIC_ENABLED = enabled; }

    public boolean getSoundEnabled() {
        return SOUND_ENABLED;
    }

    public void setSoundEnabled(boolean enabled) { SOUND_ENABLED = enabled; }
}
