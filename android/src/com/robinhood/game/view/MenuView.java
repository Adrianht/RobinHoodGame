package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.robinhood.game.controller.Controller;

/**
 * Subclass in Template method pattern creating the UI of the game menu.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class MenuView extends View  {

    public MenuView(Controller controller) {
        super(controller);

        // Create and position buttons of current UI
        Skin skin = new Skin(Gdx.files.internal(
                "skin/dark-hdpi/Holo-dark-hdpi.json"));
        TextButton newGame = new TextButton("New Game", skin);
        TextButton settings = new TextButton("Settings", skin);
        TextButton exit = new TextButton("Exit", skin);
        table.row().pad(400, 0, 10, 0);
        table.add(newGame).fillX().uniform().width(300f).height(100f);
        table.row().pad(10, 0, 10, 0);
        table.add(settings).fillX().uniform().width(300f).height(100f);
        table.row().pad(10, 0, 10, 0);
        table.add(exit).fillX().uniform().width(300f).height(100f);

        settings.addListener(generateNavigateListener("SETTINGS"));
        newGame.addListener(generateNavigateListener("LOADING"));
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                getController().exitApplication();
            }
        });
    }
}
