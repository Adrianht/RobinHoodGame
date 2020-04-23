package com.robinhood.game.assetManagers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;

public final class GameAssetManager extends AssetManager {

    private static final GameAssetManager INSTANCE = new GameAssetManager();

    // Background Textures
    public final String menuBackgroundString = "background-smaller.png";
    public final String gameBackgroundString = "game-back.png";

    // Interface Object Textures
    public final String archer = "archer-icon.png";
    public final String dragIndicator = "dragInd.png";
    public final String level1Arrow = "Level1.png";
    public final String level2Arrow = "Level2.png";
    public final String level3Arrow = "Level3.png";
    public final String level4Arrow = "Level4.png";

    // Button Textures
    public final String leftImageButton = "left.png";
    public final String rightImageButton = "right.png";
    public final String Level2ImageButton = "Level2.png";
    public final String Level3ImageButton = "Level3.png";
    public final String Level4ImageButton = "Level4.png";

    // Skins Parameters
    public final String buttonSkinParams = "skin/dark-hdpi/Holo-dark-hdpi.atlas";
    public final String textSkinParams = "skin/shade/uiskin.atlas";
    public final String headerSkinParams = "skin/craftacular/craftacular-ui.atlas";
    public final String textFieldSkinParams = "skin/shade/uiskin.atlas";

    // Skins
    public final String buttonSkin = "skin/dark-hdpi/Holo-dark-hdpi.json";
    public final String textSkin = "skin/shade/uiskin.json";
    public final String headerSkin = "skin/craftacular/craftacular-ui.json";

    // TextureAtlas
    public final String healthBarAtlas = "healthbarSheet.pack";

    private GameAssetManager() {}

    public static GameAssetManager getInstance() {
        return INSTANCE;
    }

    public void loadBackgrounds() {
        load(menuBackgroundString, Texture.class);
        load(gameBackgroundString, Texture.class);
    }

    public void loadSkins() {
        SkinParameter buttonParams = new SkinParameter(buttonSkinParams);
        SkinParameter textParams = new SkinParameter(textSkinParams);
        SkinParameter headerParams = new SkinParameter(headerSkinParams);
        load(buttonSkin, Skin.class, buttonParams);
        load(textSkin, Skin.class, textParams);
        load(headerSkin, Skin.class, headerParams);
    }

    public void loadImageButtonTextures() {
        load(leftImageButton, Texture.class);
        load(rightImageButton, Texture.class);
        load(Level2ImageButton, Texture.class);
        load(Level3ImageButton, Texture.class);
        load(Level4ImageButton, Texture.class);
    }

    public void loadInterfaceObjectsTextures() {
        load(archer, Texture.class);
        load(dragIndicator, Texture.class);
        load(level1Arrow, Texture.class);
        load(level2Arrow, Texture.class);
        load(level3Arrow, Texture.class);
        load(level4Arrow, Texture.class);
    }

    public void loadTextureAtlas() {
        load(healthBarAtlas, TextureAtlas.class);
    }

}
