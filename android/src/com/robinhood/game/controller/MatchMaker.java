package com.robinhood.game.controller;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;
import java.util.UUID;

public class MatchMaker {
    UUID uuid = UUID.randomUUID();
    Random rand = new Random();

    String gameRoomUID = uuid.toString();
    int playerName;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public MatchMaker(){
        
    }

    public void initFindPlayer(){
        playerName = rand.nextInt(1000);
        mDatabase.child("rooms").child(gameRoomUID).setValue(playerName);
    }
}
