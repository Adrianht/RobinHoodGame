package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
import com.robinhood.game.assetManagers.GameAssetManager;

/**
 * Superclass in Template method pattern - base of all application UIs.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public abstract class View {

    protected Controller controller;
    protected Model model;
    protected final Stage stage;
    protected final Table table;

    protected final GameAssetManager assetManager;
    protected final Skin buttonSkin, textSkin, headerSkin;

    View(Controller controller, Model model) {
        this(controller);
        this.model = model;
    }

    View(Controller controller) {
        this.controller = controller;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        assetManager = GameAssetManager.getInstance();
        assetManager.loadSkins();
        assetManager.loadBackgrounds();
        assetManager.finishLoading();
        buttonSkin = assetManager.get(assetManager.buttonSkin);
        textSkin = assetManager.get(assetManager.textSkin);
        headerSkin = assetManager.get(assetManager.headerSkin);

        table = new Table();
        table.setFillParent(true);
        Texture backgroundTexture =
                assetManager.get(assetManager.menuBackground);
        table.setBackground(new TextureRegionDrawable(backgroundTexture));
        stage.addActor(table);
    }

    public void render() {
        stage.draw();
    }

    public void dispose () {
        stage.dispose();
    }

    protected ClickListener generateNavigateListener(final String destination) {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.navigateTo(destination);
            }
        };
    }
}
