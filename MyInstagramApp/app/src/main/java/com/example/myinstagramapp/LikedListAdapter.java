package com.example.myinstagramapp;



import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LikedListAdapter extends RecyclerView.Adapter<LikedListAdapter.LikedNewsViewHolder> {
    private List<News> newsList;
    private boolean hearted = false;
    @Nullable
    private LikedListAdapter.ItemClickListener listener;
    private @Nullable
    FragmentLikeListener fragmentLikeListener;

    ;

    public LikedListAdapter(List<News> newsList, @Nullable ItemClickListener listener,
                            FragmentLikeListener fragmentLikeListener) {

        this.newsList = newsList;
        this.listener = listener;
        this.fragmentLikeListener = fragmentLikeListener;

    }

    @NonNull
    @Override

    public LikedNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, null, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(params);
        return new LikedNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LikedNewsViewHolder holder, final int position) {

        final News news = newsList.get(getItemViewType(position));
        holder.author.setText(news.getAuthor());
        String s = "<b>"+ news.getAuthor()+ "</b>" + " "+ news.getData();
        holder.data.setText(Html.fromHtml(s));
        holder.likeBtn.setImageResource(R.drawable.hearted);
        Glide.with(holder.image.getContext()).load(news.getImage()).into(holder.image);
        Glide.with(holder.logo.getContext()).load(news.getLogo()).into(holder.logo);
        holder.likeCnt.setText("Нравится: " + news.getLikesCnt());
        holder.comment.setText("Посмотреть все (30)");

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentLikeListener != null)
                    fragmentLikeListener.removeItemLike(news);
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
        return newsList.size();
    }

    public class LikedNewsViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView author;
        ImageView image;
        TextView data;
        TextView likeCnt;
        TextView comment;
        ImageButton likeBtn;

        public LikedNewsViewHolder(@NonNull View itemView) {

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
    public int getItemViewType(int position){
        return position;
    }
    public interface FragmentLikeListener {
        void removeItemLike(News news);
    }


}
