package com.example.myinstagramapp;



import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {
    List<News> newsList;
    @Nullable
    private ItemClickListener listener;
    private @Nullable
    FragmentButtonListener fragmentButtonListener;
    private @Nullable
    FragmentLikeListener fragmentLikeListener;


    public NewsListAdapter(List<News> newsList, @Nullable ItemClickListener listener, @Nullable FragmentButtonListener fragmentButtonListener,
                           @Nullable FragmentLikeListener fragmentLikeListener) {
        News.newsList=newsList;
        this.newsList = newsList;
        this.listener = listener;
        this.fragmentButtonListener = fragmentButtonListener;
        this.fragmentLikeListener = fragmentLikeListener;
    }

    @NonNull
    @Override

    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, null, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(params);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position) {


        final News news = newsList.get(getItemViewType(position));
        holder.author.setText(news.getAuthor());
        String s = "<b>" + news.getAuthor() + "</b>" + " " + news.getData();
        holder.data.setText(Html.fromHtml(s));

        Glide.with(holder.image.getContext()).load(news.getImage()).into(holder.image);
        Glide.with(holder.logo.getContext()).load(news.getLogo()).into(holder.logo);
        holder.likeCnt.setText("Нравится: " + news.getLikesCnt());
        holder.comment.setText("Посмотреть все (30)");

        if (news.getHeart() == true) holder.likeBtn.setImageResource(R.drawable.hearted);
        else holder.likeBtn.setImageResource(R.drawable.heart);

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Like", Toast.LENGTH_SHORT).show();

                if (fragmentButtonListener != null) {
                    if (!news.getHeart()) {
                        holder.likeBtn.setImageResource(R.drawable.hearted);
                        fragmentButtonListener.myClick(news, 1);
                        news.setheart(true);

                    } else {
                        holder.likeBtn.setImageResource(R.drawable.heart);

                        fragmentLikeListener.removeItemLike(news);
                        news.setheart(false);
                    }
                }

            }

        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.itemClick(position, news);

            }
        });

    }

    @Override
    public int getItemCount() {
        return News.newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView author;
        ImageView image;
        TextView data;
        TextView likeCnt;
        TextView comment;
        ImageButton likeBtn;

        public NewsViewHolder(@NonNull View itemView) {

            super(itemView);
            logo = itemView.findViewById(R.id.logo);
            author = itemView.findViewById(R.id.author);
            image = itemView.findViewById(R.id.image);
            data = itemView.findViewById(R.id.data);
            likeCnt = itemView.findViewById(R.id.likes);
            comment = itemView.findViewById(R.id.comments);
            likeBtn = itemView.findViewById(R.id.likeBtn);
        }
    }

    interface ItemClickListener {
        void itemClick(int position, News item);

    }

    public interface FragmentLikeListener {
        void removeItemLike(News news);
    }

    public int getItemViewType(int position) {
        return position;
    }

    public void removeLike(News news) {
        int n = News.newsList.indexOf(news);
        news.setHeart(false);
        news.setLikeBtn(R.drawable.heart);
        News.newsList.set(n, news);
        newsList.set(n,news);

        this.notifyItemChanged(n);
    }

    public interface FragmentButtonListener {
        void myClick(News news, int option);
    }

}
