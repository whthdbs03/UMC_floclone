package com.example.flo_clone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo_clone.databinding.ItemAlbumBinding

//얘가 아이템뷰 객체들에게 바인딩해줘야함 아이템걍받아옴 전에만든데이터리스트를매개변수로받아와야함
//어댑터라는 클래스의 상속을 받아야함  뷰홀더도넣어줘야함
class AlbumRVAdapter (private val albumList: ArrayList<Album>) : RecyclerView.Adapter<AlbumRVAdapter.ViewHolder>(){
    // 클릭 인터페이스 정의
//    interface MyItemClickListener{
//        fun onItemClick(album: Album)
//        fun onRemoveAlbum(position: Int)
//    }
//
//    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
//    private lateinit var mItemClickListener: MyItemClickListener
//
//    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
//        mItemClickListener = itemClickListener
//    }

    // 뷰홀더를 생성해줄때 호출되는 함수-> 처음에 생성될떄 몇번호출하고맘.온바인드뷰홀더랑다르게.
    // 여기서 아이템 뷰객체를만든뒤에 재활ㅇ용할라고 뷰홀더에 던져줌.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlbumRVAdapter.ViewHolder {
        //사용하고자하는아이템뷰객체만든뒤                그런데 이 매개변수들이 엉 그건 니가 찾아봐라
        val binding: ItemAlbumBinding = ItemAlbumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        //뷰홀더에 던져주고있죠?
        return ViewHolder(binding)
    }

//    fun addItem(album: Album){
//        albumList.add(album)
//        notifyDataSetChanged()
//    }
//
//    fun removeItem(position: Int){
//        albumList.removeAt(position)
//        notifyDataSetChanged()
//    }

    // 뷰홀더에 데이터를 바인딩해줘야 할때마다 호출되는 함수 => 사용자가화면위아래로스크롤할때마다 엄청나게 많이 호출
    // 얘는 매개변수로 뷰홀더랑 포지션(리사이클러뷰에서는 인덱스아이디)이 들어오지?
    // 따라서 받아온 뷰홀더에 바인딩을 해주기위해 앨범리스트에서 해당포지션인 데이터를 뷰홀더에 방금만든바인드에던져주기
    override fun onBindViewHolder(holder: AlbumRVAdapter.ViewHolder, position: Int) {
        holder.bind(albumList[position]) //이러면 아래 바인드함수가 받아온데이터를객체에 넣어줌
        //holder.itemView.setOnClickListener { mItemClickListener.onItemClick(albumList[position]) }
//        holder.binding.itemAlbumTitleTv.setOnClickListener { mItemClickListener.onRemoveAlbum(position) } //삭제됐을 때
    }

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다.
    override fun getItemCount(): Int = albumList.size

    //얘네는 이너클래스로 뷰홀더를 만들었구나 따로 빼면 엉
    //아이템뷰객체들이 날아가지않도록 잡고있는 아이
    //그럼 매개변수로 아이템뷰객체를 받아야겠지? >그래서 아이템앨범객체를 받아온것임.
    //그리고 뷰홀더 상속받을때 바인딩루트를 어 걍 저렇게 한다고만 알아둬라
    inner class ViewHolder(val binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root){

        //이 바인드함수가 받아온데이터를 아이템뷰객체에넣어줌.
        fun bind(album: Album){
            binding.itemAlbumTitleTv.text = album.title
            binding.itemAlbumSingerTv.text = album.singer
            binding.itemAlbumCoverImgIv.setImageResource(album.coverImg!!)
        }
    }

}