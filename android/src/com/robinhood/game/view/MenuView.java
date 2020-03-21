package com.robinhood.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;

public class MenuView extends View {

    private Button StartGameButton;
    private Button SettingsButton;
    private Button ExitButton;

    public MenuView(Controller controller, Model model) {
        //super.setData(com.robinhood.game.model.getData("state2"));
        //super.setController(controller);
        super(controller, model);

        //TODO: render data from com.robinhood.game.model for the given view
        super.batch = new SpriteBatch();
        super.img = new Texture("badlogic.jpg");

        StartGameButton = new Button();
        SettingsButton = new Button();
        ExitButton = new Button();

        // Attempt at adding ClickListener, need to fix call for controller
        StartGameButton.addListener( new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //controller.navigateTo("GAME");
            };
        });

        UI.addActor(StartGameButton);
        UI.addActor(SettingsButton);
        UI.addActor(ExitButton);

    }

    @Override
    public void init(){

    }

    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}

}
