package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.math.Vector2;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Components;
import com.robinhood.game.model.Model;
import com.robinhood.game.view.interfaceObjects.DragIndicator;
import com.robinhood.game.view.interfaceObjects.Button;

import java.util.List;


public class GameView extends View {

    //private final Controller controller;
    private final DragIndicator dragIndicator;

    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font = new BitmapFont();

    private final Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
    private final OrthographicCamera cam = new OrthographicCamera(32, 24);

    private World world;

    private boolean buyLv2 = true;
    private boolean buyLv3 = true;
    private boolean buyLv4 = true;


    public GameView(Controller cont, Model model) {
        super(cont);

        this.world = model.world;

        Skin skinButton = new Skin(Gdx.files.internal(
                "skin/dark-hdpi/Holo-dark-hdpi.json"));

        TextButton buyLevel2 = new TextButton("Upgrade 2", skinButton);
        TextButton buyLevel3 = new TextButton("Upgrade 3", skinButton);
        TextButton buyLevel4 = new TextButton("Upgrade 4", skinButton);

        buyLevel2.setName("buyLevel2");
        buyLevel3.setName("buyLevel3");
        buyLevel4.setName("buyLevel4");

        TextButton menu = new TextButton("Menu", skinButton);

        TextButton left = new TextButton("Left", skinButton);
        TextButton right = new TextButton("Right", skinButton);

        table.bottom();
        table.padBottom(100f);
        table.add(left).left().width(300f).height(100f);
        table.add(buyLevel2).padLeft(50f).width(150f).height(100f);
        table.add(buyLevel3).width(150f).height(100f);
        table.add(buyLevel4).width(150f).height(100f);
        table.add(menu).uniform().width(200f).height(100f);
        table.add(right).right().uniform().width(300f).height(100f);

        buyLevel2.addListener(generateBuyArrowListener("Level2"));
        buyLevel3.addListener(generateBuyArrowListener("Level3"));
        buyLevel4.addListener(generateBuyArrowListener("Level4"));

        left.addListener(generateMoveListener(true));
        right.addListener(generateMoveListener(false));

        menu.addListener(generateNavigationListener("MENU"));

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
                getController().drawBow(new Vector2(
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

        //TODO: change to int
        List<Integer> hitPoints = getController().getHP();
        List<Integer> energyPoints = getController().getEnergy();

        String hpText = "";
        String energyText = "EnergyPoints" + ": " + energyPoints + "\n";
        for (int i = 0; i < hitPoints.size(); i++) {
            //FIXME: change check, now game over with only one player at hp=0
            if(hitPoints.get(i) <= 0) {
                getController().handleGameOver();
            }
            hpText += "HitPoints P" + i + ": " + hitPoints.get(i) + "\n";

        }
        //TODO: change to only "energypoints" When changed to int
        checkBuys(energyPoints.get(0));

        batch.begin();
        font.draw(batch, (hpText + energyText), 750, 830);
        batch.end();
    }

    public void setVisible(Actor actor, Boolean bool){
        actor.setVisible(bool);
    }

    public void checkBuys(int energyPoints){
        if (energyPoints >= 20) {
            if(!this.buyLv2){
                setVisible(table.findActor("buyLevel2"), true);
            }
            this.buyLv2 = true;
        } else {
            if(this.buyLv2){
                setVisible(table.findActor("buyLevel2"), false);
            }
            this.buyLv2 = false;
        }

        if(energyPoints >= 50){
            if(!this.buyLv3){
                setVisible(table.findActor("buyLevel3"), true);
            }
            this.buyLv3 = true;
        } else {
            if(this.buyLv3){
                setVisible(table.findActor("buyLevel3"), false);
            }
            this.buyLv3 = false;
        }

        if(energyPoints >= 70){
            if(!this.buyLv4){
                setVisible(table.findActor("buyLevel4"), true);
            }
            this.buyLv4 = true;
        } else {
            if(this.buyLv4){
                setVisible(table.findActor("buyLevel4"), false);
            }
            this.buyLv4 = false;
        }
    }

    protected ClickListener generateBuyArrowListener(final String type) {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("You want to buy a level " + type + " weapon");
                getController().buyArrow(type);
            }
        };
    }

    protected ClickListener generateMoveListener(final Boolean way) {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("Move " + way);
                getController().move(way);
            }
        };
    }

    protected ClickListener generateNavigationListener(final String destination) {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                System.out.println("navigate " + destination);
                getController().navigateTo(destination);
            }
        };
    }
}
