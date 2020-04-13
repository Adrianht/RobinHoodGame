package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.robinhood.game.controller.Controller;
import com.robinhood.game.view.interfaceObjects.*;

public class MenuView extends View {

    private final Controller controller;

    public MenuView(Controller cont) {

        this.controller = cont;

        // Stage: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Stage.html
        // set the stage of the View superclass - same in all subclasses
        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        // Actor: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Actor.html
        // adds all the elements to this interface
        Button settingsButton = new Button("settings");
        Button loadingButton = new Button("play");
        super.stage.addActor(settingsButton);
        super.stage.addActor(loadingButton);
        // adds all listeners to this interface
        stage.addListener(menuViewListener);
    }

    // ClickListener: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/utils/ClickListener.html
    // ClickListener triggered by user clicks to call appropriate actions
    private ClickListener menuViewListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float clickX, float clickY) {

            // clickX and clickY are the (x,y)-coordinates of the users click

            // this is only example code
            // here the interface change to loading if the user click on the area made by the
            // leftmost 300 pixels of the screen, else the interface change to settings
            if(clickX < 300) {
                System.out.println("TO PLAY!");
                controller.navigateTo("LOBBY");

            } else {
                System.out.println("TO SETTINGS!");
                controller.navigateTo("SETTINGS");

            }
        }
    };

}
