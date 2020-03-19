package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;


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
    public void init(){}

    @Override
    final public void show() {

        // Maps the controller
        InputMultiplexer input = new InputMultiplexer();
        input.addProcessor(UI);
        Gdx.input.setInputProcessor(input);

        // Screen specific
        init();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(UI != null){
            //UI.act(delta);
            UI.draw();
        }
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        UI.getViewport().update(width, height);
    }

    public void dispose () {
        if(UI != null) UI.dispose();
        UI = null;
        batch.dispose();
        img.dispose();
    }
*/
}
