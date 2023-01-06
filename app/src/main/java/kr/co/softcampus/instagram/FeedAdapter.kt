package kr.co.softcampus.instagram

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.co.softcampus.instagram.databinding.ItemHomeFeedBinding
import java.lang.StringBuilder

class FeedAdapter(private val context:MainActivity,private val dataList: ArrayList<Feed>) :
        RecyclerView.Adapter<FeedAdapter.ViewHolder>(){
            class ViewHolder(private val binding:ItemHomeFeedBinding):
                   RecyclerView.ViewHolder(binding.root){
                       fun bind(context: Context, item:Feed,position: Int){
                           binding.feedTvUserId.text=item.userId
                           val likeCount=StringBuilder()
                           likeCount.append(context.resources.getString(R.string.home_like_count_before))
                               .append(item.likeCount)
                               .append(context.resources.getString(R.string.home_like_count_after))
                           binding.feedTvLikeCount.text=likeCount
                           var database : DatabaseReference
                           val db= Firebase.database
                           database= db.getReference("Feedlist")

                           Glide.with(context).load(item.profileImageUrl).into(binding.feedIvProfile)
                           Glide.with(context).load(item.imageUrl).into(binding.feedIvImage)

                           binding.feedBtnHeart.setOnClickListener{
                               if(item.isLike){
                                   binding.feedBtnHeart.setImageResource(R.drawable.ic_heart_off)
                                   item.isLike=false
                                   item.likeCount-=1

                               }else{
                                   binding.feedBtnHeart.setImageResource(R.drawable.ic_heart_on)
                                   item.isLike=true
                                   item.likeCount+=1
                                   database.child((position+1).toString()).child("likeCount").setValue(item.likeCount)
                               }
                               val likeCount=StringBuilder()
                               likeCount.append(context.resources.getString(R.string.home_like_count_before))
                                   .append(item.likeCount)
                                   .append(context.resources.getString(R.string.home_like_count_after))
                               binding.feedTvLikeCount.text=likeCount

                           }
                           binding.feedBtnBookmark.setOnClickListener{
                               if(item.isBookmark){
                                   binding.feedBtnBookmark.setImageResource(R.drawable.ic_bookmark_off)
                                   item.isBookmark=false
                               }
                               else{
                                   binding.feedBtnBookmark.setImageResource(R.drawable.ic_bookmark_on)
                                   item.isBookmark=true
                               }
                           }

                       }

                   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding= ItemHomeFeedBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context,dataList[position],position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}
