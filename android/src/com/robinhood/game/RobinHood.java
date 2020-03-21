package com.robinhood.game;

import com.badlogic.gdx.ApplicationAdapter;

import com.robinhood.game.controller.*;
import com.robinhood.game.view.*;

public class RobinHood extends ApplicationAdapter {

    private View view;

    @Override
    public void create () {
        new Controller(this);
    }

    @Override
    public void render () {
        //view.render();
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
