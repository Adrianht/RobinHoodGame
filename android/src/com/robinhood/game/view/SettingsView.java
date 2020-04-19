package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.robinhood.game.controller.Controller;

/**
 * Subclass in Template method pattern creating the UI of game settings menu.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class SettingsView extends View {

    public SettingsView(Controller cont) {
        super(cont);

        // TODO: add possibility to change username and do it by calling:
        //  controller.setUsername(username)

        // Create and position buttons of current UI
        Skin skin = new Skin(Gdx.files.internal(
                "skin/dark-hdpi/Holo-dark-hdpi.json"));
        TextButton backButton = new TextButton("Back", skin);
        Label musicOnOffLabel = new Label("Music", skin);
        Label soundOnOffLabel = new Label("Effects", skin);
        final CheckBox musicCheckbox = new CheckBox(null, skin);
        final CheckBox soundCheckbox = new CheckBox(null, skin);

        musicCheckbox.setChecked(getController().getMusicEnabled());
        soundCheckbox.setChecked(getController().getSoundEnabled());

        table.row().pad(350,0,0,0);
        table.add(musicOnOffLabel).left().width(300f).height(100f);
        table.add(musicCheckbox);
        table.row().pad(10,0,0,0);
        table.add(soundOnOffLabel).left().width(300f).height(100f);
        table.add(soundCheckbox);
        table.row().pad(50, 0, 0, 0);
        table.add(backButton).colspan(3).center().width(300f).height(100f);

        backButton.addListener(generateNavigateListener("MENU"));
        musicCheckbox.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                boolean enabled = musicCheckbox.isChecked();
                getController().setMusicEnabled( enabled );
            }
        });
        soundCheckbox.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                boolean enabled = soundCheckbox.isChecked();
                getController().setSoundEnabled( enabled );
            }
        });
    }
}
