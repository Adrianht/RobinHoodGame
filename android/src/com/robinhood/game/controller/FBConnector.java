package com.robinhood.game.controller;

import com.badlogic.gdx.math.Vector2;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FBConnector {

    private DatabaseReference mDatabase;
    private Controller controller;

    FBConnector (Controller controller) {
        this.controller = controller;
    }

    // attempts to find a match
    public void findPlayer(final String username) {

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("availablePlayer");
        mDatabase.push().setValue(username);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> playerNames = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    playerNames.add(snapshot.getValue().toString());
                }
                if(playerNames.size() == 2) {
                    FirebaseDatabase.getInstance().getReference()
                            .child("availablePlayer").removeValue();
                    mDatabase = FirebaseDatabase.getInstance().getReference()
                            .child("rooms").child(hashRoomId(playerNames.get(0)));
                    controller.initiateGame(
                            playerNames.get(0),
                            playerNames.get(1)
                    );
                    createGameRoom(hashRoomId(username));
                }
                /*} else if (playerNames.size() == 1) {
                    createGameRoom(hashRoomId(username));
                }

                 */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }
        });
    }

    // Cancels search for opponent
    public void cancelFindPlayer() {
        FirebaseDatabase.getInstance().getReference()
                .child("availablePlayer").removeValue();
    }

    // creates a new game room in firebase real-time database
    public void createGameRoom(String roomRef) {
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child(roomRef).child("move");
        mDatabase.setValue(false);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(controller.isGameInitialized()) {
                    controller.registerMove((boolean) dataSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child(roomRef).child("activeArrow");
        mDatabase.setValue("Normal");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(controller.isGameInitialized()) {
                    controller.registerBuy(dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }
        });


        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child(roomRef).child("drawBow");
        mDatabase.child("x").setValue((float) 1);
        mDatabase.child("y").setValue((float) 2);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(controller.isGameInitialized()) {
                    float x = (float) dataSnapshot.child("x").getValue();
                    float y = (float) dataSnapshot.child("y").getValue();
                    controller.registerDraw(new Vector2(x, y));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }
        });
    }

    // Method creating unique game room id
    private String hashRoomId(String username) {
        // FIXME: change and change system generally,
        //  cannot have similar roomIDs
        return username + "roomhash";
    }

    // TODO: dobbeltsjekk at disse metodene fungerer sammen med lytterne definert i createGameRoom,
    //  slik at controller.register...-metodene blir kalt.
    // Method to change last movement in players game room
    public void setMove(boolean left) {
        mDatabase.child("move").setValue(left);
    }

    // Method to change active arrow type in players game room
    public void setBuy(String type) {
        mDatabase.child("activeArrow").setValue(type);
    }

    // Method to change draw vector in players game room
    public void setDraw(Vector2 vector2) {
        mDatabase.child("drawBow").child("x").setValue(vector2.x);
        mDatabase.child("drawBow").child("y").setValue(vector2.y);
        // TODO: check that the onChange does register both values
        //  before sending to model
    }

}
