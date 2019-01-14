package com.example.admin.firebase;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.firebase.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {


    private boolean isOpen = false ;
    private ConstraintSet layout1,layout2;
    private ConstraintLayout constraintLayout ;
    private ImageView imageViewPhoto;
    private TextView tv_email, tv_phone,tv_location,tv_description,tv_username;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        tv_username = (TextView) findViewById(R.id.tv_name);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_location = (TextView) findViewById(R.id.tv_location);
        tv_description = (TextView) findViewById(R.id.tv_description);
        auth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        userID = auth.getCurrentUser().getUid();
        // changing the status bar color to transparent

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        layout1 = new ConstraintSet();
        layout2 = new ConstraintSet();
        imageViewPhoto = findViewById(R.id.photo);
        constraintLayout = findViewById(R.id.constraint_layout);
        layout2.clone(this,R.layout.profile_expanded);
        layout1.clone(constraintLayout);

        imageViewPhoto.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {


                if (!isOpen) {
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    layout2.applyTo(constraintLayout);
                    isOpen = !isOpen ;
                }

                else {

                    TransitionManager.beginDelayedTransition(constraintLayout);
                    layout1.applyTo(constraintLayout);
                    isOpen = !isOpen ;

                }
            }
        });

        myRef = mFirebaseDatabase.getReference().child("users").child(userID);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                String userEmail = user.getEmail();
                String userAddress = user.getAddress();
                String userPhone = String.valueOf(user.getPhone_number());
                String userName =user.getUsername();
                tv_username.setText(userName);
                tv_email.setText(userEmail);
                tv_location.setText(userAddress);
                tv_phone.setText(userPhone);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        myRef.addListenerForSingleValueEvent(valueEventListener);


    }

}