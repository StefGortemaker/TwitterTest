package com.example.tweeter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tweeter.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FollowAdapter extends ArrayAdapter<User> {

    private LayoutInflater inflater;

    FollowAdapter(Context context, List<User> objects) {
        super(context, R.layout.compound_follow_list, objects);

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.compound_follow_list, parent, false);
        }

        User user = getItem(position);

        ImageView ivProfileImage = convertView.findViewById(R.id.ivCFLUserImage);
        TextView tvUserName = convertView.findViewById(R.id.tvCFLName);
        TextView tvScreenNam = convertView.findViewById(R.id.tvCFLScreenName);
        TextView tvDescription = convertView.findViewById(R.id.tvCFLDesc);
        Button btnFollow = convertView.findViewById(R.id.btnCFLFollow);

        if (user != null){
            tvUserName.setText(user.getName());
            tvScreenNam.setText(user.getScreen_name());
            tvDescription.setText(user.getDescription());
            Picasso.get().load(user.getProfile_image_url()).into(ivProfileImage);

            if (user.isFollowing()){
                btnFollow.setText(R.string.btnFollowing);
            } else {
                btnFollow.setText(R.string.btnFollow);
            }
        }

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }
}
