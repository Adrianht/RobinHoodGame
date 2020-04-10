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
                sprite.setSize(90, 70);
                setBounds(530, 400, sprite.getWidth(), sprite.getHeight());
                break;
            case "play":
                sprite = new Sprite(new Texture("play.png"));
                setBounds(345, 180, sprite.getWidth(), sprite.getHeight());
                break;
            case "menu":
                sprite = new Sprite(new Texture("menu.png"));
                setBounds(410, 50, sprite.getWidth(), sprite.getHeight());
                break;
            case "exit":
                sprite = new Sprite(new Texture("exit.png"));
                sprite.setSize(180, 100);
                setBounds(410, 50, sprite.getWidth(), sprite.getHeight());
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
                sprite.setSize(370, 480);
                setBounds(0,0,sprite.getWidth(),sprite.getHeight());
                break;
            case "backbutton":
                sprite = new Sprite(new Texture("back.png"));
                sprite.setSize(100,100);
                setBounds(460, 50, sprite.getWidth(), sprite.getHeight());
                break;
            case "shoot":
                sprite = new Sprite(new Texture("shoot_arrow_test_button.png"));
                sprite.setSize(30,30);
                setBounds(210, 25, sprite.getWidth(), sprite.getHeight());
                break;
            case "buyLevel2":
                sprite = new Sprite(new Texture("buyLevel2.png"));
                sprite.setSize(30,30);
                setBounds(250, 25, sprite.getWidth(), sprite.getHeight());
                break;
            case "buyLevel3":
                sprite = new Sprite(new Texture("buyLevel3.png"));
                sprite.setSize(30,30);
                setBounds(290, 25, sprite.getWidth(), sprite.getHeight());
                break;
            case "buyLevel4":
                sprite = new Sprite(new Texture("buyLevel4.png"));
                sprite.setSize(30,30);
                setBounds(330, 25, sprite.getWidth(), sprite.getHeight());
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
