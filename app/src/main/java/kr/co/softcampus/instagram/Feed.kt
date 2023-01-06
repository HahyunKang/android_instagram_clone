package kr.co.softcampus.instagram

import com.google.android.material.appbar.AppBarLayout

class Feed (val userId:String, val imageUrl:String, val profileImageUrl:String, var likeCount:Long,
var isLike: Boolean, var isBookmark:Boolean)