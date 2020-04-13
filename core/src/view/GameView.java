package view;

import controller.Controller;
import model.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import view.interfaceObjects.*;

public class GameView extends View {

    private final Controller controller;
    private final DragIndicator dragIndicator;

    private SpriteBatch batch;
    private BitmapFont font;


    public GameView(Controller cont, Model model) {

        this.controller = cont;

        // Stage: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Stage.html
        // set the stage of the View superclass
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Button: view/interfaceObjects/Button.java
        // Initiate clickable objects within the interface
        Button menuButton = new Button("menu");
        Button leftButton = new Button("left");
        Button rightButton = new Button("right");
        Button buyLevel2 = new Button("buyLevel2");
        Button buyLevel3 = new Button("buyLevel3");
        Button buyLevel4 = new Button("buyLevel4");

        // ClickListener: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/utils/ClickListener.html
        // Add ClickListeners to call appropriate actions at clickable objects
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("TO MENU!");
                controller.navigateTo("MENU");
            }
        });
        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("LEFT!");
                controller.move(true);
            }
        });
        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("RIGHT!");
                controller.move(false);
            }
        });
        buyLevel2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("You want to buy a Level 2 weapon!");
                controller.buyArrow("Level2");
            }
        });
        buyLevel3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("You want to buy a Level 3 weapon!");
                controller.buyArrow("Level3");
            }
        });
        buyLevel4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("You want to buy a Level 4 weapon!");
                controller.buyArrow("Level4");
            }
        });

        // Actor: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Actor.html
        // Add all the clickable objects to this interface
        stage.addActor(menuButton);
        stage.addActor(leftButton);
        stage.addActor(rightButton);
        stage.addActor(buyLevel2);
        stage.addActor(buyLevel3);
        stage.addActor(buyLevel4);

        // adds archers, arrows and arena from model
        List<Actor> actors = model.getActors();
        for (Actor actor: actors) {
            stage.addActor(actor);
        }

        // DragListener: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/utils/DragListener.html#drag-com.badlogic.gdx.scenes.scene2d.InputEvent-float-float-int-
        // Add listener to detect drag on screen and trigger controller.drawBow()-method
        dragIndicator = new DragIndicator();
        stage.addActor(dragIndicator);
        stage.addListener(new DragListener() {
            @Override
            public void drag(InputEvent event, float clickX, float clickY, int pointer) {
                float power = (float)Math.sqrt(Math.pow(clickX - getDragStartX(), 2) +
                        Math.pow(clickY - getDragStartY(), 2));
                dragIndicator.sprite.setSize(power, 40);

                double angleRad = Math.atan(Math.abs(clickY-getDragStartY()) / Math.abs(clickX-getDragStartX()));
                float angleDeg = (float)Math.toDegrees(angleRad);
                if(clickX < getDragStartX()) {
                    if(clickY < getDragStartY()) {
                        dragIndicator.sprite.setRotation(angleDeg);
                    } else {
                        dragIndicator.sprite.setRotation(360-angleDeg);
                    }
                } else {
                    if(clickY < getDragStartY()) {
                        dragIndicator.sprite.setRotation(180-angleDeg);
                    } else {
                        dragIndicator.sprite.setRotation(180+angleDeg);
                    }
                }
            }
            @Override
            public void dragStop(InputEvent event, float clickX, float clickY, int pointer) {
                controller.drawBow(new Vector2(clickX-getDragStartX(), clickY-getDragStartY()));
            }
        });

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

        List<Integer> hitPoints = controller.getHP();
        List<Integer> energyPoints = controller.getEnergy();

        // TODO: may only fetch and renew values when changed
        int hpP1 = hitPoints.get(0);
        int hpP2 = hitPoints.get(1);
        int energyP1 = energyPoints.get(0);
        int energyP2 = energyPoints.get(1);

        String text = ("HitPoints P1: " + hpP1 +
                "\nHitPoints P2: " + hpP2 +
                "\nEnergyPoints P1: " + energyP1 +
                "\nEnergyPoints P2: " + energyP2);

        batch.begin();
        font.draw(batch, text, 250, 130);
        batch.end();
    }
}
