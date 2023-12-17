package com.example.flo_clone

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flo_clone.fragments.DetailFragment
import com.example.flo_clone.fragments.SongFragment
import com.example.flo_clone.fragments.VideoFragment

class AlbumVPAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SongFragment()
            1 -> DetailFragment()
            else -> VideoFragment()
        }
    }
}