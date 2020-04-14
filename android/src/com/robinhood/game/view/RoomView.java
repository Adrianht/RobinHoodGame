package com.robinhood.game.view;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.robinhood.game.controller.Controller;
import com.robinhood.game.view.interfaceObjects.*;
import com.badlogic.gdx.scenes.scene2d.Stage;


//view med en knapp som du trykker på for å legge deg til i et rom
public class RoomView extends View {
    private Controller controller;
    private DatabaseReference mDatabase;
    private String roomID;


    public RoomView(Controller cont){
        this.controller = cont;

        super.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(super.stage);

        //fikse denne knappen så den viser tekst elns, nå er det bare "right" knappen
        Button roomButton = new Button("room");

        super.stage.addActor(roomButton);
        roomButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //kalle på roomfinder med player objekt
                mDatabase.push().getKey();

                System.out.println("123");
            }
        });
    }
}