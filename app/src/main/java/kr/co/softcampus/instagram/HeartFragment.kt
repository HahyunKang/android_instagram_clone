package kr.co.softcampus.instagram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.softcampus.instagram.databinding.FragmentHeartBinding

class HeartFragment: Fragment() {
    private lateinit var  viewBinding: FragmentHeartBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHeartBinding.inflate(layoutInflater)
        return viewBinding.root
    }



}