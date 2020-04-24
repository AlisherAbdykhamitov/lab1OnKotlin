package com.example.myinstagramapp

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myinstagramapp.News
import com.example.myinstagramapp.NewsListAdapter.NewsViewHolder

class NewsListAdapter(newsList: MutableList<News>, listener: ItemClickListener?, fragmentButtonListener: FragmentButtonListener?,
                      fragmentLikeListener: FragmentLikeListener?) : RecyclerView.Adapter<NewsViewHolder>() {
    var newsList: MutableList<News>
    private val listener: ItemClickListener?
    private val fragmentButtonListener: FragmentButtonListener?
    private val fragmentLikeListener: FragmentLikeListener?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, null, false)
        val params = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[getItemViewType(position)]
        holder.author.text = news.author
        val s = "<b>" + news.author + "</b>" + " " + news.data
        holder.data.text = Html.fromHtml(s)
        Glide.with(holder.image.context).load(news.image).into(holder.image)
        Glide.with(holder.logo.context).load(news.logo).into(holder.logo)
        holder.likeCnt.text = "Нравится: " + news.likesCnt
        holder.comment.text = "Посмотреть все (30)"
        if (news.heart == true) holder.likeBtn.setImageResource(R.drawable.hearted) else holder.likeBtn.setImageResource(R.drawable.heart)
        holder.likeBtn.setOnClickListener { v ->
            Toast.makeText(v.context, "Like", Toast.LENGTH_SHORT).show()
            if (fragmentButtonListener != null) {
                if (!news.heart) {
                    holder.likeBtn.setImageResource(R.drawable.hearted)
                    fragmentButtonListener.myClick(news, 1)
                    news.setheart(true)
                } else {
                    holder.likeBtn.setImageResource(R.drawable.heart)
                    fragmentLikeListener!!.removeItemLike(news)
                    news.setheart(false)
                }
            }
        }
        holder.itemView.setOnClickListener { listener?.itemClick(position, news) }
    }

    override fun getItemCount(): Int {
        return News.newsList.size
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var logo: ImageView
        var author: TextView
        var image: ImageView
        var data: TextView
        var likeCnt: TextView
        var comment: TextView
        var likeBtn: ImageButton

        init {
            logo = itemView.findViewById(R.id.logo)
            author = itemView.findViewById(R.id.author)
            image = itemView.findViewById(R.id.image)
            data = itemView.findViewById(R.id.data)
            likeCnt = itemView.findViewById(R.id.likes)
            comment = itemView.findViewById(R.id.comments)
            likeBtn = itemView.findViewById(R.id.likeBtn)
        }
    }

    interface ItemClickListener {
        fun itemClick(position: Int, item: News?)
    }

    interface FragmentLikeListener {
        fun removeItemLike(news: News?)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun removeLike(news: News) {
        val n = News.newsList.indexOf(news)
        news.heart = false
        news.likeBtn = R.drawable.heart
        News.newsList[n] = news
        newsList[n] = news
        this.notifyItemChanged(n)
    }

    interface FragmentButtonListener {
        fun myClick(news: News?, option: Int)
    }

    init {
        News.newsList = newsList
        this.newsList = newsList
        this.listener = listener
        this.fragmentButtonListener = fragmentButtonListener
        this.fragmentLikeListener = fragmentLikeListener
    }
}