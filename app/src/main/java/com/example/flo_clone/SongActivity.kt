package com.example.flo_clone

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.RenderProcessGoneDetail
import androidx.appcompat.app.AppCompatActivity
import com.example.flo_clone.databinding.ActivityMainBinding
import com.example.flo_clone.databinding.ActivitySongBinding
import java.util.Timer

//클래스를 다른클래스로 상속할때 {소괄호}써주기
class SongActivity :AppCompatActivity() {
    lateinit var binding: ActivitySongBinding //binding은 xml파일과 class파일을 연결하는 것
    lateinit var song: Song //전역변수 하나 만들어서 송데이터클래스를 초기화해주는함수만들것임
    lateinit var timer: Timer

    private var mediaPlayer: MediaPlayer? = null //액티비티 소멸될때 미디어플레이어해제시켜야해서 ? 사용

    override fun onCreate(savedInstanceState: Bundle?) { //함수가 실행될떄 처음으로실행되는애
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root) //xml에 있는걸가져와서우리가맘껏쓰게할거야!

        initSong()
        setPlayer(song)

        //binding.root 가 우리가 맘대로쓸아이디
        //root는 xml파일의 최상단 뷰. 즉, 맥티비티_송xml의 맨위constraint레이아웃뷰
        binding.songBtnArrowDownIv.setOnClickListener {
            finish()
        }
        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(false)
        }
        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(true)
        }

        //받는사람입장에선 인텐트데이터가올수도안올수도잇으니 if문
//        if(intent.hasExtra("title") && intent.hasExtra("singer")){
//            binding.songPlayerTitleTv.text = intent.getStringExtra("title")!!
//            binding.songPlayerNameTv.text = intent.getStringExtra("singer")!!
//        }

    }

    //사용자가 포커스잃으면 음악 중지됨!
    override fun onPause() {
        super.onPause()
        setPlayerStatus(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
        mediaPlayer?.release() //리소스해제 불필요한낭비
        mediaPlayer = null //미디어 플레이어 해제
    }

    private fun initSong(){ //얘를 메인에서 송으로 주고받고있지? 그래서 메인에서도 수정해줄게
        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            song = Song(
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("second", 0),
                intent.getIntExtra("playTime", 0),
                intent.getBooleanExtra("isPlaying", false),
                intent.getStringExtra("music")!!
            )
        }
        startTimer()
    }

    private fun setPlayer(song: Song){
        binding.songPlayerTitleTv.text = song.title
        binding.songPlayerNameTv.text = song.singer
        binding.songStartTimeTv.text = String.format("%02d:%02d",song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d",song.playTime / 60, song.playTime % 60)

        binding.songProgressSb.progress = (song.second * 1000 / song.playTime)

        //뭔음악어딨는지그거세팅ㅇㅇ해서 줌. 미디어플레이어야 이음악 재생할거야~
        val music = resources.getIdentifier(song.music, "raw", this.packageName)
        mediaPlayer = MediaPlayer.create(this, music)

        setPlayerStatus(song.isPlaying)

    }
    fun setPlayerStatus(isPlaying : Boolean){
        song.isPlaying=isPlaying
        timer.isPlaying=isPlaying
        binding.songMiniplayerIv.visibility = View.GONE
        binding.songPauseIv.visibility = View.VISIBLE

        if(isPlaying){
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
            mediaPlayer?.start() //트루나오면 걍 재생 ㄱ
        }
        else{
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
            //false면 음악 중지해야하는데
            if(mediaPlayer?.isPlaying == true){ //미디어플레이어가재생중이아닐떄 포즈 하게되면 오류떠서 이렇게함.
                mediaPlayer?.pause() 
            }
        }
    }

    private fun startTimer(){
        timer = Timer(song.playTime, song.isPlaying)
        timer.start()
    }

    inner class Timer(private var playTime: Int, var isPlaying: Boolean = true):Thread(){
        private var second: Int = 0
        private var mills: Float = 0f
        override fun run() {
            super.run()
            try {
                while (true){
                    if (second>=playTime){
                        break
                    }
                    if(isPlaying){
                        sleep(50)
                        mills+=50

                        runOnUiThread {
                            binding.songProgressSb.progress =((mills/playTime)*100).toInt()
                        }
                        if (mills%1000 == 0f){
                            runOnUiThread {
                                binding.songStartTimeTv.text = String.format("%02d:%02d",second / 60, second % 60)
                            }
                            second++
                        }
                    }
                }
            }catch (e: InterruptedException){
                Log.d("Song", "쓰레드가 죽었습니다. ${e.message}")
            }

        }
    }
}