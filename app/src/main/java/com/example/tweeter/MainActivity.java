package com.example.tweeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        InputStream is = getBaseContext().getResources().openRawResource(R.raw.statuses);

        try {
            byte [] b = new byte[is.available()];
            is.read(b);
            String fileContent = new String(b);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
