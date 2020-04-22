package com.robinhood.game.view;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;

/**
 * Subclass in Template method pattern creating the UI of game settings menu.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class SettingsView extends View {

    public SettingsView(final Controller controller, Model model) {
        super(controller, model);

        TextButton menuButton =
                new TextButton("Menu", buttonSkin);
        Label musicOnOffLabel =
                new Label("Music", buttonSkin);
        Label soundOnOffLabel =
                new Label("Effects", buttonSkin);
        final CheckBox musicCheckbox =
                new CheckBox(null, buttonSkin);
        final CheckBox soundCheckbox =
                new CheckBox(null, buttonSkin);
        final TextField usernameField =
                new TextField(model.getMyUsername(), buttonSkin);

        musicCheckbox.setChecked(model.isMusicEnabled());
        soundCheckbox.setChecked(model.isSoundEnabled());
        usernameField.setMaxLength(12);

        table.row().pad(350,0,0,0);
        table.add(musicOnOffLabel)
                .left().width(300f).height(100f);
        table.add(musicCheckbox);
        table.row().pad(10,0,0,0);
        table.add(soundOnOffLabel)
                .left().width(300f).height(100f);
        table.add(soundCheckbox);
        table.row().pad(50, 0, 0, 0);
        table.add(usernameField)
                .colspan(3).center().width(300f).height(100f);
        table.row().pad(30, 0, 0, 0);
        table.add(menuButton)
                .colspan(3).center().width(300f).height(100f);

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.setMyUsername(usernameField.getText());
                controller.navigateTo("MENU");
            }
        });
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
    }
}
