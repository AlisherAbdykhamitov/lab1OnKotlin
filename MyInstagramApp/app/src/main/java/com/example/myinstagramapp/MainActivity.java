package com.example.myinstagramapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.TargetApi;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NewsListAdapter.FragmentButtonListener,
        LikedListAdapter.FragmentLikeListener{
    LockableViewPager  pager;
    private Toolbar toolbar;
    private MenuItem prevMenuItem;
    private BottomNavigationView bottomNavigationView;
    List<Fragment> list = new ArrayList<Fragment>();


    PageFragment pagefragment = new PageFragment();

    FragmentLike fragment = new FragmentLike();


    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        list.add(pagefragment);//добавляем фрагмент
        list.add(fragment);
        pager = findViewById(R.id.pager);
        pager.setSwipeable(false);
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(pagerAdapter);

        bottomNavigationView=findViewById(R.id.bottom_navigation);//bottomnavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                pager.setCurrentItem(0, false);
                                bottomNavigationView.getMenu().findItem(R.id.navigation_home).setIcon(R.drawable.ic_home);
                                bottomNavigationView.getMenu().findItem(R.id.navigation_likes).setIcon(R.drawable.ic_heart);

                                break;
                            case R.id.navigation_likes:
                                pager.setCurrentItem(1,false);
                                item.setIcon(R.drawable.ic_favorite);
                                break;

                        }
                        return false;
                    }
                });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }


    @Override
    public void myClick(News news, int option) {

        if (option==1)
            fragment.saveNews(news);
        else
            fragment.removeNews(news);
    }


    public void removeItemLike(News news) {
        pagefragment.removeLike(news);
        fragment.removeLike(news);
    }
}