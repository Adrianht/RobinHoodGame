package com.robinhood.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.view.View;
import com.robinhood.game.assetManagers.AudioManager;

public class RobinHood extends ApplicationAdapter {

    private View view;

    @Override
    public void create () {
        Controller controller = new Controller(this);
        controller.navigateTo("MENU");
        AudioManager.getInstance();
    }

    @Override
    public void render () {
        view.render();
    }

    @Override
    public void dispose () {
        view.dispose();
    }

    public void setView(View view) {
        this.view = view;
    }
}
