package com.robinhood.game.assetManagers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public final class AudioManager extends AssetManager {

    private static final AudioManager INSTANCE = new AudioManager();
    private boolean MUSIC_ENABLED = true;
    private boolean SOUND_ENABLED = true;
    private Music themeSong;
    private Sound draw, hit, shoot, upgrade;

    // Music
    private final String music = "sounds/game_music.mp3";

    // Sounds
    private final String drawSound = "sounds/arrow_draw.wav";
    private final String shootSound = "sounds/arrow_shoot.wav";
    private final String hitSound = "sounds/arrow_hit.wav";
    private final String upgradeSound = "sounds/arrow_upgrade.wav";

    private AudioManager() {
        load(music, Music.class);
        finishLoading();
        themeSong = get(music);
        themeSong.setLooping(true);
        themeSong.setVolume(0.5f);
        themeSong.play();
    }

    public static AudioManager getInstance() {
        return INSTANCE;
    }

    public void initSound() {
        load(drawSound, Sound.class);
        load(shootSound, Sound.class);
        //load(hitSound, Sound.class);
        //load(upgradeSound, Sound.class);
        finishLoading();
        //hit = get(hitSound);
        shoot = get(shootSound);
        draw = get(drawSound);
        //upgrade = get(upgradeSound);
    }

    public void playSound(String sound) {
        if(SOUND_ENABLED) {
            switch (sound) {
                case "draw":
                    draw.play();
                    break;
                default: // "shoot"
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
