package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.robinhood.game.controller.Controller;

public class MenuView extends View  {

    private final Controller controller;

    // Creating buttons for home screen
    Skin skin = new Skin(Gdx.files.internal("skin/dark-hdpi/Holo-dark-hdpi.json"));
    TextButton newGame = new TextButton("New Game", skin);
    TextButton settings = new TextButton("Settings", skin);
    TextButton exit = new TextButton("Exit", skin);
    TextButton gameOver = new TextButton("GAME OVER", skin);

    public MenuView(Controller cont) {

        this.controller = cont;

        // set the stage of the View superclass - same in all subclasses
        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        // Create new table that fills the screen -> Table added to stage
        Table table = new Table();
        table.setFillParent(true);
        // Cheap way to add background, fix later using asset manager
        table.setBackground(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("background-smaller.png")
                        )
                )
        );
        // table.setPosition(400, 0);      // Want to move the buttons to the right
        stage.addActor(table);

        table.row().pad(400, 0, 10, 0);
        table.add(newGame).fillX().uniform().width(300f).height(100f);
        table.row().pad(10, 0, 10, 0);
        table.add(settings).fillX().uniform().width(300f).height(100f);
        table.row().pad(10, 0, 10, 0);
        table.add(exit).fillX().uniform().width(300f).height(100f);
        table.row().pad(10, 0, 10, 0);
        table.add(gameOver).fillX().uniform().width(300f).height(100f);

        // ClickListener triggered by user clicks on Button/Actor to call appropriate actions
        settings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("TO SETTINGS!");
                controller.navigateTo("SETTINGS");
            }
        });
        newGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("TO GAME!");
                controller.navigateTo("LOADING");
            }
        });
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("EXITING APP!");
                controller.exitApplication();
            }
        });
        gameOver.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("Game Over!");
                controller.navigateTo("GAMEOVER");
            }
        });
    }
}
