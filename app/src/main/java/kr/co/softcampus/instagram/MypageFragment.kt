package kr.co.softcampus.instagram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.softcampus.instagram.databinding.FragmentHeartBinding
import kr.co.softcampus.instagram.databinding.FragmentMypageBinding

class MypageFragment: Fragment() {
    private lateinit var  viewBinding: FragmentMypageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMypageBinding.inflate(layoutInflater)
        return viewBinding.root
    }



}