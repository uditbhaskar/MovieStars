package com.example.moviestars.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviestars.DatabaseManager.DBManager;
import com.example.moviestars.Model.Data;
import com.example.moviestars.R;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    ImageView avatar, favourites, btnBack;
    TextView actorName, actorEmail;
    Data data;
    boolean isFavourite = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setUpViews();
        if (getIntent() != null && getIntent().getExtras() != null) {
            data = getIntent().getExtras().getParcelable("data");

            Glide.with(this).load(data.getAvatar()).into(avatar);
            actorName.setText(data.getFirst_name() + " " + data.getLast_name());
            actorEmail.setText(data.getEmail());
            favourites.setImageResource(R.drawable.favourite_selected);

            favourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isFavourite) {
                        removeItem(data);
                        isFavourite = false;
                        favourites.setImageResource(R.drawable.favourite_not_selected);
                    } else {
                        addItem(data);
                        isFavourite = true;
                        favourites.setImageResource(R.drawable.favourite_selected);
                    }
                }
            });

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            finish();
        }
    }

    private void setUpViews() {
        avatar = findViewById(R.id.imgViewActor);
        favourites = findViewById(R.id.favourites);
        actorName = findViewById(R.id.tvActorName);
        actorEmail = findViewById(R.id.tvActorEmail);
        btnBack = findViewById(R.id.btnBack);
    }

    public void removeItem(Data data) {
        DBManager dbManager = new DBManager(this);
        String[] selectionArgs = {data.getId() + ""};
        dbManager.Delete("colMovieId=?", selectionArgs);
        Toast.makeText(this, "Removed from Favourites", Toast.LENGTH_SHORT).show();

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
        } else {
            Toast.makeText(this, "Unable to add!!!", Toast.LENGTH_SHORT).show();
        }

    }
}
