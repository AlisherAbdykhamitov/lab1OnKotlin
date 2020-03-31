package com.example.myinstagramapp;



import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class News implements Parcelable {
    public static List<News> newsList;
    private int logo;
    private String author;
    private int image;
    private String data;
    private int likesCnt;
    private int comment;
    private boolean hearted = false;
    private int likeBtn;

    public int getLikeBtn() {
        return likeBtn;
    }

    public void setLikeBtn(int likeBtn) {
        this.likeBtn = likeBtn;
    }


    public News(int logo, String author, int image, String data, int likes, int comment) {
        this.logo = logo;
        this.author = author;
        this.image = image;
        this.data = data;
        this.likesCnt = likes;
        this.comment = comment;
        this.likeBtn = R.drawable.heart;

    }

    protected News(Parcel in) {
        logo = in.readInt();
        author = in.readString();
        image = in.readInt();
        data = in.readString();
        likesCnt = in.readInt();
        comment = in.readInt();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public boolean getHeart() {
        return hearted;
    }
    public void setHeart(boolean heart) {
        hearted=heart;
    }

    public int getLogo() {
        return logo;
    }

    public String getAuthor() {
        return author;
    }

    public int getImage() {
        return image;
    }

    public String getData() {
        return data;
    }

    public int getLikesCnt() {
        return likesCnt;
    }

    public int getComment() {
        return comment;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLikesCnt(int likes) {
        this.likesCnt = likes;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public void setheart(boolean hearted) {
        this.hearted = hearted;
    }

    @Override
    public String toString() {
        return "News{" +
                "logo='" + logo + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", data='" + data + '\'' +
                ", likesCnt=" + likesCnt +
                ", comment=" + comment +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(logo);
        dest.writeString(author);
        dest.writeInt(image);
        dest.writeString(data);
        dest.writeInt(likesCnt);
        dest.writeInt(comment);
    }


}
