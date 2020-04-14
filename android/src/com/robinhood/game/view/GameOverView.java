package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
import com.robinhood.game.view.interfaceObjects.Button;

import java.util.List;


public class GameOverView extends View {

    private final Controller controller;

    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font = new BitmapFont();
    private int playerNrWinner;

    public GameOverView(Controller cont, Model model) {
        this.controller = cont;

        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        Button menuButton = new Button("menu");
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.cancelFindPlayer();
                controller.navigateTo("MENU");
            }
        });
        stage.addActor(menuButton);

        List<Integer> hpList = controller.getHP();
        playerNrWinner = 0;
        for (int i = 0; i < hpList.size(); i++ ){
            if(hpList.get(i) > 0) {
                playerNrWinner = i;
                break;
            }
        }

        controller.endGameInstance();
    }

    @Override
    public void render() {
        float[] values = hextoRGB("#5f8db0");
        Gdx.gl.glClearColor(values[0], values[1], values[2], 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        batch.begin();
        font.draw(batch,
                ("GAME OVER..." +
                    "\ṅPlayer number " + playerNrWinner + " won the game."),
                250, 250);
        batch.end();
    }

}