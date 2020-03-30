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
                sprite = new Sprite(new Texture("settings.png"));
                setPosition(530, 400);
                sprite.setSize(90,70);
                break;
            case "play":
                sprite = new Sprite(new Texture("play.png"));
                setPosition(345, 180);
                break;
            case "menu":
                sprite = new Sprite(new Texture("menu.png"));
                setPosition(410, 50);
                break;
            case "exit":
                sprite = new Sprite(new Texture("exit.png"));
                setPosition(410, 50);
                break;
            case "left":
                sprite = new Sprite(new Texture("left.png"));
                setPosition(50, 0);
                sprite.setSize(100,100);
                break;
            case "right":
                sprite = new Sprite(new Texture("right.png"));
                setPosition(500, 0);
                sprite.setSize(100,100);
                break;
            case "robinhoodpic":
                sprite = new Sprite(new Texture("robinhood.png"));
                setPosition(0, 0);
                sprite.setSize(370, 480);
                break;
            case "backbutton":
                sprite = new Sprite(new Texture("back.png"));
                setPosition(460, 50);
                sprite.setSize(100,100);
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
