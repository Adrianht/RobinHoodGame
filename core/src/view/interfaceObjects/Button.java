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
                setBounds(400, 200, sprite.getWidth(), sprite.getHeight());
                break;
            case "play":
                sprite = new Sprite(new Texture("play.png"));
                setBounds(50, 200, sprite.getWidth(), sprite.getHeight());
                break;
            case "menu":
                sprite = new Sprite(new Texture("menu.png"));
                setBounds(250, 50, sprite.getWidth(), sprite.getHeight());
                break;
            case "exit":
                sprite = new Sprite(new Texture("exit.png"));
                setBounds(700, 50, sprite.getWidth(), sprite.getHeight());
                break;
            case "left":
                sprite = new Sprite(new Texture("left.png"));
                sprite.setSize(100,100);
                setBounds(50, 0, sprite.getWidth(), sprite.getHeight());
                break;
            case "right":
                sprite = new Sprite(new Texture("right.png"));
                sprite.setSize(100,100);
                setBounds(500, 0, sprite.getWidth(), sprite.getHeight());
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
