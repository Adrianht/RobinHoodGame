package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
<<<<<<< HEAD:android/src/com/robinhood/game/view/View.java
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
=======
import com.badlogic.gdx.scenes.scene2d.Stage;

>>>>>>> dd046e3c51ad34b401b66fd63028aa8c27f0268c:core/src/view/View.java

public abstract class View extends Stage {

    protected Stage stage;

    public void render() {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void dispose () {
        stage.dispose();
    }

}
