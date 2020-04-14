/*package com.robinhood.game.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoomModel {

    private DatabaseReference mDatabase;
    private Boolean hasGameStarted;
    private ArrayList<String> players = new ArrayList<>();
    private String roomID;



    public RoomModel(String roomId){
        this.roomID = roomId;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms").child(roomId).child("players");

        //sjekker antall players i room
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            int counter = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //Player player = snapshot.getValue(Player.class);
                    //System.out.println(player.getName());
                    System.out.println(snapshot);
                    counter += 1;
                    if(counter == 2){
                        //startgame
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    //denne burde gjøres et annet sted - typ i controller findPlayer()
    public void addPlayer(){
        //player = new Player("asd", "RED");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms").child(roomID).child("players");
        //mDatabase.setValue(player.getName());
    }

    public void start(){
        hasGameStarted = true;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms").child(roomID).child("hasGameStarted");
        mDatabase.setValue(hasGameStarted);
    }
}

 */