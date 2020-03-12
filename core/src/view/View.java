package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;

import controller.Controller;
import model.Model;

public abstract class View implements Screen {
    /*
    SpriteBatch batch;
    Texture img;

    protected Controller controller;
    protected Model world;
    protected Stage UI;

    public View (Controller controller, Model world){
        this.controller = controller;
        this.world = world;
        UI = new Stage(new ScreenViewport());
    }

    // Lifecycle methods
    @Override
    final public void show() {

        // Maps the controller
        InputMultiplexer input = new InputMultiplexer();
        input.addProcessor(UI);
        Gdx.input.setInputProcessor(input);

        // Screen specific
        init();
    }

    public void init(){}

    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(UI != null){
            UI.act(delta);
            UI.draw();
        }
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    public void dispose () {
        if(UI != null) UI.dispose();
        UI = null;
        batch.dispose();
        img.dispose();
    }

    public void screenListener() {
        controller.userInput("USER INPUT");
    }
    */
}
