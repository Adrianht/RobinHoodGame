package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.List;

import controller.Controller;
import model.Model;
import view.interfaceObjects.Button;

public class GameOverView extends View {

    private final Controller controller;
    private SpriteBatch batch;
    private BitmapFont font;

    public GameOverView(Controller controller, Model model) {
        this.controller = controller;

        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        Button menuButton = new Button("menu");
        super.stage.addActor(menuButton);

        super.stage.addListener(gameOverViewListener);
    }

    // FIXME: Identical implementation to render() in superclass
    //  see if that can be avoided
    @Override
    public void render() {
        float[] values = hextoRGB("#5f8db0");
        Gdx.gl.glClearColor(values[0], values[1], values[2], 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        drawText();
    }

    private void drawText(){
        batch = new SpriteBatch();
        font = new BitmapFont();

        List<Integer> hpList = controller.getHP();
        int playerNrLoser = 0;
        int playerNrWinner = 0;

        for (int i = 0; i < hpList.size(); i++ ){
            if(hpList.get(i) > 0) {
                playerNrWinner = i;
            } else {
                playerNrLoser = i;
            }
        }

        String text = ("GAME OVER..." +
                "\ṅPlayer number " + playerNrWinner + " won the game." +
                "\nPlayer number " + playerNrLoser + " lost");

        batch.begin();
        font.draw(batch, text, 250, 250);
        batch.end();
    }

    private ClickListener gameOverViewListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float clickX, float clickY) {

            if (clickX > 100) {
                System.out.println("TO MENU!");
                controller.navigateTo("MENU");
            }

        }
    };
}