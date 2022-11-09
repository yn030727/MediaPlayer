package com.example.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.sql.BatchUpdateException

class MainActivity : AppCompatActivity() {
    private val mediaPlayer = MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button:Button=findViewById(R.id.button)
        val button2:Button=findViewById(R.id.button2)
        val button3:Button =findViewById(R.id.button3)
        initMediaPlayer()
        button.setOnClickListener {
            if(!mediaPlayer.isPlaying){
                mediaPlayer.start()//开始播放
            }
        }
        button2.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.stop()//暂停播放
            }
        }
        button3.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.reset()//停止播放
                initMediaPlayer()
            }
        }

    }

    private fun initMediaPlayer(){
        //首先通过getAssets()语法糖方法来得到一个AssetManager的实例
        val assetManager = assets//可以读取assets目录下的所有资源
        val fd=assetManager.openFd("bing.mp3")  //调用openFd方法将音频文件句柄打开
        //播放的准备工作
        mediaPlayer.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}