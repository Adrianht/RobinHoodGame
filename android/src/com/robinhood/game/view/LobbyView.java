package com.robinhood.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.controller.RoomFinder;
import com.robinhood.game.model.Model;
import com.robinhood.game.view.interfaceObjects.*;
import com.badlogic.gdx.scenes.scene2d.Stage;



public class LobbyView extends View {
    private Controller controller;
    private DatabaseReference mDatabase;

    public LobbyView(Controller cont){
        this.controller = cont;

        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms");

        Button roomButton = new Button("room");

        System.out.println(mDatabase);

        super.stage.addActor(roomButton);
        roomButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("create new");
            }
        });
    }
}