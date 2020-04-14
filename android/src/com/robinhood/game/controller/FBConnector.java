package com.robinhood.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.google.firebase.database.ChildEventListener;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FBConnector {

    private String roomRef = "";
    // TODO: legge til unik game room refereanse delt mellom to spillere

    private DatabaseReference mDatabase;
    private Controller controller;

    FBConnector (Controller controller) {
        this.controller = controller;
    }

    public void findPlayer(final String username) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("availablePlayer");

        // TODO: push denne spilleren med username
        mDatabase.push().setValue(username);
        System.out.println("player pushed");

        final List<String> playerNames = new ArrayList<>();

        this.roomRef = username;

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int counter = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    System.out.println(snapshot);
                    counter += 1;
                    System.out.println(roomRef);
                    playerNames.add(snapshot.getValue().toString());
                }

                if(counter == 2){
                    System.out.println("INIT GAME");
                    System.out.println(roomRef);
                    mDatabase.removeValue();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms").child(playerNames.get(0));
                    roomRef = mDatabase.getKey();
                    controller.initiateGame(0, playerNames.get(0), playerNames.get(1));
                } else {
                    createGameRoom(username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void createGameRoom(String roomRef) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms");
        mDatabase.push().setValue(roomRef);


        // TODO: lage default field move, og tilhorende listener onChange()
        //  onChange skal kalle controller.registerMove()
        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms").child(roomRef).child("move");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println("move made " + dataSnapshot);
                //usikker på om setningen under fungerer
                controller.registerMove((Boolean) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // TODO: lage default field activeArrow, og tilhorende listener onChange()
        //  onChange skal kalle controller.registerBuy()

        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms").child(roomRef).child("activeArrow");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //controller.registerBuy(datasnapshot)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // TODO: lage default field drawBow, og tilhorende listener onChange()
        //  onChange skal kalle controller.registerDraw()

        mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms").child(roomRef).child("drawBow");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //controller.registerDraw(datasnapshot)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // TODO: dobbeltsjekk at disse metodene fungerer sammen med lytterne definert i createGameRoom,
    //  slik at controller.register...-metodene blir kalt.
    // Method to change last movement in players game room
    public void setMove(boolean left) {
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child(roomRef).child("move");
        mDatabase.setValue(left);
    }

    // Method to change active arrow type in players game room
    public void setBuy(String type) {
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child(roomRef).child("activeArrow");
        mDatabase.setValue(type);
    }

    // Method to change draw vector in players game room
    public void setDraw(Vector2 vector2) {
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child(roomRef).child("drawBow");
        mDatabase.child("x").setValue(vector2.x);
        mDatabase.child("y").setValue(vector2.y);
        // TODO: check that the onChange does register both values
        //  before sending to model
    }

}
