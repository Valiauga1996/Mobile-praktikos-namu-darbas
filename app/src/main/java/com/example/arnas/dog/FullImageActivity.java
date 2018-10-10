package com.example.arnas.dog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

// class to display full size image on thumbnail click
public class FullImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        ImageView imageView = (ImageView) findViewById(R.id.fullImg);
        RequestOptions options = new RequestOptions()
                .error(R.drawable.error);

        // get image url from previous activity and display the image
        Glide.with(FullImageActivity.this)
                .load(getIntent().getStringExtra("imgURL"))
                .apply(options)
                .into(imageView);
    }
}
