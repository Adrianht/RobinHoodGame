package com.robinhood.game.view;

<<<<<<< HEAD:android/src/com/robinhood/game/view/SettingsView.java
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
=======
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import controller.Controller;
import model.Model;
import view.interfaceObjects.Button;
import model.SoundBar;
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/view/SettingsView.java

public class SettingsView extends View {

    private final Controller controller;

    public SettingsView(Controller cont, Model model) {

<<<<<<< HEAD:android/src/com/robinhood/game/view/SettingsView.java
        /*
         TODO:
         use/create methods in com.robinhood.game.model to extract current data
         from objects related to this view
         */
=======
        this.controller = cont;
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/view/SettingsView.java

        // Stage: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Stage.html
        // set the stage of the View superclass - same in all subclasses
        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        // Actor: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Actor.html
        // adds all the elements to this interface
        Button menuButton = new Button("menu");
        super.stage.addActor(menuButton);
        SoundBar soundBar = model.getSoundBar();
        super.stage.addActor(soundBar);

        // adds all listeners to this interface
        stage.addListener(settingsViewListener);
    }

    // ClickListener: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/utils/ClickListener.html
    // ClickListener triggered by user clicks to call appropriate actions
    private ClickListener settingsViewListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float clickX, float clickY) {

            // clickX and clickY are the (x,y)-coordinates of the users click

            // Change sound if the user clicks the upper part of the screen
            // else redirect the user to the menu
            if(clickY > 200) {
                System.out.println("CHANGE SOUND SETTINGS");
                controller.changeSound();
            } else {
                System.out.println("TO MENU!");
                controller.navigateTo("MENU");
            }
        }
    };

}
