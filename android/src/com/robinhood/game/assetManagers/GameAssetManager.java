package com.robinhood.game.assetManagers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;

public final class GameAssetManager extends AssetManager {

    private static final GameAssetManager INSTANCE = new GameAssetManager();

    public static GameAssetManager getInstance() {
        return INSTANCE;
    }

    private GameAssetManager() {
    }


    // TODO: Add files specific files
    // Textures
    public final String firstPlayerImage = "";
    public final String secondPlayerImage = "";

    // Background Textures
    public final String menuBackground = "background-smaller.png";
    public final String gameBackground1 = "";
    public final String gameBackground2 = "";

    // Skins Parameters
    public final String buttonSkinParams = "skin/dark-hdpi/Holo-dark-hdpi.atlas";
    public final String textSkinParams = "skin/shade/uiskin.atlas";
    public final String headerSkinParams = "skin/craftacular/craftacular-ui.atlas";
    // Skins
    public final String buttonSkin = "skin/dark-hdpi/Holo-dark-hdpi.json";
    public final String textSkin = "skin/shade/uiskin.json";
    public final String headerSkin = "skin/craftacular/craftacular-ui.json";


    public void loadBackgrounds() {
        //manager.load(menuBackground, Texture.class);
        //manager.load(gameBackground1, Texture.class);
        //manager.load(gameBackground2, Texture.class);
    }

    public void loadSkins() {
        SkinParameter buttonParams = new SkinParameter(buttonSkinParams);
        SkinParameter textParams = new SkinParameter(textSkinParams);
        SkinParameter headerParams = new SkinParameter(headerSkinParams);
        //manager.load(buttonSkin, Skin.class, buttonParams);
        //manager.load(textSkin, Skin.class, textParams);
        //manager.load(headerSkin, Skin.class, headerParams);
    }

}