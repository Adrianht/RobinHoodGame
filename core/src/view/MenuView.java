package view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.Controller;
import model.Model;

public class MenuView extends View {

    public MenuView(Model model, Controller controller) {
        super.setData(model.getData("state2"));
        super.setController(controller);

        //TODO: render data from model for the given view
        super.batch = new SpriteBatch();
        super.img = new Texture("badlogic.jpg");
    }
}
