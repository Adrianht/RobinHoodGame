package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;

public class LoadingView extends View {

    private Skin skinButton = new Skin(Gdx.files.internal(
            "skin/dark-hdpi/Holo-dark-hdpi.json"));

    private final TextButton playButton =
            new TextButton("Play!", skinButton);
    private final TextButton menuButton =
            new TextButton("To menu", skinButton);

    public LoadingView(Controller cont, Model model) {
        super(cont);

        playButton.addListener(generateNavigateListener("GAME"));
        menuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                getController().cancelFindPlayer();
                getController().navigateTo("MENU");
            }
        });

        table.row().pad(400, 0, 10, 0);
        table.add(playButton).fillX().uniform().width(300f).height(100f);
        table.row().pad(10, 0, 10, 0);
        table.add(menuButton).fillX().uniform().width(300f).height(100f);
        this.getController().findPlayer();

    }


    @Override
    public void render() {
        menuButton.setVisible(!getController().isGameInitialized());
        playButton.setVisible(getController().isGameInitialized());
        super.render();
    }

}
