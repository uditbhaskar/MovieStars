package com.example.moviestars.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moviestars.Adapters.SavedPostAdapter;
import com.example.moviestars.DatabaseManager.DBManager;
import com.example.moviestars.Model.Data;
import com.example.moviestars.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {
    List<Data> favDataList;
    RecyclerView recyclerView;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        recyclerView = findViewById(R.id.favRecyclerView);
        backButton = findViewById(R.id.btnBack);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadQuery();
    }

    public void LoadQuery() {
        favDataList = new ArrayList<>();
        favDataList.clear();
        DBManager dbManager = new DBManager(this);
        String[] projections = {"_id", "colMovieId", "firstName", "lastName", "movieName", "time", "image"};
        String[] selectionArgs = {""};
        Cursor cursor = dbManager.Query(projections, "", null, null);

        if (cursor.getCount() == 0) {
            //No data..
            Toast.makeText(this, "No favourites", Toast.LENGTH_SHORT).show();
        } else {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String colMovieId = cursor.getString(cursor.getColumnIndex("colMovieId"));
                    String firstName = cursor.getString(cursor.getColumnIndex("firstName"));
                    String lastName = cursor.getString(cursor.getColumnIndex("lastName"));
                    String movieName = cursor.getString(cursor.getColumnIndex("movieName"));
                    String time = cursor.getString(cursor.getColumnIndex("time"));
                    String image = cursor.getString(cursor.getColumnIndex("image"));

                    favDataList.add(new Data(lastName, colMovieId, image, firstName, movieName));
                } while (cursor.moveToNext());
            }
        }
        if (favDataList != null) {
            Collections.reverse(favDataList);
            //Send favDataList to favourties adapter
            recyclerView.setAdapter(new SavedPostAdapter(favDataList, this));
        }

    }
}
