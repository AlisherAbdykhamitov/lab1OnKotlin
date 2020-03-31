package com.example.myinstagramapp;



import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLike extends Fragment {

    private RecyclerView recyclerView;
    private LikedListAdapter adapter;

    private LikedListAdapter.ItemClickListener listener = null;
    private LikedListAdapter.FragmentLikeListener fragmentLikeListener = null;

    List<News> newsList;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fragment_like, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));


        listener = new LikedListAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position, News item) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("news", item);
                startActivity(intent);
            }
        };


        fragmentLikeListener = new LikedListAdapter.FragmentLikeListener() {
            @Override
            public void removeItemLike(News news) {
                ((MainActivity) getActivity()).removeItemLike(news);
            }
        };
        newsList = new ArrayList<News>();
        adapter = new LikedListAdapter(newsList, listener, fragmentLikeListener);
        recyclerView.setAdapter(adapter);
        return rootView;
    }


    public void saveNews(News news) {
        newsList.add(news);
        recyclerView.getAdapter().notifyItemInserted(newsList.size() - 1);
    }

    public void removeNews(News news) {
        if (newsList.indexOf(news)==0){
            newsList.remove(news);
        } else {
            int position = newsList.indexOf(news);
            newsList.remove(news);
            recyclerView.getAdapter().notifyItemRemoved(position);
        }
    }
    public void removeLike(News news){
        int n = newsList.indexOf(news);
        this.removeNews(news);
        adapter.notifyItemRemoved(n);
    }


}

