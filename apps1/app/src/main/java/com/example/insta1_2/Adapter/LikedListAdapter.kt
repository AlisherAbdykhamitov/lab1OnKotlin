package com.example.insta1_2.Adapter

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.insta1_2.News
import com.example.insta1_2.R

class LikedListAdapter(
    var newsList: List<News?>?, var listener: ItemClickListener?,
    var fragmentLikeListener: FragmentLikeListener?
) :
    RecyclerView.Adapter<LikedListAdapter.LikedNewsViewHolder>() {
    @SuppressLint("InflateParametrs")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedNewsViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, null, false)

        val params = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        return LikedNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikedNewsViewHolder, position: Int) {
        val news = newsList!![getItemViewType(position)]
        holder.author.setText(news?.author)
        val s =
            "<b>" + news?.author.toString() + "</b>" + " " + news?.author
        holder.data.text = Html.escapeHtml(s)
        holder.likeBtn.setImageResource(R.drawable.hearted)
        Glide.with(holder.image.context).load(news?.image).into(holder.image)
        Glide.with(holder.logotype.context).load(news?.logotype).into(holder.logotype)
        holder.likeCnt.text = "Нравится: " + news?.likesCount
        holder.comment.text = "Посмотреть все (30)"
        holder.likeBtn.setOnClickListener { fragmentLikeListener?.removeItemLike(news) }
        holder.itemView.setOnClickListener { listener?.itemClick(position, news) }
    }

    override fun getItemCount(): Int {
        return newsList!!.size
    }

    class LikedNewsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var logotype: ImageView
        var author: TextView
        var image: ImageView
        var data: TextView
        var likeCnt: TextView
        var comment: TextView
        var likeBtn: ImageButton

        init {
            logotype = itemView.findViewById(R.id.logo)
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

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface FragmentLikeListener {
        fun removeItemLike(news: News?)
    }
}