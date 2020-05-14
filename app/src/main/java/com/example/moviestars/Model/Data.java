package com.example.moviestars.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    public Data(String last_name, String id, String avatar, String first_name, String email) {
        this.last_name = last_name;
        this.id = id;
        this.avatar = avatar;
        this.first_name = first_name;
        this.email = email;
    }

    private String last_name;

    private String id;

    private String avatar;

    private String first_name;

    private String email;

    protected Data(Parcel in) {
        last_name = in.readString();
        id = in.readString();
        avatar = in.readString();
        first_name = in.readString();
        email = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAvatar ()
    {
        return avatar;
    }

    public void setAvatar (String avatar)
    {
        this.avatar = avatar;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [last_name = "+last_name+", id = "+id+", avatar = "+avatar+", first_name = "+first_name+", email = "+email+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(last_name);
        dest.writeString(id);
        dest.writeString(avatar);
        dest.writeString(first_name);
        dest.writeString(email);
    }
}
