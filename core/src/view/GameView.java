package view;

import controller.Controller;
import model.Model;
import model.*;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.List;

import view.interfaceObjects.*;

public class GameView extends View {

    private final Controller controller;

    private SpriteBatch batch;
    private BitmapFont font;


    public GameView(Controller cont, Model model) {

        this.controller = cont;


        // Stage: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Stage.html
        // set the stage of the View superclass - same in all subclasses
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Button menuButton = new Button("menu");
        Button leftButton = new Button("left");
        Button rightButton = new Button("right");
        Button buyButton = new Button("buy");
        Button shootButton = new Button("shoot");

        // ClickListener: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/utils/ClickListener.html
        // ClickListener triggered by user clicks on Button/Actor to call appropriate actions
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
        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("You want to buy a new weapon!");
                controller.buyArrow("weaponLevel2");
            }
        });
        shootButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("SHOOT");
                controller.drawBow(new Vector2());
            }
        });


        /*
        TODO:
        Clicks in this view should trigger one of the following methods
        in the controller, based on the position and duration of the click:
            - controller.navigateTo("MENU") (ONLY IF GAMEOVER)
            - controller.move(Boolean left)
            - controller.buyArrow(String type)
            - controller.drawBow(Vector2 vector2)
        Additional:
            - if move() or buyArrow is called, they should be followed by
                updateView()
        */

        // Actor: https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/scenes/scene2d/Actor.html
        // adds all the elements to this interface

        stage.addActor(menuButton);
        stage.addActor(leftButton);
        stage.addActor(rightButton);
        stage.addActor(buyButton);
        stage.addActor(shootButton);



        // adds archers, arrows and arena
        List<Actor> actors = model.getActors();
        for (Actor actor: actors) {
            stage.addActor(actor);
        }

        /*
         TODO:
         Use/create methods in model to extract current data
         from objects related to this view.
         This includes getting data from stage, players, etc. and
         checking if the game is over.
         */

    }

    //This is a quickfix and should be avoided....
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

        List<Integer> hitpoints = new ArrayList<>();
        hitpoints = controller.getHP();
        List<Integer> energyPoints = new ArrayList<>();
        energyPoints = controller.getEnergy();

        int hpP1 = hitpoints.get(0);
        int hpP2 = hitpoints.get(1);
        int energyP1 = energyPoints.get(0); //Trenger kun å oppdateres og hentes ut etter draw() - kan se på dette senere
        int energyP2 = energyPoints.get(1);

        if(controller.gameIsOver() != -1) {
            controller.navigateTo("GAMEOVER");
            System.out.println(controller.gameIsOver());
        }


        String text = "HitPoints P1: " + hpP1 + "\nHitPoints P2: " + hpP2 + "\nEnergyPoints P1: " + energyP1 + "\nEnergyPoints P2: " + energyP2;


        batch.begin();
        font.draw(batch, text, 250, 130);
        batch.end();

    }

}
