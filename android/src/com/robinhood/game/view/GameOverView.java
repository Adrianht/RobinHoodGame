package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;

/**
 * Subclass in Template method pattern creating the UI when game is over.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class GameOverView extends View {

    public GameOverView(Controller controller, Model model) {
        super(controller);
        controller.endGameInstance();

        // Create and position text fields and buttons of current UI
        Skin skinButton = new Skin(Gdx.files.internal(
                "skin/dark-hdpi/Holo-dark-hdpi.json"));
        Skin skinGameOver = new Skin(Gdx.files.internal(
                "skin/craftacular/craftacular-ui.json"));
        Skin skinResult = new Skin(Gdx.files.internal(
                "skin/shade/uiskin.json"));
        Label gameOver = new Label("GAME OVER", skinGameOver);
        Label gameResult = new Label(
                model.getGameWinner() + " won the game!", skinResult);
        TextButton menu = new TextButton("Menu", skinButton);
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

        menu.addListener(generateNavigateListener("MENU"));
    }
}
