package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
import com.robinhood.game.model.SoundBar;
import com.robinhood.game.view.interfaceObjects.Button;

public class SettingsView extends View {

    private final Controller controller;

    public SettingsView(Controller cont, Model model) {

        this.controller = cont;

        // Stage: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Stage.html
        // set the stage of the View superclass - same in all subclasses
        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        Button menuButton = new Button("backbutton");
        Button robinHood = new Button("robinhoodpic");
        SoundBar soundBar = model.getSoundBar();

        // TODO: add possibility to change username and do it by calling:
        //  controller.setUsername(username)

        // Checks if soundBar already has a clickListener, adds listener if not
        if(!soundBar.getListener()){
            soundBar.addListener(controller);
        }

        // ClickListener: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/utils/ClickListener.html
        // ClickListener triggered by user clicks on Button/Actor to call appropriate actions
        menuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("TO MENU!");
                controller.navigateTo("MENU");
            }
        });

        // Actor: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Actor.html
        // adds all the elements to this interface
        super.stage.addActor(menuButton);
        super.stage.addActor(robinHood);
        super.stage.addActor(soundBar);
    }

}