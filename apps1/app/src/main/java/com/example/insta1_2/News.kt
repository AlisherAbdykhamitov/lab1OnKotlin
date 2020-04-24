package com.example.insta1_2

import java.io.Serializable

data class News(
    val logotype: Int = 0,
    val author: String? = null,
    val image: Int = 0,
    val data: String? = null,
    val likesCount: Int = 0,
    val comment: Int = 0,
    var hearted: Boolean = false

) :Serializable{
    var likeBtn: Int = R.drawable.heart
}