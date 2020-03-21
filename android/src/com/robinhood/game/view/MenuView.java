package com.robinhood.game.view;

<<<<<<< HEAD:android/src/com/robinhood/game/view/MenuView.java
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
=======
import com.badlogic.gdx.Gdx;
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/view/MenuView.java
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

<<<<<<< HEAD:android/src/com/robinhood/game/view/MenuView.java
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
=======
import controller.Controller;
import view.interfaceObjects.Button;
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/view/MenuView.java

public class MenuView extends View {

    private final Controller controller;

<<<<<<< HEAD:android/src/com/robinhood/game/view/MenuView.java
    public MenuView(Controller controller, Model model) {
        //super.setData(com.robinhood.game.model.getData("state2"));
        //super.setController(controller);
        super(controller, model);

        //TODO: render data from com.robinhood.game.model for the given view
        super.batch = new SpriteBatch();
        super.img = new Texture("badlogic.jpg");
=======
    public MenuView(Controller cont) {

        this.controller = cont;
>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/view/MenuView.java

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
                controller.navigateTo("GAME");
            } else {
                System.out.println("TO SETTINGS!");
                controller.navigateTo("SETTINGS");
            }
        }
    };

}
