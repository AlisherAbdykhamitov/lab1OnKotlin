package com.example.myinstagramapp;



import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
    private ImageView imageViewIntentResult;
    private ImageView logo;
    private TextView author;
    private TextView likes;
    private TextView comments;
    private TextView data;
    private ImageView likeBtn;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        backBtn = findViewById(R.id.backBtn);
        imageViewIntentResult = findViewById(R.id.image);
        logo = findViewById(R.id.logo);
        author = findViewById(R.id.author);
        likes = findViewById(R.id.likes);
        comments = findViewById(R.id.comments);
        data = findViewById(R.id.data);
        likeBtn = findViewById(R.id.likeBtn);


        final News news = (News) getIntent().getParcelableExtra("news");
        Glide.with(this).load(news.getImage()).into(imageViewIntentResult);
        Glide.with(this).load(news.getLogo()).into(logo);
        author.setText(news.getAuthor());
        likes.setText("Нравится: " + String.valueOf(news.getLikesCnt()));
        comments.setText("Посмотреть все комментарии(30)");
        String s = "<b>" + news.getAuthor() + "</b>" + " " + news.getData();
        data.setText(Html.fromHtml(s));
        Boolean hearted = getIntent().getBooleanExtra("hearted", false);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Like", Toast.LENGTH_SHORT).show();

                if (news.getLikeBtn()==R.drawable.heart) {

                    Glide.with(likeBtn.getContext()).load(R.drawable.hearted).into(likeBtn);

                } else {
                    Glide.with(likeBtn.getContext()).load(R.drawable.heart).into(likeBtn);

                }

            }
        });





    }
}
