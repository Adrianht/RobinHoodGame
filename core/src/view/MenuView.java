package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import controller.Controller;

import view.interfaceObjects.*;

public class MenuView extends View {

    private final Controller controller;

    public MenuView(Controller cont) {

        this.controller = cont;

        // Stage: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Stage.html
        // set the stage of the View superclass - same in all subclasses
        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        Button settingsButton = new Button("settings");
        Button loadingButton = new Button("play");
        Button exitButton = new Button("exit");
        Button robinHood = new Button("robinhoodpic");

        // ClickListener: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/utils/ClickListener.html
        // ClickListener triggered by user clicks on Button/Actor to call appropriate actions
        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("TO SETTINGS!");
                controller.navigateTo("SETTINGS");
            }
        });
        loadingButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("TO GAME!");
                controller.navigateTo("GAME");
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("EXITING APP!");
                controller.exitApplication();
            }
        });

        // Actor: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Actor.html
        // adds all the elements to this interface
        super.stage.addActor(settingsButton);
        super.stage.addActor(loadingButton);
        super.stage.addActor(exitButton);
        super.stage.addActor(robinHood);
    }

}
