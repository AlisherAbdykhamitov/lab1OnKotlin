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


public class PageFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsListAdapter adapter;
    private NewsListAdapter.ItemClickListener listener = null;
    private NewsListAdapter.FragmentButtonListener fragmentButtonListener = null;
    private NewsListAdapter.FragmentLikeListener fragmentLikeListener = null;


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(
                R.layout.fragment_page, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        listener = new NewsListAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position, News item) {
                Intent intent = new Intent(rootView.getContext(), NewsDetailActivity.class);
                intent.putExtra("news", item);
                startActivity(intent);
            }
        };

        fragmentButtonListener = new NewsListAdapter.FragmentButtonListener() {
            @Override
            public void myClick(News news, int option) {
                ((MainActivity) getActivity()).myClick(news, option);
            }
        };

        fragmentLikeListener = new NewsListAdapter.FragmentLikeListener() {
            @Override
            public void removeItemLike(News news) {
                ((MainActivity)getActivity()).removeItemLike(news);
            }
        };


        adapter = new NewsListAdapter( newsGenerator(), listener, fragmentButtonListener, fragmentLikeListener);
        recyclerView.setAdapter(adapter);


        return rootView;
    }


    public void removeLike(News news) {
        adapter.removeLike(news);
    }

    public static List<News> newsGenerator() {
        List<News> items = new ArrayList<News>();
        ArrayList<Integer> logo = new ArrayList<>();
        ArrayList<String> author = new ArrayList<>();
        ArrayList<Integer> image = new ArrayList<>();
        ArrayList<String> data = new ArrayList<>();

        logo.add(R.drawable.loh);
        author.add("modny_garderob");
        image.add(R.drawable.photo1);
        data.add("Стильная футболка");

        logo.add(R.drawable.log1);
        author.add("koreataldyk");
        image.add(R.drawable.photo2);
        data.add("Вы видели такую? ✔️Двойная сковорода. Удобно! Практично!\n" +
                "Производство: Южная Корея");

        logo.add(R.drawable.log2);
        author.add("afanasya");
        image.add(R.drawable.photo3);
        data.add("Топ 10 книг в моем личном рейтинге");


        logo.add(R.drawable.log3);
        author.add("Boshnikov");
        image.add(R.drawable.photo4);
        data.add("Картинки на сайте: как сократить размер на 80% без потери качества" +
                " В первую очередь все говорят, что изображения на сайте должны быть сочными, красивыми, полезными и " +
                "т.д. Ок, с этим не спорю. Но хочу сделать акцент, что каждый элемент на вашем сайте " +
                "должен помогать достигать вашей цели.");

        logo.add(R.drawable.log4);
        author.add("veronika_tender");
        image.add(R.drawable.photo5);
        data.add("Мечта сбылась \uD83D\uDE48 Ну а в тот момент я думала над тем, что в жизни обязательно должны быть паузы. " +
                "Такие паузы, когда с вами ничего не происходит, " +
                "когда вы просто сидите и смотрите на Мир, а Мир смотрит на вас.");


        logo.add(R.drawable.log5);
        author.add("anton_bekkuzhev_");
        image.add(R.drawable.photo6);
        data.add("TМеня искренне удивляют люди, которые спрашивают, нравится ли мне моя работа. Я не понимаю - а как может быть иначе? Для чего мучать себя и заниматься делом, которое не приносит удовольствие? " +
                "Ради интереса заглянул в статистику и увидел, " +
                "что около трети россиян ходят на работу с большой неохотой.");


        logo.add(R.drawable.log6);
        author.add("sergeysuxov");
        image.add(R.drawable.photo7);
        data.add("Когда кажется, что радугу все таки получится попробовать на вкус, стоит лишь чуть повыше подпрыгнуть✨");

        logo.add(R.drawable.log7);
        author.add("greenbookmovie");
        image.add(R.drawable.photo8);
        data.add("#GreenBookMovie is the winner of 3 Academy Awards including Best Picture. " +
                "Own it NOW on Blu-ray, DVD and Digital [Link in Bio]");
        logo.add(R.drawable.log8);
        author.add("state_of_survival");
        image.add(R.drawable.photo9);
        data.add("Survivor #Tip\n" +
                "Heroes can bring you benefits even outside the battlefield.\n" +
                "\n" +
                "So far, there are four heroes who can add extra bonus to your gathering marches: " +
                "Ghost for gas, Rusty for metal, Mike for food and Chef for wood!");


        logo.add(R.drawable.log9);
        author.add("animals.co");
        image.add(R.drawable.photo10);
        data.add("Smile \uD83D\uDE0A | Photography by @aloe_the_dalmatian");

/////////////////////////////////////////////////

        logo.add(R.drawable.log10);
        author.add("physics");
        image.add(R.drawable.photo11);
        data.add("Follow my personal @aram ❤");

        logo.add(R.drawable.log11);
        author.add("box_khu");
        image.add(R.drawable.photo12);
        data.add("");

        logo.add(R.drawable.log12);
        author.add("adamsandler");
        image.add(R.drawable.photo13);
        data.add("Loooved this man! Stopped what I was doing anytime Curly was on TV. " +
                "Just like every other kid! RIP and Love to his family!");

        logo.add(R.drawable.log13);
        author.add("abdykhamitov.a");
        image.add(R.drawable.photo14);
        data.add("Моя семья!");

        logo.add(R.drawable.log14);
        author.add("_shakarbek");
        image.add(R.drawable.photo15);
        data.add("Я тебе люблю, будем всегда вместе;)");

        logo.add(R.drawable.log15);
        author.add("ufc");
        image.add(R.drawable.photo16);
        data.add("What's on your playlist this week?");

        logo.add(R.drawable.log17);
        author.add("dota2");
        image.add(R.drawable.photo18);
        data.add("Rehearsals are underway ahead of the premiere of True Sight, " +
                "with jet lag making these two even crazier than usual" +
                " \uD83D\uDE01 #truesight #ti9 #dota2 .");

        logo.add(R.drawable.log16);
        author.add("csgo_dev");
        image.add(R.drawable.photo17);
        data.add("Today we are adding Half-Life: Alyx content to CS:GO. Pins, stickers, patches, and an exclusive " +
                "Music Kit for Index owners are now available in-game! #CSGO");

        logo.add(R.drawable.log18);
        author.add("kbtu_official");
        image.add(R.drawable.photo19);
        data.add("ПЕРВАЯ ПРЕДМЕТНАЯ ОЛИМПИАДА КБТУ СОСТОЯЛАСЬ⚡️");

        logo.add(R.drawable.log19);
        author.add("trand_muz");
        image.add(R.drawable.photo20);
        data.add("Согласны или нет?\uD83D\uDC47\uD83C\uDFFB");
        for (int i = 0; i < logo.size(); i++) {
            News news = new News(
                    logo.get(i),
                    author.get(i),
                    image.get(i),
                    data.get(i),
                    i,
                    i
            );
            items.add(news);
        }
        return items;
    }

}