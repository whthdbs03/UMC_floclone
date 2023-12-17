package com.example.flo_clone

data class Album(
    var title: String = "",
    var singer: String = "",
    var coverImg: Int? = null //이거 이미지 아이디땜에 INt형태인것.
// 이미지 자체를 포함하는 것이 아니라, 앱의 리소스에서 해당 이미지를 식별하기 위한 정수 값일 뿐입니다.
// 리소스 ID를 사용하면 앱이 리소스를 직접 가지고 있기 때문에 관리가 용이할 수 있지만,
// 이미지가 외부 소스에서 로드되는 경우 String 형태로 저장하는 것이 유용할 수 있습니다.
    //var songs: ArrayList<Song>? = null //수록곡 이건 내가 따로 공부해보든가말든가 해봐라
)
