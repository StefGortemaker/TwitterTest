package com.example.tweeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tweeter.model.Dataprovider;
import com.example.tweeter.model.User;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView, backgroundImage;
    TextView tvName, tvScreenName, tvDescription, tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent launchIntent = getIntent();

        if(launchIntent != null){
            int index = launchIntent.getIntExtra("Taskindex", -1);

            imageView = findViewById(R.id.profileImage);
            tvName = findViewById(R.id.textViewName);
            tvScreenName = findViewById(R.id.textViewScreenName);
            tvDescription = findViewById(R.id.textViewDescription);
            tvLocation = findViewById(R.id.textViewLocation);
            backgroundImage = findViewById(R.id.backgroundImage);

            if (index >= 0 && index < Dataprovider.tweets.size()){
                User user = Dataprovider.users.get(index);

                Picasso.get().load(user.getProfile_image_url()).into(imageView);
                tvName.setText(user.getName());
                tvScreenName.setText(user.getScreen_name());
                tvDescription.setText(user.getDescription());
                tvLocation.setText(user.getLocation());
                Picasso.get().load(user.getProfile_background_image_url()).into(backgroundImage);
            }
        }
    }
}
