package com.merttoptas.googlesignsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class Main2Activity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    ImageView img_profile;
    TextView tvName, tvMail;
    Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        bindViews();
        getCurrentUserInfo();

    }

    public void logout(View view) {

        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {

                        FirebaseAuth.getInstance().signOut();
                        LoginManager.getInstance().logOut();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                });

    }
    private void  bindViews(){

        img_profile = findViewById(R.id.img_profile);
        tvMail = findViewById(R.id.tvMail);
        tvName = findViewById(R.id.tvName);
        btnLogout = findViewById(R.id.logout);
    }

    private void getCurrentUserInfo(){

        user = mAuth.getCurrentUser();

        String currentUserMail = user.getEmail();
        String currentUserName = user.getDisplayName();
        Uri userImg=user.getPhotoUrl();

        Glide.with(getApplicationContext()).load(userImg.toString()).centerCrop().circleCrop().into(img_profile);
        tvName.setText(currentUserName);
        tvMail.setText(currentUserMail);

    }
}
