package com.robinhood.game.controller;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FBConnector {

    private DatabaseReference mDatabase;

    public void findPlayer(String username) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("availablePlayer");

        // TODO: push denne spilleren med username

        // TODO: telle antall spillere etter endring

        // TODO: dersom det er to spillere i "availablePlayer" -> kall
        // controller.initateGame()

    }

}
