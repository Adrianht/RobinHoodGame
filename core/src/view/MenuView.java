package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import controller.Controller;

public class MenuView extends View {

    private final Controller controller;

    public MenuView(Controller cont) {

        this.controller = cont;

        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        Button settingsButton = new Button("settings");
        Button loadingButton = new Button("loading");
        super.stage.addActor(settingsButton);
        super.stage.addActor(loadingButton);

        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                if(clickX > 300) {
                    System.out.println("TO SETTINGS!");
                    controller.navigateTo("LOADING");
                } else {
                    System.out.println("TO LOADING!");
                    controller.navigateTo("SETTINGS");
                }
            }
        });
    }

}
