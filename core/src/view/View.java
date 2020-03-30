package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class View extends Stage {

    protected Stage stage;

    public void render() {
        Gdx.gl.glClearColor(130/255f, 230/255f, 250/255f, 255/255f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void dispose () {
        stage.dispose();
    }

}