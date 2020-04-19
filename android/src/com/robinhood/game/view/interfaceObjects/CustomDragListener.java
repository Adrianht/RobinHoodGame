package com.robinhood.game.view.interfaceObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.robinhood.game.controller.Controller;

public class CustomDragListener extends DragListener {

    private final DragIndicator dragIndicator;
    private final Controller controller;

    public CustomDragListener(
            DragIndicator dragIndicator,
            Controller controller
    ) {
        this.dragIndicator = dragIndicator;
        this.controller = controller;
    }

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
        controller.actionToFirebase(new Vector2(
                clickX-getDragStartX(),
                clickY-getDragStartY()
        ).toString());
        dragIndicator.sprite.setSize(0,0);
    }
}
