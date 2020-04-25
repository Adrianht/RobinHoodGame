package com.robinhood.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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

    private final ImageButton
            upgrade2Button,
            upgrade3Button,
            upgrade4Button,
            leftButton,
            rightButton;
    private EnergyBar energyBar;

    public GameView(final Controller controller, Model model) {
        super(controller, model);
        AudioManager.getInstance().initSound();

        assetManager.loadImageButtonTextures();
        assetManager.loadInterfaceObjectsTextures();
        assetManager.loadTextureAtlas();
        assetManager.finishLoading();

        leftButton = createImgButton("left");
        rightButton = createImgButton("right");
        upgrade2Button = createImgButton("Level2");
        upgrade3Button = createImgButton("Level3");
        upgrade4Button = createImgButton("Level4");

        Texture backgroundTexture =
                assetManager.get(assetManager.gameBackground);
        table.setBackground(new TextureRegionDrawable(backgroundTexture));
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

        initiateInterfaceObjects();
    }

    @Override
    public void render() {
        super.render();
        updateGameInfo();
    }

    private void updateGameInfo() {
        if(model.getGameWinner() != null) {
            controller.navigateTo("GAMEOVER");
        } else {
            int myEnergyPoints = model.getMyEnergyPoints();
            energyBar.setVisible(myEnergyPoints >= 10);
            if(energyBar.isVisible()) {
                energyBar.updateSprite(myEnergyPoints);
            }

            upgrade2Button.setVisible(myEnergyPoints >= 20);
            upgrade3Button.setVisible(myEnergyPoints >= 40);
            upgrade4Button.setVisible(myEnergyPoints >= 60);
        }
    }

    private void initiateInterfaceObjects() {
        Entity[] playerEntities = model.getPlayerEntities();
        int space = 1200 / (playerEntities.length - 1);
        Texture archerTexture = assetManager.get(assetManager.archer);
        TextureAtlas healthBarAtlas =
                assetManager.get(assetManager.healthBarAtlas);
        for (int i = 0; i < playerEntities.length; i++) {
            stage.addActor(new HealthBar(
                    playerEntities[i],
                    space * i + 48,
                    healthBarAtlas));
            stage.addActor(new Archer(
                    playerEntities[i],
                    archerTexture
            ));
        }

        TextureAtlas energyBarAtlas =
                assetManager.get(assetManager.energyBarAtlas);
        energyBar = new EnergyBar(energyBarAtlas);
        stage.addActor(energyBar);

        Texture arrowTexture = assetManager.get(assetManager.arrow);
        stage.addActor(new Arrow(model, arrowTexture));

        Texture dragIndicatorTexture =
                assetManager.get(assetManager.dragIndicator);
        new DragIndicator(controller, stage, dragIndicatorTexture);
    }

    private ImageButton createImgButton(String name) {
        ImageButton imgButton = new ImageButton(buttonSkin, name);
        Texture texture =
                assetManager.get("img/" + name + ".png");
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
