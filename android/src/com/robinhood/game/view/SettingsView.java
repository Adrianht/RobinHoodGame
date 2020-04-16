package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.robinhood.game.controller.Controller;

public class SettingsView extends View {

    private final Controller controller;

    // Labels
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;

    // Skin
    private Skin skin = new Skin(Gdx.files.internal("skin/dark-hdpi/Holo-dark-hdpi.json"));

    private final CheckBox musicCheckbox = new CheckBox(null, skin);
    private final CheckBox soundCheckbox = new CheckBox(null, skin);
    private final TextButton backButton = new TextButton("Back", skin);

    public SettingsView(Controller cont) {

        this.controller = cont;

        super.stage = new Stage(new ScreenViewport());

        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        Gdx.input.setInputProcessor(super.stage);

        // Create new table that fills the screen -> Table added to stage
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);

        // Cheap way to add background, TODO: fix later using asset manager
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background.png"))));

        musicCheckbox.setChecked(controller.getMusicEnabled());
        soundCheckbox.setChecked(controller.getSoundEnabled());

        musicOnOffLabel = new Label("Music", skin);
        soundOnOffLabel = new Label("Effects", skin);

        // TODO: add possibility to change username and do it by calling:
        //  controller.setUsername(username)

        musicCheckbox.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                boolean enabled = musicCheckbox.isChecked();
                controller.setMusicEnabled( enabled );
            }
        });

        soundCheckbox.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                boolean enabled = soundCheckbox.isChecked();
                controller.setSoundEnabled( enabled );
            }
        });

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("TO MENU!");
                controller.navigateTo("MENU");
            }
        });

        // Table generated
        table.row().pad(350,0,0,0);
        table.add(musicOnOffLabel).left();
        table.add(musicCheckbox);
        table.row().pad(10,0,0,0);
        table.add(soundOnOffLabel).left();
        table.add(soundCheckbox);
        // Back button
        table.row().pad(50, 0, 0, 0);
        table.add(backButton).colspan(3).center().fillX().height(100f);

        stage.addActor(table);
    }
}