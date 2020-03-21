package com.robinhood.game.view;

<<<<<<< HEAD:android/src/com/robinhood/game/view/LoadingView.java
import com.robinhood.game.controller.Controller;
=======
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import controller.Controller;
import model.Model;
import view.interfaceObjects.Button;
import view.interfaceObjects.LoadingObject;

public class LoadingView extends View {

    private Controller controller;

    public LoadingView(Controller cont, Model model) {

        this.controller = cont;

        // This method initiates the creation of a FirebaseConnector
        // and search for opponent in the controller
        this.controller.findPlayer();

        // Stage: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Stage.html
        // Set the stage of the View superclass - same in all subclasses
        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/view/LoadingView.java

        // adds all the elements to this interface
        LoadingObject loadingObject = new LoadingObject();
        stage.addActor(loadingObject);

        // the following button and clicklistener are only examples to
        // demonstrate navigation logic
        Button menuButton = new Button("menu");
        super.stage.addActor(menuButton);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("TO MENU!");
                controller.navigateTo("MENU");
            }
        });
    }

}
