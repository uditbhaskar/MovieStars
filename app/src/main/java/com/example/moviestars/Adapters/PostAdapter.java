package com.example.moviestars.Adapters;

import android.content.Context;
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
import com.example.moviestars.UI.MainActivity;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private List<Data> datas;
    private List<Data> favItemList;
    private Context context;

    public PostAdapter(List<Data> datas, List<Data> favItemList,Context context) {
        this.datas = datas;
        this.favItemList = favItemList;
        this.context = context;
    }

    public void updateItems( List<Data> favItemList) {
       this.favItemList = favItemList;
       notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.post_item_view, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Data data = datas.get(position);
        Glide.with(context).load(data.getAvatar()).into(holder.iv_profile_image);
        holder.tv_name.setText(data.getFirst_name()+" "+data.getLast_name());
        holder.tv_movieName.setText(data.getEmail());
        if(isFavourite(data)) {
            holder.iv_favourite.setImageResource(R.drawable.favourite_selected);
        } else {
            holder.iv_favourite.setImageResource(R.drawable.favourite_not_selected);
        }

        holder.iv_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFavourite(data)) {
                    ((MainActivity)context).removeItem(data);
                } else {
                    ((MainActivity)context).addItem(data);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public boolean isFavourite(Data data) {
        for(int i = 0 ; i<favItemList.size() ; i++) {
            if(data.getId().equals(favItemList.get(i).getId())) {
                return true;
            }
        }
        return false;
    }


    public class PostHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_profile_image;
        TextView tv_name;
        TextView tv_movieName;
        ImageView iv_favourite;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile_image = itemView.findViewById(R.id.profile_image);
            tv_name = itemView.findViewById(R.id.star_name);
            tv_movieName = itemView.findViewById(R.id.movie_name);
            iv_favourite = itemView.findViewById(R.id.favourites);
        }
    }



}
