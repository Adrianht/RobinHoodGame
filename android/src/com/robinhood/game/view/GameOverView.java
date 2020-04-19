package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;


public class GameOverView extends View {

    private final Controller controller;

    public GameOverView(Controller cont, Model model) {
        this.controller = cont;
        String usernameWinner = controller.getWinner();

        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        // Creating text field for game results
        Skin skinButton = new Skin(Gdx.files.internal("skin/dark-hdpi/Holo-dark-hdpi.json"));
        Skin skinGameOver = new Skin(Gdx.files.internal("skin/craftacular/craftacular-ui.json"));
        Skin skinResult = new Skin(Gdx.files.internal("skin/shade/uiskin.json"));
        Label gameOver = new Label("GAME OVER", skinGameOver);
        Label gameResult = new Label(usernameWinner + " won the game!", skinResult);
        TextButton menu = new TextButton("Menu", skinButton);
        
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
        stage.addActor(table);

        table.row().pad(500, 0, 10, 0);
        table.add(gameOver).fillX().uniform().width(300f).height(100f);
        table.row().pad(10, 0, 10, 0);
        gameOver.setAlignment(Align.center); // Align center
        table.add(gameResult).fillX().uniform().width(300f).height(100f);
        table.row().pad(70, 0, 70, 0);
        gameResult.setAlignment(Align.center); // Align center
        table.add(menu).fillX().uniform().width(300f).height(100f);
        table.row().pad(70, 0, 10, 0);

        gameOver.setFontScale(4.0f );
        gameResult.setFontScale(3.0f);

        // ClickListener triggered by user clicks on Button/Actor to call appropriate actions
        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.navigateTo("MENU");
            }
        });

        controller.endGameInstance();
    }
}
