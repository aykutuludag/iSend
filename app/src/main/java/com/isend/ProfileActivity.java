package com.isend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    FrameLayout frame;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String name = MainActivity.name;
        String email = MainActivity.email;
        String photoURL = MainActivity.photoURL;

        //Name
        TextView navUsername = findViewById(R.id.profile_name);
        navUsername.setText(name);
        //E-mail
        TextView navEmail = findViewById(R.id.profile_mail);
        navEmail.setText(email);
        //ProfilePicture
        ImageView profilePic = findViewById(R.id.profile_pic);
        Picasso.with(this).load(photoURL).error(R.drawable.ic_error).placeholder(R.drawable.ic_placeholder)
                .into(profilePic);

        frame = findViewById(R.id.content);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, new ProfileDetail());
        transaction.commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, new ProfileDetail());
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, new ProfileAccounts());
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, new ProfileFriends());
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };
}
