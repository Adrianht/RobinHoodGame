package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import com.robinhood.game.assetManagers.AudioManager;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;

/**
 * Class with interface object showing a player arrows
 * expected direction and power.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class DragIndicator extends Actor {

    private Sprite sprite;
    private boolean dragStart;

    public DragIndicator(
            final Model model,
            final Controller controller,
            Stage stage,
            Texture dragIndicatorTexture) {

        sprite = new Sprite(dragIndicatorTexture);
        sprite.setSize(0, 0);
        sprite.setOrigin(0, 20);
        setPosition(900, 600);

        stage.addActor(this);
        stage.addListener(new DragListener() {
            @Override
            public void drag(
                    InputEvent event,
                    float clickX,
                    float clickY,
                    int pointer) {
                if(!dragStart && model.isMyTurn()){
                    dragStart = true;
                    AudioManager.getInstance().playSound("draw");
                }
                float power = (float)Math.sqrt(
                        Math.pow(clickX - getDragStartX(), 2)
                                + Math.pow(clickY - getDragStartY(), 2));
                sprite.setSize(power, 40);

                double angleRad =
                        Math.atan(Math.abs(clickY-getDragStartY())
                                / Math.abs(clickX-getDragStartX()));
                float angleDeg = (float)Math.toDegrees(angleRad);
                if(clickX < getDragStartX()) {
                    if(clickY < getDragStartY()) {
                        sprite.setRotation(angleDeg);
                    } else {
                        sprite.setRotation(360-angleDeg);
                    }
                } else {
                    if(clickY < getDragStartY()) {
                        sprite.setRotation(180-angleDeg);
                    } else {
                        sprite.setRotation(180+angleDeg);
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
                if(model.isMyTurn()) {
                    AudioManager.getInstance().playSound("shoot");
                }
                controller.actionToFirebase(new Vector2(
                        clickX-getDragStartX(),
                        clickY-getDragStartY()
                ).toString());
                sprite.setSize(0,0);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(this.getX(), this.getY());
        sprite.draw(batch);
    }
}
