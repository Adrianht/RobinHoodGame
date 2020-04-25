package com.robinhood.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import com.robinhood.game.controller.Controller;

/**
 * Subclass in Template method pattern creating the UI if
 * the games instruction page.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class InstructionsView extends View {

    public InstructionsView(Controller controller) {
        super(controller);

        Label headerText =
                new Label("INSTRUCTIONS", headerSkin);
        Label instructions1 = new Label(
                " - Initiate game by pressing the 'New Game' button.\n"
                        + " - This will start your search for potential opponents.\n"
                        + " - You can change music settings and " +
                        "your username in 'Settings'.\n"
                , textSkin);
        Label instructions2 = new Label(
                " - Health bars display the players remaining life.\n"
                        + " - You win the game when your opponent is out of life.\n"
                        + " - On your turn, you can move your archer, " +
                        "upgrade your arrow, or launch an attack.\n"
                        + " - To move your archer, press one of " +
                        "the arrows on the sides.\n"
                        + " - To upgrade your weapon, press on of the shooting " +
                        "arrow buttons in the middle of the screen.\n"
                        + " - To launch an attack on your opponent, drag across the screen.\n"
                , textSkin);
        Label instructions3 = new Label(
                " - To move and upgrade your arrow costs energy points.\n"
                        + " - The bar on the bottom of the screen " +
                        "indicates roughly how many energy points you have available.\n"
                        + " - You get 1 extra bar of energy points every turn.\n"
                        + " - The upgrades on your arrow cost 2, 4, and 6 bars. " +
                        "But does also take the same amount of \nbars from your opponents " +
                        "life if you hit him within the same turn.\n"
                , textSkin);
        TextButton menuButton =
                new TextButton("Menu", buttonSkin);

        System.out.println(instructions1);
        System.out.println(instructions2);
        System.out.println(instructions3);

        headerText.setFontScale(2f);
        instructions1.setFontScale(2f);
        instructions2.setFontScale(2f);
        instructions3.setFontScale(2f);

        table.setDebug(true);
        Texture backgroundTexture =
                assetManager.get(assetManager.instructionBackground);
        table.setBackground(new TextureRegionDrawable(backgroundTexture));
        table.bottom();
        table.row().pad(0f, 0, 30f, 0);
        table.add(headerText)
                .fillX().uniform().width(300f).height(100f);
        headerText.setAlignment(Align.center);
        table.row().pad(0, 0, 70f, 0);
        table.add(instructions1)
                .fillX().uniform().width(300f).height(100f);
        instructions1.setAlignment(Align.center);
        table.row().pad(0f, 0, 70f, 0);
        table.add(instructions2)
                .fillX().uniform().width(300f).height(100f);
        instructions2.setAlignment(Align.center);
        table.row().pad(0f, 0, 60f, 0);
        table.add(instructions3)
                .fillX().uniform().width(300f).height(100f);
        instructions3.setAlignment(Align.center);
        table.row().pad(0f, 0, 100f, 0);
        table.add(menuButton)
                .fillX().uniform().width(300f).height(100f);
        table.row().pad(0f, 0, 60f, 0);

        menuButton.addListener(
                generateNavigateListener("MENU"));
    }
}
