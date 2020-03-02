package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.Controller;

public abstract class View {
    SpriteBatch batch;
    Texture img;

    private String data;
    private Controller controller;

    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    public void dispose () {
        batch.dispose();
        img.dispose();
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void screenListener() {
        controller.userInput("USER INPUT");
    }
}
