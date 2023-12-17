package com.example.flo_clone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.flo_clone.Album
import com.example.flo_clone.AlbumRVAdapter
import com.example.flo_clone.BannerVPAdapter
import com.example.flo_clone.MainActivity
import com.example.flo_clone.R
import com.example.flo_clone.databinding.FragmentHomeBinding

class HomeFragment:Fragment() {
    /*private val mBinding : FragmentHomeBinding? = null*/
    //위는 언제 뭐하려고 만든건지 알수없음;
    lateinit var binding: FragmentHomeBinding
    private var albumDatas = ArrayList<Album>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //어레이리스트에 데이터를 담아야함.
        //원래는 서버 사용. 근데 우린안쓰니까 임의로 더미데이터 준비한것.
        albumDatas.apply {
            add(Album("버터","방탄",R.drawable.img_album_exp))
            add(Album("라일락","iu",R.drawable.img_album_exp2))
            add(Album("제목","가수",R.drawable.img_album_exp3))
            add(Album("오잉","가아아수",R.drawable.img_album_exp4))
            add(Album("엥","갓ㄷ굿",R.drawable.img_album_exp5))
            add(Album("으악","굿",R.drawable.img_album_exp6))
        }

        //어댑터와 데이터리스트 연결
        val albumRVAdapter = AlbumRVAdapter(albumDatas)
        // 리사이클러뷰에 어댑터 연결 = 니가 사용해야할 어댑터는 이것이다. 라고 알려줌
        binding.homeTodayMusicAlbumRv.adapter = albumRVAdapter
        // 레이아웃 매니저 설정
        // 우린 수평으로 배치하고 싶음
        binding.homeTodayMusicAlbumRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

//        binding.homePannelAlbumImgbIv.setOnClickListener {
//            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm,AlbumFragment()).commitAllowingStateLoss()
//        }
        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }
}