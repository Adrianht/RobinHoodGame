package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class View extends Stage {

    protected Stage stage;

    public void render() {
        float[] values = hextoRGB("#5f8db0");
        Gdx.gl.glClearColor(values[0], values[1], values[2], 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    /* re-map RGB colors so they can be used in OpenGL */
    private float[] map(float[]rgb) {
        float[] result = new float[3];
        result[0] = rgb[0] / 255;
        result[1] = rgb[1] / 255;
        result[2] = rgb[2] / 255;
        return result;
    }
    //Can use hexadecimals for colors instead of the gl colors
    public float[] hextoRGB(String hex) {
        float[] rgbcolor = new float[3];
        rgbcolor[0] = Integer.valueOf(hex.substring(1,3 ),16);
        rgbcolor[1] = Integer.valueOf(hex.substring(3,5 ),16);
        rgbcolor[2] = Integer.valueOf(hex.substring(5,7 ),16);
        return map(rgbcolor);
    }

    @Override
    public void dispose () {
        stage.dispose();
    }

}