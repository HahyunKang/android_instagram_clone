package kr.co.softcampus.instagram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.co.softcampus.instagram.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private lateinit var  viewBinding: FragmentHomeBinding
    private lateinit var database: DatabaseReference

    private var feedList : ArrayList<Feed> = arrayListOf()
    private var storyList: ArrayList<Story> = arrayListOf(
    Story("zzamong","https://i.ytimg.com/vi/zNi9FLd-NI8/maxresdefault.jpg"),
    Story("zzamong","https://i.ytimg.com/vi/zNi9FLd-NI8/maxresdefault.jpg"),
    Story("zzamong","https://i.ytimg.com/vi/zNi9FLd-NI8/maxresdefault.jpg"),
    Story("zzamong","https://i.ytimg.com/vi/zNi9FLd-NI8/maxresdefault.jpg"),
    Story("zzamong","https://i.ytimg.com/vi/zNi9FLd-NI8/maxresdefault.jpg"),
        Story("zzamong","https://i.ytimg.com/vi/zNi9FLd-NI8/maxresdefault.jpg"),
        Story("zzamong","https://bit.ly/2V1ipNj"),
        Story("zzamong","https://bit.ly/2V1ipNj"),
        Story("zzamong","https://bit.ly/2V1ipNj"),

        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val db= Firebase.database
        database= db.getReference("Feedlist")
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val values= snapshot.value  as ArrayList<HashMap<String, Any>>?
                for(i : Int in 1 until(values?.size ?: 0)){
                    val data= values?.get(i)
                    feedList.add(
                        Feed(
                            data?.get("userId") as String,
                            data.get("imageUrl") as String,
                            data.get("profileImageUrl") as String,
                            data.get("likeCount") as Long,
                            data.get("isLike") as Boolean,
                            data.get("isBookmark") as Boolean
                        )
                    )
                }
                viewBinding.homeRvFeed.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }


        })

        viewBinding = FragmentHomeBinding.inflate(layoutInflater)
        viewBinding.homeRvFeed.layoutManager=LinearLayoutManager(activity as MainActivity,LinearLayoutManager.VERTICAL,false)
        val adapter1=FeedAdapter(activity as MainActivity,feedList)
        viewBinding.homeRvFeed.adapter=adapter1
        viewBinding.homeRvFeed.isNestedScrollingEnabled=false


        viewBinding.homeRvStory.layoutManager=LinearLayoutManager(activity as MainActivity,LinearLayoutManager.HORIZONTAL,false)
        val adapter2=StoryAdapter(activity as MainActivity,storyList)
        viewBinding.homeRvStory.adapter=adapter2
        return viewBinding.root
    }


}