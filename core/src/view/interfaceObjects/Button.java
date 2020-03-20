package view.interfaceObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Button extends Actor {
    /*

    Example class demonstrating an interface object which
    don't hold game data. Objects containing game data should
    be placed in the model-package

    */

    private Sprite sprite;

    public Button(String type) {
        switch(type) {
            case "settings":
                sprite = new Sprite(new Texture("setting.png"));
                setPosition(400, 200);
                break;
            case "loading":
                sprite = new Sprite(new Texture("loading.png"));
                setPosition(50, 200);
                break;
            case "menu":
                sprite = new Sprite(new Texture("menu.png"));
                setPosition(100, 100);
                break;
            default:
                // code block
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(this.getX(), this.getY());
        sprite.draw(batch);
    }

}
