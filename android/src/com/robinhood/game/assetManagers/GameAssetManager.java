package com.robinhood.game.assetManagers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public final class GameAssetManager extends AssetManager {

    private static final GameAssetManager INSTANCE = new GameAssetManager();

    //public TextureRegionDrawable menuBackground, gameBackground;


    // TODO: Add files specific files
    // Textures
    public final String firstPlayerImage = "";
    public final String secondPlayerImage = "";

    // Background Textures
    public final String menuBackgroundString = "background-smaller.png";
    public final String gameBackgroundString = "game-back.png";
    //public final String gameBackground2 = "";

    // Skins Parameters
    public final String buttonSkinParams = "skin/dark-hdpi/Holo-dark-hdpi.atlas";
    public final String textSkinParams = "skin/shade/uiskin.atlas";
    public final String headerSkinParams = "skin/craftacular/craftacular-ui.atlas";
    // Skins
    public final String buttonSkin = "skin/dark-hdpi/Holo-dark-hdpi.json";
    public final String textSkin = "skin/shade/uiskin.json";
    public final String headerSkin = "skin/craftacular/craftacular-ui.json";

    private GameAssetManager() {}

    public static GameAssetManager getInstance() {
        return INSTANCE;
    }

    public void loadBackgrounds() {
        load(menuBackgroundString, Texture.class);
        load(gameBackgroundString, Texture.class);
        finishLoading();
        //menuBackground = get(menuBackgroundString);
        //gameBackground = get(gameBackgroundString);
    }

    public void loadSkins() {
        SkinParameter buttonParams = new SkinParameter(buttonSkinParams);
        SkinParameter textParams = new SkinParameter(textSkinParams);
        SkinParameter headerParams = new SkinParameter(headerSkinParams);
        load(buttonSkin, Skin.class, buttonParams);
        load(textSkin, Skin.class, textParams);
        load(headerSkin, Skin.class, headerParams);
    }
}
