package com.robinhood.game.view;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.robinhood.game.controller.Controller;

/**
 * Subclass in Template method pattern creating the UI of the game menu.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class MenuView extends View  {

    public MenuView(final Controller controller) {
        super(controller);

        TextButton gameButton =
                new TextButton("New Game", buttonSkin);
        TextButton settingsButton =
                new TextButton("Settings", buttonSkin);
        TextButton instructionsButton =
                new TextButton("Game Instructions", buttonSkin);
        TextButton exitButton =
                new TextButton("Exit", buttonSkin);

        table.row().pad(400, 0, 10, 0);
        table.add(gameButton)
                .fillX().uniform().width(300f).height(100f);
        table.row().pad(10, 0, 10, 0);
        table.add(settingsButton)
                .fillX().uniform().width(300f).height(100f);
        table.row().pad(10, 0, 10, 0);
        table.add(instructionsButton)
                .fillX().uniform().width(300f).height(100f);
        table.row().pad(10, 0, 10, 0);
        table.add(exitButton)
                .fillX().uniform().width(300f).height(100f);

        settingsButton.addListener(
                generateNavigateListener("SETTINGS"));
        instructionsButton.addListener(
                generateNavigateListener("INSTRUCTIONS"));
        gameButton.addListener(
                generateNavigateListener("LOADING"));
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.exitApplication();
            }
        });
    }
}
