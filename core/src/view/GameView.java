package view;

import controller.Controller;
import model.Model;
import model.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


import java.util.List;

import view.interfaceObjects.*;


public class GameView extends View {

    private final Controller controller;


    public GameView(Controller cont, Model model) {

        this.controller = cont;

        // Stage: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Stage.html
        // set the stage of the View superclass - same in all subclasses
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Actor: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Actor.html
        // adds all the elements to this interface
        Button menuButton = new Button("menu");
        Button leftButton = new Button("left");
        Button rightButton = new Button("right");
        stage.addActor(menuButton);
        stage.addActor(leftButton);
        stage.addActor(rightButton);

        // adds archers, arrows and arena
        List<Actor> actors = model.getActors();
        for (Actor actor: actors) {
            stage.addActor(actor);
        }

        // adds all listeners to this interface
        stage.addListener(gameListener);

        /*
         TODO:
         Use/create methods in model to extract current data
         from objects related to this view.
         This includes getting data from stage, players, etc. and
         checking if the game is over.
         */

    }

    // ClickListener: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/utils/ClickListener.html
    // ClickListener triggered by user clicks to call appropriate actions
    private ClickListener gameListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float clickX, float clickY) {

            if(clickY < 100 && clickX < 200) {
                System.out.println("LEFT!");
                controller.move(true);
                //updateView()
            } else if(clickY < 100 && clickX > 200 && clickX < 450) {
                System.out.println("TO MENU!");
                controller.navigateTo("MENU");
            } else if(clickY < 100 && clickX > 450) {
                System.out.println("RIGHT!");
                controller.move(false);
                //updateView()
            }



            /*
                TODO:
                Clicks in this view should trigger one of the following methods
                in the controller, based on the position and duration of the click:
                    - controller.navigateTo("MENU") (ONLY IF GAMEOVER)
                    - controller.move(Boolean left)
                    - controller.buyArrow(String type)
                    - controller.drawBow(Vector2 vector2)
                Additional:
                    - if move() or buyArrow is called, they should be followed by
                        updateView()
             */


        }
    };



}
