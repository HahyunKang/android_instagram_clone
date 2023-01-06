package kr.co.softcampus.instagram

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kr.co.softcampus.instagram.databinding.ActivitySearchBinding

class SearchActivity: AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val keywordList: ArrayList<String>? = getKeyWords("keywords")

        binding.rvRecommendSearch.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRecommendSearch.adapter = keywordList?.let { KeywordAdapter(this, it) }

        binding.etSearch.setOnKeyListener { v, keyCode, event ->
            if(event.action== KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER){
                keywordList!!.add(binding.etSearch.text.toString())
                saveKeywords("keywords",keywordList)
                binding.rvRecommendSearch.adapter!!.notifyDataSetChanged()
                    //binding.etSearch.text=null
                Intent(this,MainActivity::class.java).apply{
                    putExtra("keyword",binding.etSearch.text.toString())
                    putExtra("tabFragment","search")
                    this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                }.run{startActivity(this)}



            }
            true


        }
        binding.ivSearchBack.setOnClickListener{
            onBackPressed()
        }

    }


    private fun getKeyWords(key:String): ArrayList<String> {
        val prefs = getSharedPreferences("Dorysgram", Context.MODE_PRIVATE)
        val json = prefs.getString(key, "[]")
        val gson = Gson()

        return gson.fromJson(
            json,
            object : TypeToken<ArrayList<String?>>() {}.type
        )
    }

    private fun saveKeywords(key:String, values: ArrayList<String>){
        val gson=Gson()
        val json=gson.toJson(values)
        val prefs=getSharedPreferences("Dorysgram",Context.MODE_PRIVATE)
        val editor=prefs.edit()
        editor.putString(key,json)
        editor.apply()
    }


    }