package com.robinhood.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
            upgrade4Button,
            leftButton,
            rightButton;

    private final Entity[] playerEntities;
    private HealthBar[] healthBars;

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
        gameInfo = new Label("", textSkin);

        Texture backgroundTexture =
                assetManager.get(assetManager.gameBackground);
        table.setBackground(new TextureRegionDrawable(backgroundTexture));
        table.row().pad(0, 0, 600f, 0);
        table.add(gameInfo)
                .fillX().uniform().height(100f);
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

        playerEntities = model.getPlayerEntities();
        initiateHealthBars();
        initiateArchers();
        stage.addActor(new Arrow(model, assetManager));
        new DragIndicator(controller, stage, assetManager);
    }

    @Override
    public void render() {
        super.render();
        updateGameInfo();
    }

    private void updateGameInfo() {
        if(model.getGameWinner() != null) {
            controller.navigateTo("GAMEOVER");
        }

        for (int i = 0; i < healthBars.length; i++) {
            int hp = playerEntities[i]
                    .components.playerInfo.hitPoints;
            healthBars[i].updateSprite(hp);
        }

        int myEnergyPoints = model.getMyEnergyPoints();
        String gameInfoString = "Your Energy Points: "
                + myEnergyPoints;
        gameInfo.setText(gameInfoString);
        gameInfo.setFontScale(3f);
        upgrade2Button.setVisible(myEnergyPoints >= 20);
        upgrade3Button.setVisible(myEnergyPoints >= 40);
        upgrade4Button.setVisible(myEnergyPoints >= 60);
    }

    private void initiateHealthBars() {
        healthBars = new HealthBar[playerEntities.length];
        int space = 1200 / (playerEntities.length - 1);
        for (int i = 0; i < playerEntities.length; i++) {
            healthBars[i] =
                    new HealthBar(space * i + 48, assetManager);
            stage.addActor(healthBars[i]);
        }
    }

    private void initiateArchers() {
        Texture archerTexture = assetManager.get(assetManager.archer);
        for (Entity player: playerEntities) {
            stage.addActor(new Archer(
                    player,
                    archerTexture
            ));
        }
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
