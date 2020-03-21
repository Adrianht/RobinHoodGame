package com.robinhood.game.view;

<<<<<<< HEAD:android/src/com/robinhood/game/view/GameView.java
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
=======
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import controller.Controller;
import model.Archer;
import model.Model;
import model.Player;
import view.interfaceObjects.Button;
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/view/GameView.java


public class GameView extends View {

    private final Controller controller;

<<<<<<< HEAD:android/src/com/robinhood/game/view/GameView.java
        /*
         TODO:
         Use/create methods in com.robinhood.game.model to extract current data
         from objects related to this view.
=======
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/view/GameView.java

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
        Archer archer1 = model.getPlayer1().getArcher();
        Archer archer2 = model.getPlayer2().getArcher();
        stage.addActor(menuButton);
        stage.addActor(leftButton);
        stage.addActor(rightButton);
        stage.addActor(archer1);
        stage.addActor(archer2);

        // adds all listeners to this interface
        stage.addListener(gameListener);

        /*
         TODO:
<<<<<<< HEAD:android/src/com/robinhood/game/view/GameView.java
         Use/create methods in com.robinhood.game.model to extract updated data
=======
         Use/create methods in model to extract current data
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/view/GameView.java
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
            } else if(clickY < 100 && clickX > 200 && clickX < 450) {
                System.out.println("TO MENU!");
                controller.navigateTo("MENU");
            } else if(clickY < 100 && clickX > 450) {
                System.out.println("RIGHT!");
                controller.move(false);
            }

            /*
                TODO:

                Clicks in this view should trgger one of the following methods
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