package com.robinhood.game.view;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;

/**
 * Subclass in Template method pattern creating the UI while
 * a player is waiting for opponent(s).
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class LoadingView extends View {

    private final TextButton playButton, cancelButton;
    private final Label loadingText;

    public LoadingView(final Controller controller, Model model) {
        super(controller, model);
        controller.findPlayers();

        playButton = new TextButton("Play!", buttonSkin);
        cancelButton = new TextButton("Cancel", buttonSkin);
        loadingText = new Label("Finding opponent...", textSkin);

        loadingText.setFontScale(3f);

        table.row().pad(400, 0, 10, 0);
        table.add(playButton)
                .fillX().uniform().width(300f).height(100f);
        table.row().pad(10, 0, 10, 0);
        table.add(loadingText)
                .fillX().uniform().width(300f).height(100f);
        loadingText.setAlignment(Align.center);
        table.row().pad(10, 0, 10, 0);
        table.add(cancelButton)
                .fillX().uniform().width(300f).height(100f);

        playButton.addListener(
                generateNavigateListener("GAME"));
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.cancelFindPlayer();
                controller.navigateTo("MENU");
            }
        });
    }

    @Override
    public void render() {
        cancelButton.setVisible(!model.isGameInitialized());
        loadingText.setVisible(!model.isGameInitialized());
        playButton.setVisible(model.isGameInitialized());
        super.render();
    }

}
