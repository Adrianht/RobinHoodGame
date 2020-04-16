package com.robinhood.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
import com.robinhood.game.view.MenuView;
import com.robinhood.game.view.View;

public class RobinHood extends ApplicationAdapter {

    private View view;

    @Override
    public void create () {
        Controller controller = new Controller(this);
        view = new MenuView(controller);
    }

    @Override
    public void render () {
        view.render();
    }

    @Override
    public void dispose () {
        view.dispose();
    }

    // TODO: add description
    public void setView(View view) {
        this.view = view;
    }

}

