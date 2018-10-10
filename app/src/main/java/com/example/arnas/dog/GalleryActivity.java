package com.example.arnas.dog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

// class for displaying image thumbnails in grid view
public class GalleryActivity extends AppCompatActivity {
    public static final int NUM_OF_COLUMNS = 3;
    JSONArray jsonUrlArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // create urls array from json file
        try {
            JSONObject jsonObj = new JSONObject(loadJSONFromAssets());
            jsonUrlArray = jsonObj.getJSONArray("urls");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), NUM_OF_COLUMNS);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Thumbnail> thumbnails = prepareData();
        ImageAdapter adapter = new ImageAdapter(thumbnails);
        recyclerView.setAdapter(adapter);
    }

    // parse json file from assets folder
    public String loadJSONFromAssets() {
        String json = null;
        try {
            InputStream is = getAssets().open("dog_urls.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // create thumbnail object with image url and add to list
    private ArrayList<Thumbnail> prepareData() {
        ArrayList<Thumbnail> image = new ArrayList<>();
        for (int i = 0; i < jsonUrlArray.length(); i++) {
            Thumbnail thumbnail = new Thumbnail();
            try {
                thumbnail.setImgURL(jsonUrlArray.getString(i));
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            image.add(thumbnail);
        }
        return image;
    }

}
