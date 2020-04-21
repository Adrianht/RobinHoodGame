package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
import com.robinhood.game.view.interfaceObjects.DragIndicator;
import com.robinhood.game.view.interfaceObjects.HealthBar;

/**
 * Subclass in Template method pattern creating the UI when in-game.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class GameView extends View {

    private final Box2DDebugRenderer debugRenderer =
            new Box2DDebugRenderer(
            true,
            true,
            true,
            true,
            true,
            true);
    private final OrthographicCamera cam = new OrthographicCamera(
            32,
            24);

    private Label gameInfo;
    private ImageButton
            upgrade2Button,
            upgrade3Button,
            upgrade4Button;

    HealthBar[] healthBars;

    // TODO: Lars / Include texture atlas in asset manager please

    public GameView(final Controller controller, Model model) {
        super(controller, model);

        ImageButton leftButton = new ImageButton(textSkin);
        ImageButton rightButton = new ImageButton(textSkin);
        upgrade2Button = new ImageButton(buttonSkin);
        upgrade3Button = new ImageButton(buttonSkin);
        upgrade4Button = new ImageButton(buttonSkin);
        gameInfo = new Label("", textSkin);

        leftButton.getStyle().imageUp =
                createTexture("left.png");
        rightButton.getStyle().imageUp =
                createTexture("right.png");
        upgrade2Button.getStyle().imageUp =
                createTexture("buyLevel2.png");
        upgrade3Button.getStyle().imageUp =
                createTexture("buyLevel3.png");
        upgrade4Button.getStyle().imageUp =
                createTexture("buyLevel4.png");
        gameInfo.setFontScale(2f);

        table.setBackground(
                new BaseDrawable()); // remove superclass background
        table.row().pad(20f, 0, 700f, 0);
        table.add(gameInfo)
                .fillX().uniform().width(300f).height(100f);
        gameInfo.setAlignment(Align.left);
        table.row().pad(0, 0, 0, 0);
        table.bottom();
        table.padBottom(100f);
        table.add(leftButton)
                .left().padRight(300f).width(200f).height(150f);
        table.add(upgrade2Button)
                .padRight(50f).width(150f).height(150f);
        table.add(upgrade3Button)
                .padRight(50f).width(150f).height(150f);
        table.add(upgrade4Button)
                .width(150f).height(150f);
        table.add(rightButton)
                .right().padLeft(300f).width(200f).height(150f);

        upgrade2Button.addListener(
                generateActionListener("Level2"));
        upgrade3Button.addListener(
                generateActionListener("Level3"));
        upgrade4Button.addListener(
                generateActionListener("Level4"));
        leftButton.addListener(
                generateActionListener("left"));
        rightButton.addListener(
                generateActionListener("right"));

        int[] hitPointValues = model.getHitPointValues();
        healthBars = new HealthBar[hitPointValues.length];
        int space = 1200 / (hitPointValues.length - 1);
        for (int i = 0; i < hitPointValues.length; i++) {
            healthBars[i] = new HealthBar(space * i + 48);
            stage.addActor(healthBars[i]);
        }

        final DragIndicator dragIndicator = new DragIndicator();
        stage.addActor(dragIndicator);
        stage.addListener(new DragListener() {
            @Override
            public void drag(
                    InputEvent event,
                    float clickX,
                    float clickY,
                    int pointer) {
                float power = (float)Math.sqrt(
                        Math.pow(clickX - getDragStartX(), 2)
                                + Math.pow(clickY - getDragStartY(), 2));
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
            public void dragStop(
                    InputEvent event,
                    float clickX,
                    float clickY,
                    int pointer) {
                controller.actionToFirebase(new Vector2(
                        clickX-getDragStartX(),
                        clickY-getDragStartY()
                ).toString());
                dragIndicator.sprite.setSize(0,0);
            }
        });
    }

    @Override
    public void render() {
        super.render();
        updateGameInfo();
        debugRenderer.render(model.getWorld(), cam.combined);
    }

    private void updateGameInfo() {
        if(model.getGameWinner() != null) {
            controller.handleGameOver();
        }

        int myEnergyPoints = model.getMyEnergyPoints();
        String gameInfoString = "Your Energy Points: "
                + myEnergyPoints + "\n";

        int[] hitPointValues = model.getHitPointValues();
        for (int i = 0; i < healthBars.length; i++) {
            healthBars[i].updateSprite(hitPointValues[i]);
        }

        gameInfo.setText(gameInfoString);
        upgrade2Button.setVisible(myEnergyPoints >= 20);
        upgrade3Button.setVisible(myEnergyPoints >= 40);
        upgrade4Button.setVisible(myEnergyPoints >= 60);
    }

    private ClickListener generateActionListener(final String action) {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.actionToFirebase(action);
            }
        };
    }

    private TextureRegionDrawable createTexture(String internalPath) {
        return new TextureRegionDrawable(
                new TextureRegion(
                        new Texture(
                                Gdx.files.internal(internalPath))));
    }
}
