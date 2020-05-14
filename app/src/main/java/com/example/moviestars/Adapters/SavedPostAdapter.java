package com.example.moviestars.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.moviestars.Model.Data;
import com.example.moviestars.R;
import com.example.moviestars.UI.DetailsActivity;
import com.example.moviestars.UI.FavouritesActivity;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SavedPostAdapter extends RecyclerView.Adapter<SavedPostAdapter.SavedPostHolder> {

    private List<Data> datas;
    private Context context;

    public SavedPostAdapter(List<Data> favItemList,Context context) {
        this.datas = favItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public SavedPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.post_item_view, parent, false);
        return new SavedPostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedPostHolder holder, int position) {
        Data data = datas.get(position);
        Glide.with(context).load(data.getAvatar()).into(holder.iv_profile_image);
        holder.tv_name.setText(data.getFirst_name()+" "+data.getLast_name());
        holder.tv_movieName.setText(data.getEmail());
        holder.iv_favourite.setImageResource(R.drawable.favourite_selected);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("data", data);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class SavedPostHolder extends RecyclerView.ViewHolder {
        CircleImageView iv_profile_image;
        TextView tv_name;
        TextView tv_movieName;
        ImageView iv_favourite;

        public SavedPostHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile_image = itemView.findViewById(R.id.profile_image);
            tv_name = itemView.findViewById(R.id.star_name);
            tv_movieName = itemView.findViewById(R.id.movie_name);
            iv_favourite = itemView.findViewById(R.id.favourites);
        }
    }
}
