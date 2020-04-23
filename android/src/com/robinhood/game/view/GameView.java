package com.robinhood.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import com.robinhood.game.assetManagers.AudioManager;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Entity;
import com.robinhood.game.model.Model;
import com.robinhood.game.view.interfaceObjects.*;

/**
 * Subclass in Template method pattern creating the UI when in-game.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class GameView extends View {

    private Label gameInfo;
    private final ImageButton
            upgrade2Button,
            upgrade3Button,
            upgrade4Button;

    private boolean dragStart;

    private final HealthBar[] healthBars;

    public GameView(final Controller controller, Model model) {
        super(controller, model);
        AudioManager.getInstance().initSound();
        assetManager.loadImageButtonTextures();
        assetManager.loadInterfaceObjectsTextures();
        assetManager.finishLoading();

        ImageButton leftButton = createImgButton("left");
        ImageButton rightButton = createImgButton("right");
        upgrade2Button = createImgButton("Level2");
        upgrade3Button = createImgButton("Level3");
        upgrade4Button = createImgButton("Level4");
        gameInfo = new Label("", textSkin);
        gameInfo.setFontScale(2f);

        Texture backgroundTexture =
                assetManager.get(assetManager.gameBackgroundString);
        table.setBackground(new TextureRegionDrawable(backgroundTexture));
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

        int[] hitPointValues = model.getHitPointValues();
        healthBars = new HealthBar[hitPointValues.length];
        int space = 1200 / (hitPointValues.length - 1);
        for (int i = 0; i < hitPointValues.length; i++) {
            healthBars[i] = new HealthBar(space * i + 48);
            stage.addActor(healthBars[i]);
        }

        Texture playerTexture = assetManager.get(assetManager.archer);
        Entity[] playerEntities = model.getPlayerEntities();
        for (int i = 0; i < playerEntities.length; i++) {
            Archer archer = new Archer(
                    playerEntities[i],
                    playerTexture);
            stage.addActor(archer);
        }

        stage.addActor(new Arrow(model, assetManager));

        Texture dragIndicatorTexture =
                assetManager.get(assetManager.dragIndicator);
        final DragIndicator dragIndicator =
                new DragIndicator(dragIndicatorTexture);
        stage.addActor(dragIndicator);
        stage.addListener(new DragListener() {
            @Override
            public void drag(
                    InputEvent event,
                    float clickX,
                    float clickY,
                    int pointer) {
                if(!dragStart){
                    dragStart = true;
                    AudioManager.getInstance().playSound("draw");
                }
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
                dragStart = false;
                AudioManager.getInstance().playSound("shoot");
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
        /*
        new Box2DDebugRenderer(
                    true,
                    true,
                    true,
                    true,
                    true,
                    true).render(model.getWorld(), new OrthographicCamera(
            32,
            24).combined);

         */
    }

    private void updateGameInfo() {
        if(model.getGameWinner() != null) {
            controller.navigateTo("GAMEOVER");
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

    private ImageButton createImgButton(String name) {
        ImageButton imgButton = new ImageButton(buttonSkin, name);
        Texture texture =
                assetManager.get(name + ".png");
        imgButton.getStyle().imageUp = new TextureRegionDrawable(texture);
        imgButton.addListener(generateActionListener(name));
        return imgButton;
    }

    private ClickListener generateActionListener(final String action) {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                controller.actionToFirebase(action);
            }
        };
    }
}
