package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import controller.Controller;
import view.interfaceObjects.Button;

public class GameOver extends View {

    private final Controller controller;

    public GameOver(Controller controller) {
        this.controller = controller;

        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        Button menuButton = new Button("menu");
        super.stage.addActor(menuButton);


    }

    private ClickListener gameOverViewListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float clickX, float clickY) {

            if (clickY < 500) {
                System.out.println("TO MENU!");
                controller.navigateTo("MENU");
            }

        }
    };
}