package com.robinhood.game.controller;

import androidx.annotation.NonNull;

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
        mDatabase.push().setValue(username);
        System.out.println("player pushed");


        // TODO: telle antall spillere etter endring
        //mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

        mDatabase.addValueEventListener(new ValueEventListener() {
            int counter = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    System.out.println(snapshot);
                    counter += 1;
                    if(counter == 2){
                        System.out.println("INIT GAME");
                        //controller.initiateGame()
                    } else {
                        //fortsette loadingscreen?
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // TODO: dersom det er to spillere i "availablePlayer" -> kall
    }

}
