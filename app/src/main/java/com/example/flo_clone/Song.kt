package com.example.flo_clone

data class Song(
    val title : String = "",
    val singer : String = "",
    var second : Int = 0, //총재생시간
    var playTime : Int =0, //얼만큼진행되었는가
    var isPlaying : Boolean = false, //노래가 재생되고있는가
    var music : String ="" //어떤음악이재생되고있었는가
)
