package com.example.insta1_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.insta1_2.Adapter.LikedListAdapter
import com.example.insta1_2.Adapter.NewsListAdapter
import com.example.insta1_2.Fragment.FragmentLike
import com.example.insta1_2.Fragment.FragmentPage
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity(), NewsListAdapter.FragmentButtonListener,
    LikedListAdapter.FragmentLikeListener {
    var pager: LockableViewPager? = null
    private var toolbar: Toolbar? = null
    private var bottomNavigationView: BottomNavigationView? = null
    var list: ArrayList<Fragment> = ArrayList()
    var pagefragment = FragmentPage()
    var fragment = FragmentLike()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        inits()
        botNav()
    }

    fun bindViews() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);
        pager = findViewById(R.id.pager)
        bottomNavigationView =
            findViewById(R.id.bottom_navigation)
    }

    fun inits() {
        list.add(pagefragment)
        list.add(fragment)
        pager?.setSwipable(false)
        val pagerAdapter =
            CustomPagerAdapter(supportFragmentManager, list)
        pager?.adapter = pagerAdapter

    }

    fun botNav() {
        bottomNavigationView?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_navigaion -> {
                    pager?.setCurrentItem(0, false)
                    bottomNavigationView?.getMenu()?.findItem(R.id.home_navigaion)
                        ?.setIcon(R.drawable.ic_home)
                    bottomNavigationView?.getMenu()?.findItem(R.id.likes_navigation)
                        ?.setIcon(R.drawable.ic_heart)
                }
                R.id.likes_navigation -> {
                    pager?.setCurrentItem(1, false)
                    item.setIcon(R.drawable.ic_favorite)
                }
            }
            false
        }
    }

    override fun myClick(news: News?, option: Int) {
        if (option == 1) fragment.saveNews(news) else fragment.removeNews(news)
    }

    override fun removeItemLike(news: News?) {
        pagefragment.removeLike(news)
        fragment.removeLike(news)

    }
}