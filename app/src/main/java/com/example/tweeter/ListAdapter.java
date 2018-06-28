package com.example.tweeter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tweeter.model.Dataprovider;
import com.example.tweeter.model.Entity;
import com.example.tweeter.model.Hashtag;
import com.example.tweeter.model.Tweet;
import com.example.tweeter.model.Url;
import com.example.tweeter.model.User;
import com.example.tweeter.model.UserMention;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.nio.charset.Charset;
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

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.compound_listview, parent, false);
        }

        Tweet tweet = getItem(position);

        if (tweet != null) {
            User user = tweet.getUser();

            Spannable spannable = new SpannableString(tweet.getText());

            TextView tvName = convertView.findViewById(R.id.textViewName);
            TextView tvScreenName = convertView.findViewById(R.id.textViewScreenName);
            TextView tvText = convertView.findViewById(R.id.textViewText);
            TextView createdAt = convertView.findViewById(R.id.textViewCreatedAt);
            ImageView imageView = convertView.findViewById(R.id.profileImage);

            Entity entity = tweet.getEntity();
            List<Hashtag> hashtags = new ArrayList<>();
            List<UserMention> userMentions = new ArrayList<>();
            List<Url> urls = new ArrayList<>();
            if(entity != null) {
                hashtags.addAll(entity.getHashtags());
                userMentions.addAll(entity.getUserMentions());
                urls.addAll(entity.getUrls());
            }

            if(!hashtags.isEmpty()){
                for (int j = 0; j < hashtags.size(); j++) {
                    if (hashtags.get(j) != null) {
                        Hashtag hashtag = hashtags.get(j);
                        spannable.setSpan(new ForegroundColorSpan(Color.RED), hashtag.getStartIndice(), hashtag.getEndIndice(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }

            if(!userMentions.isEmpty()){
                for (int j = 0; j < userMentions.size(); j++) {
                    if (userMentions.get(j) != null) {
                        UserMention userMention = userMentions.get(j);
                        spannable.setSpan(new ForegroundColorSpan(Color.CYAN), userMention.getStartIndice(), userMention.getEndIndice(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }

            if(!urls.isEmpty()){
                for(int j = 0; j < urls.size(); j++){
                    if(urls.get(j)!= null){
                        Url url = urls.get(j);
                        spannable.setSpan(new ForegroundColorSpan(Color.GREEN), url.getStartIndice(), url.getEndIndice(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }


            tvName.setText(user.getName());
            tvText.setText(spannable);
            tvScreenName.setText(user.getScreen_name());
            createdAt.setText(tweet.getCreatAt());
            Picasso.get().load(user.getProfile_image_url()).into(imageView);
        }

        return convertView;
    }
}
