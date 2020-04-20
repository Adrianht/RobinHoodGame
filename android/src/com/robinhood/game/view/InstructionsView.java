package com.robinhood.game.view;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
                " - You win the game when the other " +
                        "player’s HitPoints runs out. \n" +
                        "- It remains your turn until " +
                        "you shoot the arrow.\n" +
                        "- Hitting the opponent results " +
                        "in the opponent losing points\n"
                , textSkin);
        Label instructions2 = new Label(
                " - Every move costs 2 energy points\n" +
                        "- Upgrade your weapon if wanted " +
                        "before making the shot\n" +
                        "- Drag your finger and aim towards " +
                        "the opponent\n" +
                        "- When aimed, let go of the screen " +
                        "to shoot the opponent\n"
                , textSkin);
        Label instructions3 = new Label(
                " - Both players start with 100 " +
                        "HitPoints and 20 EnergyPoints. \n" +
                        "- EnergyPoints can be used to purchase " +
                        "upgraded arrows with increased damage\n" +
                        "- The cost of the upgraded arrows are " +
                        "indicated in the game \n" +
                        "- For every turn, the players " +
                        "gains 10 EnergyPoints \n"
                , textSkin);
        TextButton menuButton =
                new TextButton("Menu", buttonSkin);

        headerText.setFontScale(3f);
        instructions1.setFontScale(2.5f);
        instructions2.setFontScale(2.5f);
        instructions3.setFontScale(2.5f);

        table.bottom();
        table.row().pad(20f, 0, 30f, 0);
        table.add(headerText)
                .fillX().uniform().width(300f).height(100f);
        headerText.setAlignment(Align.center);
        table.row().pad(0, 0, 30f, 0);
        table.add(instructions1)
                .fillX().uniform().width(300f).height(100f);
        instructions1.setAlignment(Align.center);
        table.row().pad(30f, 0, 30f, 0);
        table.add(instructions2)
                .fillX().uniform().width(300f).height(100f);
        instructions2.setAlignment(Align.center);
        table.row().pad(30f, 0, 50f, 0);
        table.add(instructions3)
                .fillX().uniform().width(300f).height(100f);
        instructions3.setAlignment(Align.center);
        table.row().pad(30f, 0, 80f, 0);
        table.add(menuButton)
                .fillX().uniform().width(300f).height(100f);
        table.row().pad(10f, 0, 60f, 0);

        menuButton.addListener(
                generateNavigateListener("MENU"));
    }
}
