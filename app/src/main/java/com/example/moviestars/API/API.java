package com.example.moviestars.API;

import com.example.moviestars.Model.MyPojo;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class API {
    private static final String url ="https://reqres.in/api/";

    public static MovieStar movieStar = null;

    public static MovieStar getService(){
        if(movieStar==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()) //check manifest to use java compileOptions in gradel
                    .build();

            movieStar= retrofit.create(MovieStar.class);
        }
        return movieStar;
    }

    public interface MovieStar{
        @GET("users?page=1")
        Call<MyPojo> getMovieList();


    }
}
