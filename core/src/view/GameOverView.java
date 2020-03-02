package view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.Controller;
import model.Model;

public class GameOverView extends View {

    public GameOverView(Model model, Controller controller) {
        super.setData(model.getData("state1"));
        super.setController(controller);

        //TODO: render data from model for the given view
        super.batch = new SpriteBatch();
        super.img = new Texture("badlogic.jpg");
    }
}
