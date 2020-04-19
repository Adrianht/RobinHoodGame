package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import com.robinhood.game.controller.Controller;
import com.robinhood.game.model.Model;
import com.robinhood.game.view.interfaceObjects.CustomDragListener;
import com.robinhood.game.view.interfaceObjects.DragIndicator;

/**
 * Subclass in Template method pattern creating the UI when in-game.
 *
 * @author group 11
 * @version 1.0
 * @since 2020-04-25
 */
public class GameView extends View {

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
    private final World world;

    private final Skin skinInfo = new Skin(Gdx.files.internal(
            "skin/shade/uiskin.json"));
    private Skin skinButton = new Skin(Gdx.files.internal(
            "skin/dark-hdpi/Holo-dark-hdpi.json"));
    private final Label gameInfo = new Label("", skinInfo);;
    private final TextButton buyLevel2 =
            new TextButton("Upgrade 2", skinButton);
    private final TextButton buyLevel3 =
            new TextButton("Upgrade 3", skinButton);
    private final TextButton buyLevel4 =
            new TextButton("Upgrade 4", skinButton);

    public GameView(Controller cont, Model model) {
        super(cont);
        this.world = model.getWorld();

        // Create and position text fields and buttons of current UI
        Skin skinButton = new Skin(Gdx.files.internal(
                "skin/dark-hdpi/Holo-dark-hdpi.json"));
        TextButton leftButton = new TextButton("Left", skinButton);
        TextButton rightButton = new TextButton("Right", skinButton);

        // FIXME: table needs revised positioning/padding
        table.setBackground(new BaseDrawable()); // remove superclass background
        table.row().pad(20f, 0, 700f, 0);
        table.add(gameInfo).fillX().uniform().width(300f).height(100f);
        table.row().pad(0, 0, 0, 0);
        gameInfo.setAlignment(Align.left);
        table.bottom();
        table.padBottom(100f);
        table.add(leftButton).left().width(300f).height(100f);
        table.add(buyLevel2).padLeft(50f).width(150f).height(100f);
        table.add(buyLevel3).width(150f).height(100f);
        table.add(buyLevel4).width(150f).height(100f);
        table.add(rightButton).right().uniform().width(300f).height(100f);

        buyLevel2.addListener(generateBuyListener("Level2"));
        buyLevel3.addListener(generateBuyListener("Level3"));
        buyLevel4.addListener(generateBuyListener("Level4"));
        leftButton.addListener(generateMoveListener(true));
        rightButton.addListener(generateMoveListener(false));

        DragIndicator dragIndicator = new DragIndicator();
        stage.addActor(dragIndicator);
        stage.addListener(
                new CustomDragListener(dragIndicator, getController()));
    }

    @Override
    public void render() {
        super.render();
        updateGameInfo();
        debugRenderer.render(world, cam.combined);
    }

    private void updateGameInfo() {
        int[] hitPoints = getController().getHP();
        int myEnergyPoints = getController().getMyEnergyPoints();

        String gameInfoString = "Your Energy Points: "
                + myEnergyPoints + "\n";
        int survivorCount = 0;
        for (int i = 0; i < hitPoints.length; i++) {
            if(hitPoints[i] >= 0) {
                survivorCount++;
            }
            gameInfoString += "HitPoints P" + i + ": " + hitPoints[i] + "\n";
        }
        if (survivorCount < 2) {
            getController().handleGameOver();
        }

        gameInfo.setText(gameInfoString);
        buyLevel2.setVisible(myEnergyPoints >= 20);
        buyLevel3.setVisible(myEnergyPoints >= 50);
        buyLevel4.setVisible(myEnergyPoints >= 70);
    }

    private ClickListener generateBuyListener(final String type) {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                getController().buyArrow(type);
            }
        };
    }

    private ClickListener generateMoveListener(final Boolean way) {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float clickX, float clickY) {
                getController().move(way);
            }
        };
    }
}
