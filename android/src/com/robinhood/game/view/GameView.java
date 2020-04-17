package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.math.Vector2;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Components;
import com.robinhood.game.model.Model;
import com.robinhood.game.view.interfaceObjects.DragIndicator;
import com.robinhood.game.view.interfaceObjects.Button;

import java.util.List;


public class GameView extends View {

    private final Controller controller;
    private final DragIndicator dragIndicator;

    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font = new BitmapFont();

    private final Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
    private final OrthographicCamera cam = new OrthographicCamera(32, 24);

    private World world;

    public GameView(Controller cont, Model model) {

        this.controller = cont;
        this.world = model.world;

        // Set the stage of the View superclass
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Initiate clickable objects within the interface
        Button menuButton = new Button("menu");
        Button leftButton = new Button("left");
        Button rightButton = new Button("right");
        Button buyLevel2 = new Button("buyLevel2");
        Button buyLevel3 = new Button("buyLevel3");
        Button buyLevel4 = new Button("buyLevel4");

        // Add ClickListeners to call appropriate actions at clickable objects
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.navigateTo("MENU");
            }
        });
        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.move(true);
            }
        });
        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
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

        // Add all the clickable objects to this interface
        stage.addActor(menuButton);
        stage.addActor(leftButton);
        stage.addActor(rightButton);
        System.out.println(controller.getPlayerNr());

        if (controller.getEnergy().get(controller.getPlayerNr()) >= 20) {
            System.out.println("yeehaw");
        } else{
            System.out.println("nah");
        }
        stage.addActor(buyLevel2);
        stage.addActor(buyLevel3);
        stage.addActor(buyLevel4);

        // Add listener to detect drag on screen and trigger controller.drawBow()-method
        dragIndicator = new DragIndicator();
        stage.addActor(dragIndicator);
        stage.addListener(new DragListener() {
            @Override
            public void drag(InputEvent event, float clickX, float clickY, int pointer) {
                float power = (float)Math.sqrt(Math.pow(clickX -
                        getDragStartX(), 2) + Math.pow(clickY - getDragStartY(), 2));
                dragIndicator.sprite.setSize(power, 40);

                double angleRad = Math.atan(Math.abs(clickY-getDragStartY())
                        / Math.abs(clickX-getDragStartX()));
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
            public void dragStop(InputEvent event,
                                 float clickX,
                                 float clickY,
                                 int pointer) {
                controller.drawBow(new Vector2(
                        clickX-getDragStartX(),
                        clickY-getDragStartY())
                );
                dragIndicator.sprite.setSize(0,0);
            }
        });
    }

    @Override
    public void render() {
        float[] values = hextoRGB("#5f8db0");
        Gdx.gl.glClearColor(values[0], values[1], values[2], 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        handlePlayerInfo();
        debugRenderer.render(world, cam.combined);
    }

    private void handlePlayerInfo(){
        List<Integer> hitPoints = controller.getHP();
        List<Integer> energyPoints = controller.getEnergy();

        String hpText = "";
        String energyText = "";
        for (int i = 0; i < hitPoints.size(); i++) {
            //FIXME: change check, now game over with only one player at hp=0
            if(hitPoints.get(i) <= 0) {
                controller.handleGameOver();
            }
            hpText += "HitPoints P" + i + ": " + hitPoints.get(i) + "\n";
            energyText += "EnergyPoints P" + i + ": " + energyPoints.get(i) + "\n";
        }

        batch.begin();
        font.draw(batch, (hpText + energyText), 750, 830);
        batch.end();
    }
}
