package com.example.bonplan;

import static android.Manifest.permission.CALL_PHONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        ImageView imageView = findViewById(R.id.poster_image);
        TextView rating_tv = findViewById(R.id.mRating);
        TextView title_tv = findViewById(R.id.mTitle);
        TextView description = findViewById(R.id.description);

        Bundle bundle = getIntent().getExtras();

        String mTitle = bundle.getString("title");
        String mPoster = bundle.getString("poster");
        String mOverView = bundle.getString("description");
        String mRating = bundle.getString("rating");
        String tel = bundle.getString("tel");
        Button callButton = findViewById(R.id.callBtn);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

Intent i = new Intent(Intent.ACTION_DIAL);
i.setData(Uri.parse("tel:"+tel));
if (i.resolveActivity(getPackageManager()) != null) {
      startActivity(i);
}
            }
        });

        Glide.with(this).load(mPoster).into(imageView);
        rating_tv.setText(mRating);
        title_tv.setText(mTitle);
        description.setText(mOverView);
    }
}