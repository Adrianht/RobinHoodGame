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

        // TODO-OLA: back to normal
        /*
        controller.initiateGame(
                "UsernameTest",
                "Username2"
        );
        FirebaseDatabase.getInstance().getReference()
                .child("availablePlayer").removeValue();
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child(hashRoomId("Username"));
        createGameRoom(hashRoomId("Username"));
*/
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
                    controller.initiateGame(
                            playerNames.get(0),
                            playerNames.get(1)
                    );
                    FirebaseDatabase.getInstance().getReference()
                            .child("availablePlayer").removeValue();
                    mDatabase = FirebaseDatabase.getInstance().getReference()
                            .child("rooms").child(hashRoomId(playerNames.get(0)));
                    createGameRoom(hashRoomId(playerNames.get(0)));
                }
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
        mDatabase.child("move").setValue(null);
        mDatabase.child("move").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    controller.registerMove((boolean) dataSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }
        });

        mDatabase.child("activeArrow").setValue(null);
        mDatabase.child("activeArrow").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null) {
                    controller.registerBuy((String) dataSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // empty method
            }
        });

        mDatabase.child("drawBow").setValue(null);
        mDatabase.child("drawBow").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null) {
                    String vectorStr = (String) dataSnapshot.getValue();
                    controller.registerDraw(new Vector2().fromString(vectorStr));
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

    // Method to change last movement in players game room
    public void setMove(boolean left) {
        mDatabase.child("move").setValue(null);
        mDatabase.child("move").setValue(left);
    }

    // Method to change active arrow type in players game room
    public void setBuy(String type) {
        mDatabase.child("activeArrow").setValue(null);
        mDatabase.child("activeArrow").setValue(type);
    }

    // Method to change draw vector in players game room
    public void setDraw(Vector2 vector2) {
        mDatabase.child("drawBow").setValue(vector2.toString());
    }

    // Method to clean database after game is finished
    public void removeRoom() {
        mDatabase.removeValue();
    }

}
