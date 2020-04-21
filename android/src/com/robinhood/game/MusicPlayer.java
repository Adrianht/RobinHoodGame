package com.robinhood.game;

import com.badlogic.gdx.audio.Music;

import com.robinhood.game.view.loader.GameAssetManager;

public final class MusicPlayer {

    private static final MusicPlayer INSTANCE = new MusicPlayer();
    private Music themeSong;

    private MusicPlayer() {
        GameAssetManager assetManager = new GameAssetManager();
        assetManager.loadMusic();
        assetManager.manager.finishLoading();
        themeSong = assetManager.manager.get(assetManager.music);
        themeSong.setLooping(true);
        themeSong.setVolume(0.5f);
        themeSong.play();
    }

    public static MusicPlayer getInstance() {
        return INSTANCE;
    }
}
