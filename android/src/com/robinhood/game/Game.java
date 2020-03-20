package com.robinhood.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.firebase.database.FirebaseDatabase;
import com.robinhood.game.views.GameView;


public class Game extends ApplicationAdapter {

    public static final int WIDTH = 1980;
    public static final int HEIGHT = 1080;

    public static final String TITLE = "RobinYoHood";

    private SpriteBatch batch;

    @Override
    public void create () {
        batch = new SpriteBatch();
        Gdx.gl.glClearColor(1, 0, 0, 1);
    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}