package com.blogspot.raulfmiranda.dogame

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build

class Util {


    companion object {
        val TAG = "dogame"
        val SOUND_ERROR = R.raw.negative_error
        val SOUND_POSITIVE = R.raw.collect_pick_up
        private var soundPool: SoundPool? = null
        private var soundPoolMap: HashMap<Int, Int>? = null

        fun playSound(context: Context, soundID: Int) {
            initSounds(context)
            val volume = 1f // whatever in the range = 0.0 to 1.0
            // play sound with same right and left volume, with a priority of 1,
            // zero repeats (i.e play once), and a playback rate of 1f
            soundPool?.play(soundPoolMap?.get(soundID)!!, volume, volume, 1, 0, 1f);
        }

        fun initSounds(context: Context) {
            if(soundPool == null || soundPoolMap == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    soundPool = SoundPool.Builder()
                            .setMaxStreams(2)
                            .build()
                } else {
                    soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 100)
                }

                soundPoolMap = HashMap(2)
                soundPoolMap!!.put(SOUND_ERROR, soundPool!!.load(context, R.raw.negative_error, 1))
                soundPoolMap!!.put(SOUND_POSITIVE, soundPool!!.load(context, R.raw.collect_pick_up, 2))
            }
        }
    }



}