package com.example.flo_clone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flo_clone.AlbumVPAdapter
import com.example.flo_clone.MainActivity
import com.example.flo_clone.R
import com.example.flo_clone.databinding.FragmentAlbumBinding
import com.google.android.material.tabs.TabLayoutMediator

class AlbumFragment : Fragment() {
    lateinit var binding: FragmentAlbumBinding
    private val information = arrayListOf("수록곡", "상세정보", "영상")

/*액티비티에선 온크리에이트였는데 프래그먼트에선 온크리에잍뷰임*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    /*프래그먼트에서 바인딩 초기화 다음과같이 함*/
    binding = FragmentAlbumBinding.inflate(inflater,container,false)

    binding.albumBackIv.setOnClickListener {
        (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm,HomeFragment()).commitAllowingStateLoss()

    }

    val albumAdapter = AlbumVPAdapter(this)
    binding.albumContentVp.adapter = albumAdapter
//    binding.songLilacLayout.setOnClickListener {
//        Toast.makeText(activity,"LILAC",Toast.LENGTH_SHORT).show()
//    }
    TabLayoutMediator(binding.albumContentTb, binding.albumContentVp){
        tab, position ->
        tab.text = information[position]
    }.attach()
        return binding.root
    }
}