package com.example.tweeter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tweeter.model.Dataprovider;
import com.example.tweeter.model.Tweet;
import com.example.tweeter.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<Tweet> {

    private LayoutInflater inflater;

    ListAdapter(@NonNull Context context, @NonNull List<Tweet> objects) {
        super(context, R.layout.compound_listview, objects);

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.compound_listview, parent, false);
        }

        Tweet tweet = getItem(position);

        if(tweet != null) {
            User user = tweet.getUser();

            TextView tvName = convertView.findViewById(R.id.textViewName);
            TextView tvScreenName = convertView.findViewById(R.id.textViewScreenName);
            TextView tvText = convertView.findViewById(R.id.textViewText);
            TextView createdAt = convertView.findViewById(R.id.textViewCreatedAt);
            ImageView imageView = convertView.findViewById(R.id.profileImage);

            tvName.setText(user.getName());
            tvText.setText(tweet.getText());
            tvScreenName.setText(user.getScreen_name());
            createdAt.setText(tweet.getCreatAt());
            Picasso.get().load(user.getProfile_image_url()).into(imageView);
        }

        return convertView;
    }
}
