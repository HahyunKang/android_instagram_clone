package kr.co.softcampus.instagram

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.softcampus.instagram.databinding.ItemHomeFeedBinding
import kr.co.softcampus.instagram.databinding.ItemSearchBinding
import java.lang.StringBuilder

class RecommendAdapter(private val context: MainActivity, private val dataList: ArrayList<String>) :
RecyclerView.Adapter<RecommendAdapter.ViewHolder>(){
    class ViewHolder(private val binding: ItemSearchBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item:String){

            Glide.with(context).load(item).into(binding.recommendIv)

                }
            }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemSearchBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context,dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}