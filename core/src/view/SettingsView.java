package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import controller.Controller;
import model.Model;

public class SettingsView extends View {

    private final Controller controller;
    private final Model model;

    public SettingsView(Controller cont, Model model) {

        this.controller = cont;
        this.model = model;

        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        Button menuButton = new Button("menu2");
        super.stage.addActor(menuButton);

        /*
           TODO
           Add sound-bar actor
        */


        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {

                System.out.println("TO MENU!");
                controller.navigateTo("MENU");

                /*
                    TODO
                    Clicks in this view should trgger one of the following methods
                    in the controller, based on the position of the click:
                        - controller.navigateTo("MENU")
                        - controller.changeSound()

                */
            }
        });



        /*
         TODO:
         use/create methods in model to extract current data
         from objects related to this view

         Here or in an soundbar actor-componetn
         */

    }

}
