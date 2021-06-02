package com.example.profile_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class profileView extends AppCompatActivity {
ImageView img;
TextView fname,lname,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        img=findViewById(R.id.View_imgprofile);
        fname=findViewById(R.id.Views_tvname);
        lname=findViewById(R.id.Views_tvlastname);
        email=findViewById(R.id.View_tvemail);

        Intent intent =getIntent();
        Picasso.get().load(intent.getStringExtra("avatar")).into(img);
        fname.setText(getIntent().getStringExtra("fname"));
        lname.setText(getIntent().getStringExtra("lname"));
        email.setText(getIntent().getStringExtra("email"));
    }
}