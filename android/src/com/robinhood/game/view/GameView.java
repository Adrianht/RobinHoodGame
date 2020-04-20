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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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

    private final Model model;
    private final DragIndicator dragIndicator;

    private final Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer(
            true,
            true,
            true,
            true,
            true,
            true);
    private final OrthographicCamera cam = new OrthographicCamera(
            32,
            24);

    private final Skin skinInfo = new Skin(Gdx.files.internal(
            "skin/shade/uiskin.json"));
    private Skin skinButton = new Skin(Gdx.files.internal(
            "skin/dark-hdpi/Holo-dark-hdpi.json"));
    private Skin skinLeftButton = new Skin(Gdx.files.internal(
            "skin/shade/uiskin.json"));
    private Skin skinRightButton = new Skin(Gdx.files.internal(
            "skin/shade/uiskin.json"));
    private final Label gameInfo = new Label("", skinInfo);;
    private final TextButton buyLevel2 =
            new TextButton("Upgrade 2", skinButton);
    private final TextButton buyLevel3 =
            new TextButton("Upgrade 3", skinButton);
    private final TextButton buyLevel4 =
            new TextButton("Upgrade 4", skinButton);

    public GameView(Controller cont, Model model) {
        super(cont);
        this.model = model;

        // Create and position text fields and buttons of current UI
        Skin skinButton = new Skin(Gdx.files.internal(
                "skin/dark-hdpi/Holo-dark-hdpi.json"));
        ImageButton leftButton = new ImageButton(skinLeftButton);
        ImageButton rightButton = new ImageButton(skinRightButton);
        leftButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("skin/left.png"))));
        rightButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("skin/right.png"))));

        // FIXME: table needs revised positioning/padding
        table.setBackground(new BaseDrawable()); // remove superclass background
        table.row().pad(20f, 0, 700f, 0);
        table.add(gameInfo).fillX().uniform().width(300f).height(100f);
        table.row().pad(0, 0, 0, 0);
        gameInfo.setAlignment(Align.left);
        table.bottom();
        table.padBottom(110f);
        table.add(leftButton).left().width(300f).height(100f);
        table.add(buyLevel2).padLeft(50f).width(150f).height(100f);
        table.add(buyLevel3).width(150f).height(100f);
        table.add(buyLevel4).width(150f).height(100f);
        table.add(rightButton).right().uniform().width(300f).height(100f);

        buyLevel2.addListener(generateActionListener("Level2"));
        buyLevel3.addListener(generateActionListener("Level3"));
        buyLevel4.addListener(generateActionListener("Level4"));
        leftButton.addListener(generateActionListener("left"));
        rightButton.addListener(generateActionListener("right"));

        dragIndicator = new DragIndicator();
        stage.addActor(dragIndicator);
        stage.addListener(new DragListener(){
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
                getController().actionToFirebase(new Vector2(
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
            getController().handleGameOver();
        }

        int[] hitPoints = model.getHitPointValues();
        int myEnergyPoints = model.getMyEnergyPoints();

        String gameInfoString = "Your Energy Points: "
                + myEnergyPoints + "\n";
        for (int i = 0; i < hitPoints.length; i++) {
            gameInfoString += "HitPoints P" + i + ": " + hitPoints[i] + "\n";
        }

        gameInfo.setText(gameInfoString);
        buyLevel2.setVisible(myEnergyPoints >= 20);
        buyLevel3.setVisible(myEnergyPoints >= 40);
        buyLevel4.setVisible(myEnergyPoints >= 60);
    }

    private ClickListener generateActionListener(final String action) {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                getController().actionToFirebase(action);
            }
        };
    }

}
