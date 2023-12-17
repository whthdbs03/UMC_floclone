package com.example.flo_clone

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVPAdapter(fragment: Fragment) :FragmentStateAdapter(fragment) {

    private val fragmentlist : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int { //이 클래스랑 연결된 뷰페이저에게 데이터를 몇 개 전달할래?
        return fragmentlist.size
    }
//코틀린이니 당연하게도 위 함수는 override fun getItemCount(): Int = fragmentlist.size 이렇게써도됨 함수쨰로초기화

    override fun createFragment(position: Int): Fragment = fragmentlist[position]
    //프레그먼트리스트안에있는아이템들... 즉 프레그먼트를 생성함

    fun addFragment(fragment : Fragment){
        fragmentlist.add(fragment)
        //인자로 받은 프래그먼트를 추가해줄거야
        notifyItemInserted(fragmentlist.size-1) //리스트에 프래그먼트추가되면 뷰페이저에게도 알려야함
        //그래 뷰페이저야 이거 늘었으니까 얘도 포함해서 잘 보여줘야해~!~!
    }


}