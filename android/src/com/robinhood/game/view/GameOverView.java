package com.robinhood.game.view;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
        super(controller, model);
        String winner = model.getGameWinner();
        controller.endGameInstance();

        Label headerText = new Label("GAME OVER", headerSkin);
        Label resultText = new Label(
                winner + " won the game!", textSkin);
        TextButton menu = new TextButton("Menu", buttonSkin);

        headerText.setFontScale(4.0f);
        resultText.setFontScale(3.0f);

        table.row().pad(500, 0, 10, 0);
        table.add(headerText)
                .fillX().uniform().width(300f).height(100f);
        headerText.setAlignment(Align.center);
        table.row().pad(10, 0, 10, 0);
        table.add(resultText)
                .fillX().uniform().width(300f).height(100f);
        resultText.setAlignment(Align.center);
        table.row().pad(70, 0, 70, 0);
        table.add(menu)
                .fillX().uniform().width(300f).height(100f);
        table.row().pad(70, 0, 10, 0);

        menu.addListener(
                generateNavigateListener("MENU"));
    }
}
