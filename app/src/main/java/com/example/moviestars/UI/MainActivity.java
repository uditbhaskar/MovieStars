package com.example.moviestars.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moviestars.API.API;
import com.example.moviestars.Adapters.PostAdapter;
import com.example.moviestars.DatabaseManager.DBManager;
import com.example.moviestars.Model.Data;
import com.example.moviestars.Model.MyPojo;
import com.example.moviestars.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;
    ImageView btnFavourites;
    List<Data> apiDataList;
    List<Data> favDataList;
    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getContext();
        getData();
        recyclerView=findViewById(R.id.movie_star_list);
        btnFavourites = findViewById(R.id.btnFavourites);
        btnFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FavouritesActivity.class));
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadQuery();
    }

    public Context getContext(){
        context = MainActivity.this;
        return context;
    }
    private void getData() {
        Call<MyPojo> myPojoCall = API.getService().getMovieList();
        myPojoCall.enqueue(new Callback<MyPojo>() {
            @Override
            public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {
                MyPojo list=response.body();
                assert list != null;
                apiDataList = Arrays.asList(list.getData());
                postAdapter = new PostAdapter(apiDataList, favDataList, getContext());
                recyclerView.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<MyPojo> call, Throwable t) {

            }
        });

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
            if(postAdapter!=null) {
                postAdapter.updateItems(favDataList);
            }
        }

    }

    public void removeItem(Data data) {
        DBManager dbManager = new DBManager(context);
        String[] selectionArgs = {data.getId() + ""};
        dbManager.Delete("colMovieId=?", selectionArgs);
        Toast.makeText(context, "Removed from Favourites", Toast.LENGTH_SHORT).show();

        LoadQuery();
    }

    public void addItem(Data data) {
        DBManager dbManager = new DBManager(this);
        ContentValues contentValues = new ContentValues();
        contentValues.put("colMovieId", data.getId());
        contentValues.put("firstName", data.getFirst_name());
        contentValues.put("lastName", data.getLast_name());
        contentValues.put("movieName", data.getEmail());
        contentValues.put("time", System.currentTimeMillis() + "");
        contentValues.put("image", data.getAvatar());

        long id = dbManager.Insert(contentValues);
        if (id > 0) {
            Toast.makeText(this, "Added to your favourites", Toast.LENGTH_SHORT).show();
            LoadQuery();
        } else {
            Toast.makeText(this, "Unable to add!!!", Toast.LENGTH_SHORT).show();
        }

    }

}
