package com.robinhood.game.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RoomModel {

    private DatabaseReference mDatabase;
    private Boolean hasGameStarted;
    private ArrayList<String> players = new ArrayList<>();
    private String roomID;



    public RoomModel(String roomId){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms");
        this.roomID = roomId;

        String key = FirebaseDatabase.getInstance().getReference().child("rooms").push().getKey();


        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms").child(roomId).child("players");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String player = dataSnapshot.getKey();
                players.add(player);
                //kjør metode som sjekker om players er to

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String player = dataSnapshot.getKey();
                players.remove(player);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    public void start(){
        hasGameStarted = true;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms").child(roomID).child("hasGameStarted");
        mDatabase.setValue(hasGameStarted);
    }

}
