package com.robinhood.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;

import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
import com.robinhood.game.view.interfaceObjects.DragIndicator;

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
    private TextButton
            upgrade2Button,
            upgrade3Button,
            upgrade4Button;

    private final DragIndicator dragIndicator;

    public GameView(final Controller controller, Model model) {
        super(controller, model);

        TextButton leftButton = new TextButton("Left", buttonSkin);
        TextButton rightButton = new TextButton("Right", buttonSkin);
        upgrade2Button = new TextButton("Upgrade 2", buttonSkin);
        upgrade3Button = new TextButton("Upgrade 3", buttonSkin);
        upgrade4Button = new TextButton("Upgrade 4", buttonSkin);
        gameInfo = new Label("", textSkin);

        gameInfo.setFontScale(2.5f);

        // FIXME-charlotte: table needs revised positioning/padding
        // remove superclass background
        table.setBackground(new BaseDrawable());
        table.row().pad(20f, 0, 700f, 0);
        table.add(gameInfo)
                .fillX().uniform().width(300f).height(100f);
        gameInfo.setAlignment(Align.left);
        table.row().pad(0, 0, 0, 0);
        table.bottom();
        table.padBottom(100f);
        table.add(leftButton)
                .left().width(300f).height(100f);
        table.add(upgrade2Button)
                .padLeft(50f).width(150f).height(100f);
        table.add(upgrade3Button)
                .width(150f).height(100f);
        table.add(upgrade4Button)
                .width(150f).height(100f);
        table.add(rightButton)
                .right().uniform().width(300f).height(100f);

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

        dragIndicator = new DragIndicator();
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

        int[] hitPoints = model.getHitPointValues();
        int myEnergyPoints = model.getMyEnergyPoints();

        String gameInfoString = "Your Energy Points: "
                + myEnergyPoints + "\n";
        for (int i = 0; i < hitPoints.length; i++) {
            gameInfoString += "HitPoints P" + i + ": " + hitPoints[i] + "\n";
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

}
