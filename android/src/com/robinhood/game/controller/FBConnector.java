package com.robinhood.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.google.firebase.database.ChildEventListener;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FBConnector {

    private final String roomid = "FIX";
    // TODO: legge til unik game room refereanse delt mellom to spillere

    private DatabaseReference mDatabase;

    public void findPlayer(String username) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("availablePlayer");

        // TODO: push denne spilleren med username
        mDatabase.push().setValue(username);
        System.out.println("player pushed");


        // TODO: telle antall spillere etter endring

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int counter = 0;
                String roomRef = "";
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    System.out.println(snapshot);
                    counter += 1;
                    System.out.println(roomRef);
                    roomRef += snapshot.getValue().toString();
                }

                if(counter == 2){
                    System.out.println("INIT GAME");
                    System.out.println(roomRef);
                    mDatabase.removeValue();
                    createGameRoom(roomRef);
                } else {
                    //fortsette loadingscreen?
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // TODO: dersom det er to spillere i "availablePlayer" -> kall
        // controller.initateGame(username1,username2);

    }


    public void createGameRoom(String roomRef) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms");
        mDatabase.push().setValue(roomRef);



        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms").child(roomRef);
        mDatabase.push().setValue("fieldMove");
        // TODO: lage default field move, og tilhorende listener onChange()
        //  onChange skal kalle controller.registerMove()


        // TODO: lage default field activeArrow, og tilhorende listener onChange()
        //  onChange skal kalle controller.registerBuy()

        // TODO: lage default field drawBow, og tilhorende listener onChange()
        //  onChange skal kalle controller.registerDraw()

    }

    // TODO: dobbeltsjekk at disse metodene fungerer sammen med lytterne definert i createGameRoom,
    //  slik at controller.register...-metodene blir kalt.
    // Method to change last movement in players game room
    public void setMove(boolean left) {
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child(roomid).child("move");
        mDatabase.push().setValue(left);
    }

    // Method to change active arrow type in players game room
    public void setBuy(String type) {
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child(roomid).child("activeArrow");
        mDatabase.push().setValue(type);
    }

    // Method to change draw vector in players game room
    public void setDraw(Vector2 vector2) {
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child(roomid).child("drawBow");
        mDatabase.child("x").push().setValue(vector2.x);
        mDatabase.child("y").push().setValue(vector2.y);
        // TODO: check that the onChange does register both values
        //  before sending to model
    }

}
