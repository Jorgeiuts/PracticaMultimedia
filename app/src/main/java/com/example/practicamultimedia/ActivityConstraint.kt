package com.example.practicamultimedia

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.SeekBar

class ActivityConstraint : AppCompatActivity() {

    private lateinit var btnPlay: ImageView
    private lateinit var seekBar: SeekBar
    private lateinit var mediaPlayer: MediaPlayer
    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)

        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.song)
        btnPlay = findViewById(R.id.btnPlay)
        seekBar = findViewById(R.id.seekBar)

        seekBar.progress = 0
        seekBar.max = mediaPlayer.duration

        btnPlay.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?){
                if(!mediaPlayer.isPlaying){
                    mediaPlayer.start()
                    btnPlay.setImageResource(R.drawable.pause)
                }
                else {
                    mediaPlayer.pause()
                    btnPlay.setImageResource(R.drawable.play)
                }
            }
        })

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed){
                    mediaPlayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaPlayer.setOnCompletionListener {
            btnPlay.setImageResource(R.drawable.play)
            seekBar.progress = 0

        }

    }
}

