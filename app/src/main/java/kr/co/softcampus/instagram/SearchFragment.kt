package kr.co.softcampus.instagram

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.co.softcampus.instagram.databinding.FragmentSearchBinding

class SearchFragment: Fragment() {
    private lateinit var  viewBinding: FragmentSearchBinding
    private lateinit var database: DatabaseReference

    private var recommendList : ArrayList<String> = arrayListOf(
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val db= Firebase.database
        database= db.getReference("Feedlist")

        if(arguments?.getString("keyword")!=null)
        {  //oderBychilde : userid 기준으로 정렬해서 조회
            var values=database.orderByChild("userId").equalTo(arguments?.getString("keyword"))
            values.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(data in snapshot.children){
                        recommendList.add(data.child("imageUrl").value as String)
                    }
                    viewBinding.rvRecommend.adapter?.notifyDataSetChanged()

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        } else{
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val values= snapshot.value  as ArrayList<HashMap<String,Any>>?
                    for(i : Int in 1 until(values?.size ?: 0)){
                        val data= values?.get(i)
                        recommendList.add(
                            data?.get("imageUrl") as String,
                        )

                    }
                    viewBinding.rvRecommend.adapter?.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                }


            })
        }





        viewBinding = FragmentSearchBinding.inflate(layoutInflater)
        viewBinding.rvRecommend.layoutManager=
            GridLayoutManager(activity as MainActivity,3)
        val adapter=RecommendAdapter(activity as MainActivity,recommendList)
        viewBinding.rvRecommend.adapter=adapter

        viewBinding.searchLl.setOnClickListener{
            Intent(context,SearchActivity::class.java).apply {

            }.run{context?.startActivity(this)}
        }



        return viewBinding.root
    }



}