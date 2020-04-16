package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
import com.robinhood.game.view.interfaceObjects.Button;
import com.robinhood.game.view.interfaceObjects.LoadingObject;

public class LoadingView extends View {

    private Controller controller;

    private Button playButton;
    private Button menuButton;

    public LoadingView(Controller cont, Model model) {

        this.controller = cont;

        // Set the stage of the View superclass - same in all subclasses
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        // adds all the elements to this interface
        LoadingObject loadingObject = new LoadingObject();
        stage.addActor(loadingObject);

        // This method initiates the creation of a FirebaseConnector
        // and search for opponent in the controller
        this.controller.findPlayer();

        playButton = new Button("play");
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.navigateTo("GAME");
            }
        });

        menuButton = new Button("menu");
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.cancelFindPlayer();
                controller.navigateTo("MENU");
            }
        });
    }

    @Override
    public void render() {
            if(controller.isGameInitialized()) {
                menuButton.remove();
                stage.addActor(playButton);
            } else {
                playButton.remove();
                stage.addActor(menuButton);
            }
        super.render();
    }

  /*
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

   */
}
